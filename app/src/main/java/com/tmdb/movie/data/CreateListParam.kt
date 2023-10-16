package com.tmdb.movie.data

import com.google.gson.annotations.SerializedName
import com.tmdb.movie.utils.getLanguage

data class CreateListParam(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("language")
    val language: String = getLanguage()
)