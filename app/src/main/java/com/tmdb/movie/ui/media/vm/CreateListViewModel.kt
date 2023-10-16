package com.tmdb.movie.ui.media.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.movie.data.CreateListParam
import com.tmdb.movie.data.Result
import com.tmdb.movie.repository.IMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CreateListViewModel @Inject constructor(
    repository: IMovieRepository
) : ViewModel() {

    private val triggerCreateChannel = Channel<CreateListParam>(Channel.CONFLATED)
    private val _createListUiState = MutableStateFlow<CreateListUiState>(CreateListUiState.Idle)
    val createListUiState = _createListUiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = CreateListUiState.Idle
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val createListTrigger = triggerCreateChannel.receiveAsFlow()
        .combine(repository.configStream, ::Pair)
        .filter { it.second.isLogin() }
        .flatMapLatest {
            val (param, config) = it
            repository.createList(config.userData?.sessionId ?: "", param)
        }.map {
            when (it) {
                is Result.Success -> CreateListUiState.Success
                is Result.Error -> CreateListUiState.Error(it.exception?.message)
                is Result.Loading -> CreateListUiState.Loading
            }
        }.onEach {
            _createListUiState.value = it
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = CreateListUiState.Idle
        )

    init {
        createListTrigger.launchIn(viewModelScope)
    }

    fun createList(param: CreateListParam) {
        triggerCreateChannel.trySend(param)
    }

    fun resetUiState() {
        _createListUiState.value = CreateListUiState.Idle
    }
}

sealed class CreateListUiState {
    data object Idle : CreateListUiState()
    data object Loading : CreateListUiState()
    data object Success : CreateListUiState()
    data class Error(val message: String?) : CreateListUiState()
}