package com.tmdb.movie.ui.tv.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.movie.data.Result
import com.tmdb.movie.data.TMDBConfig
import com.tmdb.movie.repository.IMovieRepository
import com.tmdb.movie.ui.tv.SeasonArgs
import com.tmdb.movie.ui.tv.SeasonInfoArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TVDetailViewModel @Inject constructor(
    repository: IMovieRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val configStream: StateFlow<TMDBConfig> = repository.configStream
        .stateIn(
            scope = viewModelScope,
            initialValue = TMDBConfig(),
            started = SharingStarted.WhileSubscribed(5_000),
        )

    private val tvId = SeasonArgs(savedStateHandle).param?.tvId ?: 0
    private val seasonNumber = SeasonArgs(savedStateHandle).param?.seasonNumber ?: 0
    private val episodeNumber = SeasonArgs(savedStateHandle).param?.episodeNumber ?: 0

    val seasonInfo = MutableStateFlow(SeasonInfoArgs(savedStateHandle).seasonInfo)

    val seasonDetail = repository.getSeasonDetail(tvId, seasonNumber)
        .map {
            if (it is Result.Success) {
                it.data
            } else {
                null
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    val episodeDetail = repository.getEpisodeDetail(tvId, seasonNumber, episodeNumber)
        .map {
            if (it is Result.Success) {
                it.data
            } else {
                null
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

}