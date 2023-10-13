package com.tmdb.movie.ui.me.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.movie.data.Result
import com.tmdb.movie.repository.IMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import java.util.UUID
import javax.inject.Inject

private const val REFRESH_KEY = "refresh_key"

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val repository: IMovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val refreshKey = savedStateHandle.getStateFlow(REFRESH_KEY, "")

    private var _uiState = MutableStateFlow<AuthenticationUiState>(AuthenticationUiState.Loading)
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AuthenticationUiState.Loading
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    private val refreshState = refreshKey.filter { it.isNotEmpty() }
        .flatMapLatest { repository.getRequestToken() }
        .map {
            when (it) {
                is Result.Success -> AuthenticationUiState.Success(it.data.requestToken ?: "")
                is Result.Error -> AuthenticationUiState.Error(it.exception?.message)
                is Result.Loading -> AuthenticationUiState.Loading
            }
        }.onEach {
            _uiState.value = it
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AuthenticationUiState.Loading
        )

    init {
        refreshState.launchIn(viewModelScope)
    }

    fun updateOnAuthorize() {
        _uiState.value.let {
            if (it is AuthenticationUiState.Success) {
                _uiState.value = AuthenticationUiState.OnAuthorize(it.authUrl)
            }
        }
    }

    fun resetAuthState() {
        _uiState.value = AuthenticationUiState.Loading
    }

    fun refresh() {
        savedStateHandle[REFRESH_KEY] = UUID.randomUUID().toString()
    }

}

sealed class AuthenticationUiState {
    data object Loading : AuthenticationUiState()
    class Success(val authUrl: String) : AuthenticationUiState()
    class Error(val error: String?) : AuthenticationUiState()
    class OnAuthorize(val authUrl: String) : AuthenticationUiState()
}