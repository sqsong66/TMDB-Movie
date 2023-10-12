package com.tmdb.movie.data

import com.google.gson.annotations.SerializedName


data class SessionData(
    @SerializedName("failure")
    val failure: Boolean = false,
    @SerializedName("status_code")
    val statusCode: Int = 0,
    @SerializedName("status_message")
    val statusMessage: String? = null,
    @SerializedName("success")
    val success: Boolean = false,
    @SerializedName("session_id")
    val sessionId: String? = null,
)

data class Session(
    @SerializedName("session_id")
    val sessionId: String
)