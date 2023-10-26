package com.tmdb.movie.ui.account.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.tmdb.movie.data.MediaType
import com.tmdb.movie.data.TMDBConfig
import com.tmdb.movie.repository.IMovieRepository
import com.tmdb.movie.ui.account.AccountMediaListArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AccountMediaLisViewModel @Inject constructor(
    repository: IMovieRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val accountId: Int = AccountMediaListArgs(savedStateHandle).accountId
    private val accountMediaType: Int = AccountMediaListArgs(savedStateHandle).accountMediaType

    val configStream: StateFlow<TMDBConfig> = repository.configStream
        .stateIn(
            scope = viewModelScope,
            initialValue = TMDBConfig(),
            started = SharingStarted.WhileSubscribed(5_000),
        )

    val accountMoviePagingSource = Pager(config = PagingConfig(pageSize = 1, initialLoadSize = 1)) {
        repository.getAccountMediaListsPagingSource(accountId, MediaType.MOVIE, accountMediaType)
    }.flow.cachedIn(viewModelScope)

    val accountTVPagingSource = Pager(config = PagingConfig(pageSize = 1, initialLoadSize = 1)) {
        repository.getAccountMediaListsPagingSource(accountId, MediaType.TV, accountMediaType)
    }.flow.cachedIn(viewModelScope)
}