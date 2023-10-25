package com.tmdb.movie.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tmdb.movie.data.MediaList
import com.tmdb.movie.network.ApiService
import javax.inject.Inject

class MyMediaListPagingSource @Inject constructor(
    private val accountId: Int,
    private val apiService: ApiService,
) : PagingSource<Int, MediaList>() {

    override fun getRefreshKey(state: PagingState<Int, MediaList>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MediaList> {
        return try {
            val nextPage: Int = params.key ?: 1
            val data = apiService.getAccountMediaLists(accountId)
            val totalPage = data.totalPages
            val dataList: List<MediaList> = data.results ?: emptyList()
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