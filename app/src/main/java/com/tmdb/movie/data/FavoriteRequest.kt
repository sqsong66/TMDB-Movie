package com.tmdb.movie.data

import com.google.gson.annotations.SerializedName

data class FavoriteRequest(
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("media_id")
    val mediaId: Int,
    @SerializedName("favorite")
    val favorite: Boolean
)

data class FavoriteParam(
    @SerializedName("account_id")
    val accountId: Int,
    @SerializedName("session_id")
    val sessionId: String,
    @SerializedName("favorite")
    val favorite: Boolean
)

data class WatchlistRequest(
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("media_id")
    val mediaId: Int,
    @SerializedName("watchlist")
    val watchlist: Boolean
)