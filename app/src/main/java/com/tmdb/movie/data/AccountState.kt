package com.tmdb.movie.data

import com.google.gson.annotations.SerializedName


data class AccountState(
    @SerializedName("favorite")
    val favorite: Boolean = false,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("rated")
    val rated: Any = Any(),
    @SerializedName("watchlist")
    val watchlist: Boolean = false
) {
    
}