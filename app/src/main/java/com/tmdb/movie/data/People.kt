package com.tmdb.movie.data

import com.google.gson.annotations.SerializedName


data class People(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("gender")
    val gender: Int = 2,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("known_for")
    val knownFor: List<KnownFor>? = null,
    @SerializedName("known_for_department")
    val knownForDepartment: String? = null,
    @SerializedName("media_type")
    val mediaType: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("original_name")
    val originalName: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("profile_path")
    val profilePath: String? = null
)

data class KnownFor(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("media_type")
    val mediaType: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Float = 0.0f,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Float = 0.0f,
    @SerializedName("vote_count")
    val voteCount: Int = 0
)