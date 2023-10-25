package com.tmdb.movie.ui.lists.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.movie.data.ListsDetail
import com.tmdb.movie.data.Result
import com.tmdb.movie.data.TMDBConfig
import com.tmdb.movie.repository.IMovieRepository
import com.tmdb.movie.ui.lists.ListsIdArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListsDetailViewModel @Inject constructor(
    repository: IMovieRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val listId = ListsIdArgs(savedStateHandle).listId
    private val triggerChannel = Channel<Unit>(Channel.CONFLATED)
//    private var _uiState = MutableStateFlow<ListsDetailUiState>(ListsDetailUiState.Loading)
//    val uiState: StateFlow<ListsDetailUiState> = _uiState.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5_000),
//        initialValue = ListsDetailUiState.Loading,
//    )

    val configStream: StateFlow<TMDBConfig> = repository.configStream
        .stateIn(
            scope = viewModelScope,
            initialValue = TMDBConfig(),
            started = SharingStarted.WhileSubscribed(5_000),
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState = triggerChannel.receiveAsFlow()
        .flatMapLatest { repository.getListsDetail(listId) }
        .map { result ->
            when (result) {
                is Result.Success -> {
                    ListsDetailUiState.Success(result.data)
                }

                is Result.Error -> {
                    ListsDetailUiState.Error(result.exception?.message)
                }

                Result.Loading -> ListsDetailUiState.Loading
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ListsDetailUiState.Loading,
        )

    init {
        viewModelScope.launch {
            triggerChannel.trySend(Unit)
        }
//        listsDetailTrigger.launchIn(viewModelScope)
    }
}

sealed class ListsDetailUiState {
    data object Loading : ListsDetailUiState()
    data class Success(val data: ListsDetail) : ListsDetailUiState()
    data class Error(val error: String?) : ListsDetailUiState()

}