package com.tmdb.movie.data

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.google.gson.annotations.SerializedName
import com.tmdb.movie.R

data class SearchItem(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("media_type")
    val mediaType: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("original_name")
    val originalName: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("profile_path")
    val profilePath: String? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
) {
    fun getSearchName(): String? {
        return if (getMovieType() == MediaType.MOVIE) {
            title ?: originalTitle
        } else {
            name ?: originalName
        }
    }

    fun getAnnotatedName(query: String, color: Color): AnnotatedString {
        val resultName = getSearchName()
        return if (resultName.isNullOrEmpty()) {
            AnnotatedString("")
        } else {
            buildAnnotatedString {
                var currentIndex = 0
                while (true) {
                    val startIndex = resultName.indexOf(query, currentIndex, ignoreCase = true)
                    if (startIndex == -1) {
                        append(resultName.substring(currentIndex))
                        break
                    }
                    val endIndex = startIndex + query.length
                    append(resultName.substring(currentIndex, startIndex))
                    withStyle(style = SpanStyle(color = color)) {
                        append(resultName.substring(startIndex, endIndex))
                    }
                    currentIndex = endIndex
                }
            }
        }
    }


    fun getMovieType(): @MediaType Int {
        return when (mediaType) {
            "movie" -> {
                MediaType.MOVIE
            }

            "tv" -> {
                MediaType.TV
            }

            else -> {
                MediaType.PERSON
            }
        }
    }

    fun getSearchOverview(context: Context): String {
        return if (overview.isNullOrEmpty()) {
            context.getString(R.string.key_no_overview)
        } else {
            overview.trim()
        }
    }

    fun getAnnotatedOverview(context: Context, query: String, color: Color): AnnotatedString {
        return if (overview.isNullOrEmpty()) {
            AnnotatedString(context.getString(R.string.key_no_overview))
        } else {
            val overviewText = overview.trim()
            buildAnnotatedString {
                var currentIndex = 0
                while (true) {
                    val startIndex = overviewText.indexOf(query, currentIndex, ignoreCase = true)
                    if (startIndex == -1) {
                        append(overviewText.substring(currentIndex))
                        break
                    }
                    val endIndex = startIndex + query.length
                    append(overviewText.substring(currentIndex, startIndex))
                    withStyle(style = SpanStyle(color = color)) {
                        append(overviewText.substring(startIndex, endIndex))
                    }
                    currentIndex = endIndex
                }
            }
        }
    }

    fun getImagePath(): String? {
        return when (getMovieType()) {
            MediaType.MOVIE -> {
                posterPath
            }

            MediaType.TV -> {
                posterPath
            }

            else -> {
                profilePath
            }
        }
    }
}