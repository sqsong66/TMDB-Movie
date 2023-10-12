package com.tmdb.movie.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tmdb.movie.data.SearchHistory

@Database(entities = [SearchHistory::class], version = 1)
abstract class SearchHistoryDatabase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao

    companion object {
        const val DATABASE_NAME = "search_history_db"
    }
}