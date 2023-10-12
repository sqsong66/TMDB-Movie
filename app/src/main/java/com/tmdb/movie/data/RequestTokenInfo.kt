package com.tmdb.movie.data

import com.google.gson.annotations.SerializedName

data class RequestTokenInfo(
    @SerializedName("expires_at")
    val expiresAt: String? = null,
    @SerializedName("request_token")
    val requestToken: String? = null,
    @SerializedName("success")
    val success: Boolean = false
)

data class RequestToken(
    @SerializedName("request_token")
    val requestToken: String? = null,
)