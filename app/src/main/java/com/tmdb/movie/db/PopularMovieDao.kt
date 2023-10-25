package com.tmdb.movie.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tmdb.movie.data.HomePopularMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(popularMovie: List<HomePopularMovie>)

    // find fist row
    @Query("SELECT * FROM home_popular_movie LIMIT 1")
    suspend fun getFirstPopularMovie(): HomePopularMovie?

    @Query("SELECT * FROM home_popular_movie")
    fun getPopularMovies(): Flow<List<HomePopularMovie>>

    @Query("DELETE FROM home_popular_movie")
    suspend fun deleteAllPopularMovies()

}