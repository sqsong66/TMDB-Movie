package com.tmdb.movie.ui.people.vm

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.movie.data.PeopleCast
import com.tmdb.movie.data.PeopleDetails
import com.tmdb.movie.data.Result.Error
import com.tmdb.movie.data.Result.Success
import com.tmdb.movie.data.SortedPeopleCredits
import com.tmdb.movie.data.TMDBConfig
import com.tmdb.movie.repository.IMovieRepository
import com.tmdb.movie.ui.people.PeopleArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleDetailViewModel @Inject constructor(
    val repository: IMovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val peopleId: Int = PeopleArgs(savedStateHandle).peopleId
    private val triggerDetailsChannel = Channel<Boolean>(Channel.CONFLATED)
    private val triggerCreditsChannel = Channel<Boolean>(Channel.CONFLATED)

    init {
        Log.w("sqsong", "PeopleDetailViewModel peopleId: $peopleId")
        onRetry()
    }

    val configStream: StateFlow<TMDBConfig> = repository.configStream
        .stateIn(
            scope = viewModelScope,
            initialValue = TMDBConfig(),
            started = SharingStarted.WhileSubscribed(5_000),
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    val peopleDetailUiState: StateFlow<PeopleDetailUiState> = triggerDetailsChannel.receiveAsFlow()
        .flatMapLatest { repository.getPeopleDetails(peopleId) }
        .map { result ->
            when (result) {
                is Success -> PeopleDetailUiState.Success(result.data)
                is Error -> PeopleDetailUiState.Error(result.exception?.message)
                else -> PeopleDetailUiState.Loading
            }
        }.onCompletion {
            Log.e("sqsong", "PeopleDetailViewModel peopleDetails onCompletion: $it")
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PeopleDetailUiState.Loading,
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    val peopleCredits: StateFlow<SortedPeopleCredits?> = triggerCreditsChannel.receiveAsFlow()
        .flatMapLatest { repository.getPeopleCredits(peopleId) }
        .map {
            when (it) {
                is Success -> {
                    val credits = it.data.toSortedPeopleCredits()
                    credits
                }

                else -> null
            }
        }.flowOn(Dispatchers.IO).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SortedPeopleCredits(),
        )

    fun onRetry() {
        viewModelScope.launch {
            triggerDetailsChannel.trySend(true)
            triggerCreditsChannel.trySend(true)
        }
    }
}

sealed class PeopleDetailUiState {
    data object Loading : PeopleDetailUiState()
    data class Success(val data: PeopleDetails) : PeopleDetailUiState()
    data class Error(val message: String?) : PeopleDetailUiState()
}