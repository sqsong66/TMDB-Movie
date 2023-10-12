package com.tmdb.movie.ui.me.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.movie.data.Result
import com.tmdb.movie.data.TMDBConfig
import com.tmdb.movie.repository.IMovieRepository
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
private const val SESSION_ID = "session_id"

@HiltViewModel
class MeViewModel @Inject constructor(
    repository: IMovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val deleteSessionId = savedStateHandle.getStateFlow(SESSION_ID, "")
    private val requestToken = savedStateHandle.getStateFlow(REQUEST_TOKEN, "")

    val configStream = repository.configStream
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TMDBConfig()
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    val authorizeState = requestToken
        .flatMapLatest {
            if (it.isNotEmpty()) {
                repository.refreshUserData(it)
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

    @OptIn(ExperimentalCoroutinesApi::class)
    val signOutState = deleteSessionId
        .flatMapLatest {
            if (it.isNotEmpty()) {
                repository.signOut(it)
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

    fun signOut(sessionId: String) {
        savedStateHandle[SESSION_ID] = sessionId
    }
}