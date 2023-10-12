package com.tmdb.movie.ui.discovery.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.tmdb.movie.data.TMDBConfig
import com.tmdb.movie.repository.IMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DiscoveryMovieViewModel @Inject constructor(
    movieRepository: IMovieRepository,
) : ViewModel() {

    val configStream: StateFlow<TMDBConfig> = movieRepository.configStream
        .stateIn(
            scope = viewModelScope,
            initialValue = TMDBConfig(),
            started = SharingStarted.WhileSubscribed(5_000),
        )

    val discoveryMoviePagingSource = Pager(config = PagingConfig(pageSize = 1, initialLoadSize = 1)) {
        movieRepository.getDiscoveryMoviePagingSource(0)
    }.flow.cachedIn(viewModelScope)

    val discoveryTvPagingSource = Pager(config = PagingConfig(pageSize = 1, initialLoadSize = 1)) {
        movieRepository.getDiscoveryMoviePagingSource(1)
    }.flow.cachedIn(viewModelScope)

}