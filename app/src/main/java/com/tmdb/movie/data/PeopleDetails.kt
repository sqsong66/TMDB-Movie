package com.tmdb.movie.data

import android.content.Context
import com.google.gson.annotations.SerializedName
import com.tmdb.movie.R
import com.tmdb.movie.ext.formatWithCommasAndDecimals
import com.tmdb.movie.utils.niceDate
import java.text.DateFormat


data class PeopleDetails(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("also_known_as")
    val alsoKnownAs: List<String>? = null,
    @SerializedName("biography")
    val biography: String? = null,
    @SerializedName("birthday")
    val birthday: String? = null,
    @SerializedName("deathday")
    val deathday: String? = null,
    @SerializedName("gender")
    val gender: Int = 0,
    @SerializedName("homepage")
    val homepage: String? = null,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("images")
    val images: Profile? = null,
    @SerializedName("imdb_id")
    val imdbId: String? = null,
    @SerializedName("known_for_department")
    val knownForDepartment: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("place_of_birth")
    val placeOfBirth: String? = null,
    @SerializedName("popularity")
    val popularity: Float = 0.0f,
    @SerializedName("profile_path")
    val profilePath: String? = null
) {

    fun getGenderText(context: Context): String {
        return when (gender) {
            1 -> context.getString(R.string.key_female)
            2 -> context.getString(R.string.key_male)
            else -> context.getString(R.string.key_unknown)
        }
    }

    fun getPrettyBirthday(): String? {
        return niceDate(birthday, dateFormat = DateFormat.MEDIUM)
    }

    fun getPopularText(): String {
        return popularity.formatWithCommasAndDecimals(2)
    }

    fun getPeopleBiography(context: Context): String {
        return if (biography.isNullOrEmpty()) {
            context.getString(R.string.key_no_biography)
        } else {
            biography.trim()
        }
    }
}

data class Profile(
    @SerializedName("profiles")
    val profiles: List<MovieImage>? = null
)
