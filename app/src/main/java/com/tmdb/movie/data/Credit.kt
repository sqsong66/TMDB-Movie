package com.tmdb.movie.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cast(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("cast_id")
    val castId: Int = 0,
    @SerializedName("character")
    val character: String? = null,
    @SerializedName("credit_id")
    val creditId: String? = null,
    @SerializedName("gender")
    val gender: Int = 1,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("known_for_department")
    val knownForDepartment: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("order")
    val order: Int = 0,
    @SerializedName("original_name")
    val originalName: String? = null,
    @SerializedName("popularity")
    val popularity: Float = 0.0f,
    @SerializedName("profile_path")
    val profilePath: String? = null
) : Parcelable

@Parcelize
data class Crew(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("credit_id")
    val creditId: String? = null,
    @SerializedName("department")
    val department: String? = null,
    @SerializedName("gender")
    val gender: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("job")
    val job: String? = null,
    @SerializedName("known_for_department")
    val knownForDepartment: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("original_name")
    val originalName: String? = null,
    @SerializedName("popularity")
    val popularity: Float = 0.0f,
    @SerializedName("profile_path")
    val profilePath: String? = null
) : Parcelable