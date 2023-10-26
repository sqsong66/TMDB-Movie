package com.tmdb.movie.ui.home.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.tmdb.movie.data.MediaItem
import com.tmdb.movie.data.People
import com.tmdb.movie.data.Result
import com.tmdb.movie.data.TMDBConfig
import com.tmdb.movie.repository.IMovieRepository
import com.tmdb.movie.repository.ISearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val SEARCH_QUERY = "search_query"
private const val SEARCH_STATE = "search_state"

@HiltViewModel
class HomeViewModel @Inject constructor(
    movieRepository: IMovieRepository,
    private val searchRepository: ISearchRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // 使用channel触发请求，防止页面销毁重建重新触发冗余请求
    private val triggerChannel = Channel<Boolean>(Channel.CONFLATED)
    private val triggerChannel1 = Channel<Boolean>(Channel.CONFLATED)
    private val triggerChannel2 = Channel<Boolean>(Channel.CONFLATED)
    private val triggerChannel3 = Channel<Boolean>(Channel.CONFLATED)
    private val triggerChannel4 = Channel<Boolean>(Channel.CONFLATED)
    private val triggerChannel5 = Channel<Boolean>(Channel.CONFLATED)
    private val triggerChannel6 = Channel<Boolean>(Channel.CONFLATED)

    val searchQuery = savedStateHandle.getStateFlow(SEARCH_QUERY, "")

    val isSearchBarActive = savedStateHandle.getStateFlow(SEARCH_STATE, false)

    init {
        toggleRefresh()
    }

    val configStream: StateFlow<TMDBConfig> = movieRepository.configStream
        .stateIn(
            scope = viewModelScope,
            initialValue = TMDBConfig(),
            started = SharingStarted.WhileSubscribed(5_000),
        )

    val popularPeopleState: StateFlow<MovieLoadState<People>> = buildNetworkStateFlow(triggerChannel2) { movieRepository.getPopularPeople() }

    val movieTrendingState: StateFlow<MovieLoadState<MediaItem>> = buildNetworkStateFlow(triggerChannel) { movieRepository.getMoviesTrending() }

    val tvTrendingState: StateFlow<MovieLoadState<MediaItem>> = buildNetworkStateFlow(triggerChannel1) { movieRepository.getTVTrending() }

    val movieNowPlayingState: StateFlow<MovieLoadState<MediaItem>> = buildNetworkStateFlow(triggerChannel3) { movieRepository.getMovieNowPlaying() }

    val tvAirTodayState: StateFlow<MovieLoadState<MediaItem>> = buildNetworkStateFlow(triggerChannel4) { movieRepository.getTVAiringToday() }

    val moviePopularState: StateFlow<MovieLoadState<MediaItem>> = buildNetworkStateFlow(triggerChannel5) { movieRepository.getMoviePopular() }

    val tvPopularState: StateFlow<MovieLoadState<MediaItem>> = buildNetworkStateFlow(triggerChannel6) { movieRepository.getTVPopular() }

    val recentSearchList = searchRepository.getRecentSearchQueries()
        .stateIn(
            scope = viewModelScope,
            initialValue = emptyList(),
            started = SharingStarted.WhileSubscribed(5_000),
        )

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val searchResult = searchQuery
        .filter { it.isNotEmpty() }
        .debounce(500)
        .flatMapLatest { query ->
            Pager(config = PagingConfig(pageSize = 1, initialLoadSize = 1)) {
                movieRepository.getSearchMoviePagingSource(query)
            }.flow
        }.cachedIn(viewModelScope)

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun <T> buildNetworkStateFlow(
        channel: Channel<Boolean>,
        queryFlow: () -> Flow<Result<List<T>>>
    ): StateFlow<MovieLoadState<T>> {
        return channel.receiveAsFlow()
            .flatMapLatest { queryFlow() }
            .map {
                when (it) {
                    is Result.Success -> {
                        MovieLoadState.Success(it.data)
                    }

                    is Result.Error -> {
                        MovieLoadState.Error(it.exception?.message)
                    }

                    else -> MovieLoadState.Loading()
                }
            }
            /*.onCompletion {
                Log.w("sqsong", "HomeViewModel buildNetworkStateFlow onCompletion: $it, queryFlow: $queryFlow")
            }*/
            .stateIn(
                scope = viewModelScope,
                initialValue = MovieLoadState.Loading(),
                started = SharingStarted.WhileSubscribed(5_000),
            )
    }

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }

    fun changeSearchBarActiveState(active: Boolean) {
        if (!active) {
            viewModelScope.launch {
                delay(500)
                savedStateHandle[SEARCH_QUERY] = ""
            }
        }
        savedStateHandle[SEARCH_STATE] = active
    }

    fun onSearchTriggered(query: String) {
        viewModelScope.launch {
            searchRepository.insertOrReplaceRecentSearch(query)
        }
    }

    fun toggleRefresh() {
        viewModelScope.launch {
            triggerChannel.trySend(true)
            triggerChannel1.trySend(true)
            triggerChannel2.trySend(true)
            triggerChannel3.trySend(true)
            triggerChannel4.trySend(true)
            triggerChannel5.trySend(true)
            triggerChannel6.trySend(true)
        }
    }

}

sealed class MovieLoadState<T> {
    data class Loading<T>(val any: Any? = null) : MovieLoadState<T>()
    data class Success<T>(val data: List<T>) : MovieLoadState<T>()
    data class Error<T>(val msg: String?) : MovieLoadState<T>()
}