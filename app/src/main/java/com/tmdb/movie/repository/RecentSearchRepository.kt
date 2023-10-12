package com.tmdb.movie.repository

import com.tmdb.movie.common.Dispatcher
import com.tmdb.movie.common.TMDBDispatchers.IO
import com.tmdb.movie.data.SearchHistory
import com.tmdb.movie.db.SearchHistoryDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecentSearchRepository @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : ISearchRepository {

    override fun getRecentSearchQueries(): Flow<List<SearchHistory>> = searchHistoryDao.getSearchHistory().map {
        searchHistoryDao.deleteOldHistory()
        it
    }

    override suspend fun insertOrReplaceRecentSearch(searchQuery: String) {
       withContext(ioDispatcher) {
           searchHistoryDao.insertOrReplaceSearchHistory(
               SearchHistory(
                   query = searchQuery,
                   timestamp = System.currentTimeMillis(),
               )
           )
       }
    }

}