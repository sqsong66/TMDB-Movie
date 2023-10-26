package com.tmdb.movie.data

import android.content.Context
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.tmdb.movie.R
import com.tmdb.movie.utils.formatLongToString
import com.tmdb.movie.utils.niceDate
import kotlinx.parcelize.Parcelize
import java.text.DateFormat

@Parcelize
data class MediaItem(
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
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("original_name")
    val originalName: String? = null,
    @SerializedName("video")
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Float = 0.0f,
    @SerializedName("vote_count")
    val voteCount: Int = 0,
    @SerializedName("character")
    val character: String? = null,
    @SerializedName("episode_count")
    val episodeCount: Int = 0,
) : Parcelable {

    fun getMovieName(@MediaType mediaType: Int): String {
        return if (mediaType == MediaType.MOVIE) {
            title ?: originalTitle ?: ""
        } else {
            name ?: originalName ?: ""
        }
    }

    fun getMovieName(): String {
        return if (getMediaType() == MediaType.MOVIE) {
            title ?: originalTitle ?: ""
        } else {
            name ?: originalName ?: ""
        }
    }

    fun getNiceDate(@MediaType mediaType: Int, isFormatShort: Boolean): String? {
        return niceDate(
            if (mediaType == MediaType.MOVIE) releaseDate else firstAirDate,
            dateFormat = if (isFormatShort) DateFormat.SHORT else DateFormat.MEDIUM
        )
    }

    fun getMovieOverview(context: Context): String {
        return if (overview.isNullOrEmpty()) {
            context.getString(R.string.key_no_overview)
        } else {
            overview.trim()
        }
    }

    fun toHomePopularMovie(): HomePopularMovie {
        return HomePopularMovie(
            id = id,
            title = title,
            backdropPath = backdropPath,
            updatedAt = formatLongToString(System.currentTimeMillis())
        )
    }

    fun getMediaType(): @MediaType Int {
        return when (mediaType) {
            "movie" -> MediaType.MOVIE
            "tv" -> MediaType.TV
            else -> MediaType.PERSON
        }
    }
}