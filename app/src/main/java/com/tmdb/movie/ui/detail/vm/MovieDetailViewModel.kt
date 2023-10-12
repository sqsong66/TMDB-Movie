package com.tmdb.movie.ui.detail.vm

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.movie.data.AccountState
import com.tmdb.movie.data.FavoriteParam
import com.tmdb.movie.data.ImagesData
import com.tmdb.movie.data.MovieDetails
import com.tmdb.movie.data.Result
import com.tmdb.movie.data.TMDBConfig
import com.tmdb.movie.repository.IMovieRepository
import com.tmdb.movie.ui.detail.MovieArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    repository: IMovieRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val movieId: Int = MovieArgs(savedStateHandle).movieId
    private val movieType: Int = MovieArgs(savedStateHandle).type
    private val triggerFavoriteChannel = Channel<FavoriteParam>(Channel.CONFLATED)
    private val triggerWatchlistChannel = Channel<FavoriteParam>(Channel.CONFLATED)
    private val triggerDetailsChannel = Channel<Boolean>(Channel.CONFLATED)
    private val triggerImagesChannel = Channel<Boolean>(Channel.CONFLATED)
    private val triggerAccountStateChannel = Channel<String>(Channel.CONFLATED)

    private var _accountState = MutableStateFlow<AccountState?>(null)
    val accountState: StateFlow<AccountState?> = _accountState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null,
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    private val accountStateTrigger = triggerAccountStateChannel.receiveAsFlow()
        .filter { it.isNotEmpty() }
        .flatMapLatest { sessionId ->
            repository.getAccountState(movieId, sessionId, movieType)
        }.map { result ->
            when (result) {
                is Result.Success -> {
                    result.data
                }

                is Result.Error -> {
                    null
                }

                Result.Loading -> null
            }
        }
        .onEach {
            _accountState.value = it
            Log.e("sqsong", "accountStateTrigger onEach: $it")
        }
        .onCompletion {
            Log.e("sqsong", "accountStateTrigger onCompletion: $it")
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    private val favoriteStateTrigger = triggerFavoriteChannel.receiveAsFlow()
        .flatMapLatest {
            repository.markAsFavorite(it.accountId, it.sessionId, movieType, movieId, it.favorite)
        }.map {
            when (it) {
                is Result.Success -> {
                    it.data
                }

                is Result.Error -> null

                Result.Loading -> null
            }
        }
        .onEach {
            if (it != null) {
                _accountState.value = _accountState.value?.copy(favorite = it)
            }
            Log.e("sqsong", "favoriteStateTrigger onEach: $it")
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    private val watchlistStateTrigger = triggerWatchlistChannel.receiveAsFlow()
        .flatMapLatest {
            repository.addToWatchlist(it.accountId, it.sessionId, movieType, movieId, it.favorite)
        }.map {
            when (it) {
                is Result.Success -> {
                    it.data
                }

                is Result.Error -> null

                Result.Loading -> null
            }
        }
        .onEach {
            if (it != null) {
                _accountState.value = _accountState.value?.copy(watchlist = it)
            }
            Log.e("sqsong", "watchlistStateTrigger onEach: $it")
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    init {
        onRetry()
        // 订阅 accountState 状态
        accountStateTrigger.launchIn(viewModelScope)
        // 订阅 喜爱 状态
        favoriteStateTrigger.launchIn(viewModelScope)
        // 订阅 收藏 状态
        watchlistStateTrigger.launchIn(viewModelScope)
    }

    val configStream: StateFlow<TMDBConfig> = repository.configStream
        .stateIn(
            scope = viewModelScope,
            initialValue = TMDBConfig(),
            started = SharingStarted.WhileSubscribed(5_000),
        )

    private val movieDetailsFlow: Flow<Result<MovieDetails>> = if (movieType == 0) {
        repository.getMovieDetails(movieId)
    } else {
        repository.getTVDetails(movieId)
    }

    private val imagesFlow: Flow<Result<ImagesData>> = if (movieType == 0) {
        repository.getMovieImages(movieId)
    } else {
        repository.getTVImages(movieId)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val movieImages = triggerImagesChannel.receiveAsFlow()
        .flatMapLatest {
            imagesFlow
        }.map { result ->
            when (result) {
                is Result.Success -> {
                    result.data
                }

                is Result.Error -> {
                    null
                }

                Result.Loading -> null
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    val movieDetail = triggerDetailsChannel.receiveAsFlow()
        .flatMapLatest {
            movieDetailsFlow
        }.map { result ->
            when (result) {
                is Result.Success -> {
                    MovieDetailUiState.Success(result.data)
                }

                is Result.Error -> {
                    MovieDetailUiState.Error(result.exception?.message ?: "Unknown error")
                }

                Result.Loading -> MovieDetailUiState.Loading
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieDetailUiState.Loading,
        )

    fun onRetry() {
        viewModelScope.launch {
            triggerDetailsChannel.trySend(true)
            triggerImagesChannel.trySend(true)
        }
    }

    fun requestAccountState(sessionId: String) {
        viewModelScope.launch {
            triggerAccountStateChannel.trySend(sessionId)
        }
    }

    fun toggleFavorite(accountId: Int, sessionId: String, favorite: Boolean) {
        viewModelScope.launch {
            triggerFavoriteChannel.trySend(FavoriteParam(accountId, sessionId, favorite))
        }
    }

    fun toggleWatchlist(accountId: Int, sessionId: String, watchlist: Boolean) {
        viewModelScope.launch {
            triggerWatchlistChannel.trySend(FavoriteParam(accountId, sessionId, watchlist))
        }
    }
}

sealed class MovieDetailUiState {
    data object Loading : MovieDetailUiState()
    data class Success(val movieDetails: MovieDetails) : MovieDetailUiState()
    data class Error(val message: String) : MovieDetailUiState()
}