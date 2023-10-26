package com.tmdb.movie.ui.main.vm

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.movie.data.Result
import com.tmdb.movie.data.TMDBConfig
import com.tmdb.movie.repository.IMovieRepository
import com.tmdb.movie.ui.main.vm.MainActivityUiState.Loading
import com.tmdb.movie.ui.main.vm.MainActivityUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.util.UUID
import javax.inject.Inject

private const val REQUEST_TOKEN = "request_token"

@HiltViewModel
class MainViewModel @Inject constructor(
    movieRepository: IMovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val requestToken = savedStateHandle.getStateFlow(REQUEST_TOKEN, "")

    val uiState = movieRepository.configStream
        .map {
            Log.w("sqsong", "TMDBConfig: $it")
            Success(it)
        }
        .map {
            val config = it.config
            if (config.isLogin()) {
                config.userData?.sessionId?.let { id ->
                    try {
                        movieRepository.updateUserData(id)
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }
            }
            it
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = Loading,
            started = SharingStarted.WhileSubscribed(5_000),
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    val authorizeState = requestToken
        .flatMapLatest {
            if (it.isNotEmpty()) {
                movieRepository.refreshUserData(it)
            } else {
                flow { emit(Result.Loading) }
            }
        }
        .map {
            when (it) {
                Result.Loading -> ""
                is Result.Success -> "success_${UUID.randomUUID()}"
                is Result.Error -> "error_${UUID.randomUUID()}"
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ""
        )

    fun updateRequestToken(token: String) {
        savedStateHandle[REQUEST_TOKEN] = token
    }
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val config: TMDBConfig) : MainActivityUiState
}