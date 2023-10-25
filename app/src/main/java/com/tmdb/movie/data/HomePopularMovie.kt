package com.tmdb.movie.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home_popular_movie")
data class HomePopularMovie(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,
    @ColumnInfo(name = "updated_at")
    val updatedAt: String?,
) {
    override fun toString(): String {
        return "HomePopularMovie(id=$id, title=$title, backdropPath=$backdropPath, updatedAt=$updatedAt)"
    }
}