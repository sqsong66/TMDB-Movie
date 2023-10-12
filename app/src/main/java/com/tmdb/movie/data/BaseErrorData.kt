package com.tmdb.movie.data

import com.google.gson.annotations.SerializedName

data class BaseErrorData(
    @SerializedName("status_code")
    val statusCode: Int = 0,
    @SerializedName("status_message")
    val statusMessage: String? = null,
    @SerializedName("success")
    val success: Boolean = false
)