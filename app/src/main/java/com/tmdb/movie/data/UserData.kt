package com.tmdb.movie.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    @SerializedName("avatar")
    val avatar: Avatar? = null,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("include_adult")
    val includeAdult: Boolean = false,
    @SerializedName("iso_3166_1")
    val iso31661: String? = null,
    @SerializedName("iso_639_1")
    val iso6391: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("session_id")
    val sessionId: String? = null
)

@Serializable
data class Avatar(
    @SerializedName("gravatar")
    val gravatar: Gravatar? = null,
    @SerializedName("tmdb")
    val tmdb: Tmdb? = null
)

@Serializable
data class Gravatar(
    @SerializedName("hash")
    val hash: String? = null
)

@Serializable
data class Tmdb(
    @SerializedName("avatar_path")
    val avatarPath: String? = null
)