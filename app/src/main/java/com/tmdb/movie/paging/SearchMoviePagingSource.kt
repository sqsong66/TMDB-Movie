package com.tmdb.movie.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tmdb.movie.data.SearchItem
import com.tmdb.movie.network.ApiService
import javax.inject.Inject

class SearchMoviePagingSource @Inject constructor(
    private val apiService: ApiService,
    private val query: String
) : PagingSource<Int, SearchItem>() {

    override fun getRefreshKey(state: PagingState<Int, SearchItem>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchItem> {
        return try {
            val nextPage: Int = params.key ?: 1
            val data = apiService.search(query, nextPage)
            val totalPage = data.totalPages
            val dataList: List<SearchItem> = data.results ?: emptyList()
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