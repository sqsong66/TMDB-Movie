package com.tmdb.movie.ui.tv.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.tmdb.movie.repository.IMovieRepository
import com.tmdb.movie.ui.tv.TVArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVDetailViewModel @Inject constructor(
    private val repository: IMovieRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val tvId = TVArgs(savedStateHandle).tvId
    private val tvSeasonNum = TVArgs(savedStateHandle).tvSeasonNum
    private val tvEpisodeNum = TVArgs(savedStateHandle).tvEpisodeNum


}