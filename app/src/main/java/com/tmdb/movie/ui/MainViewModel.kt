package com.tmdb.movie.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.movie.data.TMDBConfig
import com.tmdb.movie.repository.IMovieRepository
import com.tmdb.movie.ui.MainActivityUiState.Loading
import com.tmdb.movie.ui.MainActivityUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    movieRepository: IMovieRepository
) : ViewModel() {

    val uiState = movieRepository.configStream
        .map {
            Log.w("sqsong", "TMDBConfig: $it")
            Success(it)
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = Loading,
            started = SharingStarted.WhileSubscribed(5_000),
        )
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val config: TMDBConfig) : MainActivityUiState
}