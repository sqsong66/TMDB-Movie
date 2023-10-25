package com.tmdb.movie.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tmdb.movie.data.MediaItem
import com.tmdb.movie.network.ApiService
import javax.inject.Inject

class DiscoveryMoviePagingSource @Inject constructor(
    private val apiService: ApiService,
    private val discoveryType: Int = 0
) : PagingSource<Int, MediaItem>() {

    override fun getRefreshKey(state: PagingState<Int, MediaItem>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MediaItem> {
        return try {
            val nextPage: Int = params.key ?: 1
            val data = if (discoveryType == 0) apiService.getDiscoveryMovies(nextPage) else apiService.getDiscoveryTV(nextPage)
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