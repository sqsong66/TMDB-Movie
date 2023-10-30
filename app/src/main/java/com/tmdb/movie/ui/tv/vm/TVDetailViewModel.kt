package com.tmdb.movie.ui.tv.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.movie.data.TMDBConfig
import com.tmdb.movie.repository.IMovieRepository
import com.tmdb.movie.ui.tv.SeasonInfoArgs
import com.tmdb.movie.ui.tv.SeasonInfoNavType
import com.tmdb.movie.ui.tv.TVArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TVDetailViewModel @Inject constructor(
    repository: IMovieRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val seasonInfo = MutableStateFlow(SeasonInfoArgs(savedStateHandle).seasonInfo)

    val configStream: StateFlow<TMDBConfig> = repository.configStream
        .stateIn(
            scope = viewModelScope,
            initialValue = TMDBConfig(),
            started = SharingStarted.WhileSubscribed(5_000),
        )


}