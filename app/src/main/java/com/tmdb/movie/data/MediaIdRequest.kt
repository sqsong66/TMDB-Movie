package com.tmdb.movie.data

import com.google.gson.annotations.SerializedName

data class MediaIdRequest(
    @SerializedName("media_id")
    val mediaId: Int,
    @SerializedName("media_type")
    val mediaType: String,
)