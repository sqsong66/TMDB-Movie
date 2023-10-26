package com.tmdb.movie.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tmdb.movie.data.AccountMediaType
import com.tmdb.movie.data.MediaItem
import com.tmdb.movie.data.MediaType
import com.tmdb.movie.network.ApiService
import kotlinx.coroutines.delay
import javax.inject.Inject

class AccountMediaListsPagingSource @Inject constructor(
    private val apiService: ApiService,
    private val accountId: Int,
    @MediaType private val mediaType: Int = 0,
    @AccountMediaType private val accountMediaType: Int = 0
) : PagingSource<Int, MediaItem>() {

    override fun getRefreshKey(state: PagingState<Int, MediaItem>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MediaItem> {
        return try {
            val nextPage: Int = params.key ?: 1
            delay(1000)
            val data = when (accountMediaType) {
                AccountMediaType.FAVORITE -> {
                    if (mediaType == MediaType.MOVIE) {
                        apiService.getFavoriteMovies(accountId, nextPage)
                    } else {
                        apiService.getFavoriteTV(accountId, nextPage)
                    }
                }

                AccountMediaType.WATCHLIST -> {
                    if (mediaType == MediaType.MOVIE) {
                        apiService.getWatchlistMovies(accountId, nextPage)
                    } else {
                        apiService.getWatchlistTV(accountId, nextPage)
                    }
                }

                else -> {
                    if (mediaType == MediaType.MOVIE) {
                        apiService.getRatedMovies(accountId, nextPage)
                    } else {
                        apiService.getRatedTV(accountId, nextPage)
                    }
                }
            }
            val totalPage = data.totalPages
            val dataList: List<MediaItem> = data.results ?: emptyList()
            LoadResult.Page(
                data = dataList,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (nextPage == totalPage) null else nextPage + 1
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            LoadResult.Error(ex)
        }
    }
}