package com.tmdb.movie.ui.lists.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.tmdb.movie.data.Result
import com.tmdb.movie.data.TMDBConfig
import com.tmdb.movie.repository.IMovieRepository
import com.tmdb.movie.ui.lists.AccountArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AccountListsViewModel @Inject constructor(
    repository: IMovieRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val accountId = AccountArgs(savedStateHandle).accountId

    val accountListsPagingSource = Pager(config = PagingConfig(pageSize = 1, initialLoadSize = 1)) {
        repository.getAccountListsPagingSource(accountId)
    }.flow.cachedIn(viewModelScope)

    val configStream: StateFlow<TMDBConfig> = repository.configStream
        .stateIn(
            scope = viewModelScope,
            initialValue = TMDBConfig(),
            started = SharingStarted.WhileSubscribed(5_000),
        )

    val cachedMovies = repository.getHomePopularMovie()
        .map { result ->
            if (result is Result.Success) {
                result.data
            } else {
                emptyList()
            }
        }.stateIn(
            scope = viewModelScope,
            initialValue = emptyList(),
            started = SharingStarted.WhileSubscribed(5_000),
        )

}