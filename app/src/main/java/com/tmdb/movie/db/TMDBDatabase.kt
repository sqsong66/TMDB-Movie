package com.tmdb.movie.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tmdb.movie.data.HomePopularMovie
import com.tmdb.movie.data.SearchHistory

@Database(entities = [SearchHistory::class, HomePopularMovie::class], version = 1)
abstract class TMDBDatabase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao

    abstract fun popularMovieDao(): PopularMovieDao

    companion object {
        const val DATABASE_NAME = "tmdb_db"
    }
}