package com.tmdb.movie.ui.detail.vm

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.movie.data.AccountState
import com.tmdb.movie.data.FavoriteParam
import com.tmdb.movie.data.ImagesData
import com.tmdb.movie.data.MediaList
import com.tmdb.movie.data.MediaType
import com.tmdb.movie.data.MovieDetails
import com.tmdb.movie.data.Result
import com.tmdb.movie.data.TMDBConfig
import com.tmdb.movie.network.TMDBNetworkException
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
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    repository: IMovieRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val mediaType: Int = MovieArgs(savedStateHandle).type
    private val mediaId: Int = MovieArgs(savedStateHandle).movieId
    private val triggerMediaListChannel = Channel<Int>(Channel.CONFLATED)
    private val triggerImagesChannel = Channel<Boolean>(Channel.CONFLATED)
    private val triggerDetailsChannel = Channel<Boolean>(Channel.CONFLATED)
    private val triggerAccountStateChannel = Channel<String>(Channel.CONFLATED)
    private val triggerAddListChannel = Channel<AddListParam>(Channel.CONFLATED)
    private val triggerFavoriteChannel = Channel<FavoriteParam>(Channel.CONFLATED)
    private val triggerWatchlistChannel = Channel<FavoriteParam>(Channel.CONFLATED)

    private var _accountState = MutableStateFlow<AccountState?>(null)
    val accountState: StateFlow<AccountState?> = _accountState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null,
    )

    private var _addListState = MutableStateFlow<AddListUiState>(AddListUiState.Idle)
    val addListState: StateFlow<AddListUiState> = _addListState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AddListUiState.Idle,
    )

    private var _mediaListUiState = MutableStateFlow<MediaListUiState>(MediaListUiState.Idle)
    val mediaListUiState: StateFlow<MediaListUiState> = _mediaListUiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MediaListUiState.Idle,
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    private val accountStateTrigger = triggerAccountStateChannel.receiveAsFlow()
        .filter { it.isNotEmpty() }
        .flatMapLatest { sessionId ->
            repository.getAccountState(mediaId, sessionId, mediaType)
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
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    private val favoriteStateTrigger = triggerFavoriteChannel.receiveAsFlow()
        .flatMapLatest {
            repository.markAsFavorite(it.accountId, it.sessionId, mediaType, mediaId, it.favorite)
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
            repository.addToWatchlist(it.accountId, it.sessionId, mediaType, mediaId, it.favorite)
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

    @OptIn(ExperimentalCoroutinesApi::class)
    val mediaListUiStateTrigger: StateFlow<MediaListUiState> = triggerMediaListChannel.receiveAsFlow()
        .flatMapLatest { repository.getAccountMediaLists(it) }
        .map {
            Log.d("sqsong", "getAccountMediaLists mediaListUiState: $it")
            when (it) {
                is Result.Success -> {
                    MediaListUiState.Success(it.data)
                }

                is Result.Error -> MediaListUiState.Error(it.exception?.message ?: "Unknown error")

                Result.Loading -> MediaListUiState.Idle
            }
        }
        .onEach {
            _mediaListUiState.value = it
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MediaListUiState.Idle,
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    val addListTrigger: StateFlow<AddListUiState> = triggerAddListChannel.receiveAsFlow()
        .flatMapLatest {
            repository.addMediaToList(it.sessionId, it.mediaId, it.listId, if (mediaType == MediaType.MOVIE) "movie" else "tv")
        }.map {
            when (it) {
                is Result.Success -> {
                    val result = it.data
                    if (result.success) {
                        AddListUiState.Success
                    } else {
                        AddListUiState.Error(0, result.statusMessage)
                    }
                }

                is Result.Error -> {
                    val exception = it.exception
                    if (exception is TMDBNetworkException) {
                        if (exception.errorCode == 8) {
                            AddListUiState.Error(1, exception.message)
                        } else {
                            AddListUiState.Error(0, exception.message)
                        }
                    } else {
                        AddListUiState.Error(0, it.exception?.message)
                    }
                }

                Result.Loading -> AddListUiState.Idle
            }
        }
        .onEach {
            Log.e("sqsong", "addListTrigger onEach: $it")
            _addListState.value = it
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AddListUiState.Idle,
        )


    init {
        onRetry()
        // 订阅 accountState 状态
        accountStateTrigger.launchIn(viewModelScope)
        // 订阅 喜爱 状态
        favoriteStateTrigger.launchIn(viewModelScope)
        // 订阅 收藏 状态
        watchlistStateTrigger.launchIn(viewModelScope)
        // 订阅 添加列表 状态
        addListTrigger.launchIn(viewModelScope)
        // 列表 状态
        mediaListUiStateTrigger.launchIn(viewModelScope)
    }

    val configStream: StateFlow<TMDBConfig> = repository.configStream
        .stateIn(
            scope = viewModelScope,
            initialValue = TMDBConfig(),
            started = SharingStarted.WhileSubscribed(5_000),
        )

    private val movieDetailsFlow: Flow<Result<MovieDetails>> = if (mediaType == 0) {
        repository.getMovieDetails(mediaId)
    } else {
        repository.getTVDetails(mediaId)
    }

    private val imagesFlow: Flow<Result<ImagesData>> = if (mediaType == 0) {
        repository.getMovieImages(mediaId)
    } else {
        repository.getTVImages(mediaId)
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

    fun toggleGetMediaList(accountId: Int) {
        viewModelScope.launch {
            triggerMediaListChannel.trySend(accountId)
        }
    }

    fun toggleAddMediaToList(sessionId: String, mediaId: Int, listId: Int) {
        viewModelScope.launch {
            triggerAddListChannel.trySend(AddListParam(sessionId, mediaId, listId))
        }
    }

    fun resetAddListState() {
        _addListState.value = AddListUiState.Idle
    }

    fun resetMediaListState() {
        _mediaListUiState.value = MediaListUiState.Idle
    }
}

sealed class MovieDetailUiState {
    data object Loading : MovieDetailUiState()
    data class Success(val movieDetails: MovieDetails) : MovieDetailUiState()
    data class Error(val message: String) : MovieDetailUiState()
}

sealed class MediaListUiState {
    data class Success(val mediaList: List<MediaList>?) : MediaListUiState()
    data class Error(val message: String) : MediaListUiState()
    data object Idle : MediaListUiState()
}

sealed class AddListUiState {
    data object Success : AddListUiState()
    data class Error(val errorType: Int, val message: String?) : AddListUiState()
    data object Idle : AddListUiState()
}

data class AddListParam(
    val sessionId: String,
    val mediaId: Int,
    val listId: Int,
)