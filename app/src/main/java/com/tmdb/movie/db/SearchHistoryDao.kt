package com.tmdb.movie.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.tmdb.movie.data.SearchHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {

    @Query(value = "SELECT * FROM search_history ORDER BY timestamp DESC")
    fun getSearchHistory(): Flow<List<SearchHistory>>

    @Upsert
    suspend fun insertOrReplaceSearchHistory(searchHistory: SearchHistory)

    @Query("DELETE FROM search_history WHERE timestamp NOT IN (SELECT timestamp FROM search_history ORDER BY timestamp DESC LIMIT 10)")
    suspend fun deleteOldHistory()

}