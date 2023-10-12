package com.tmdb.movie.ext

private fun <T> String?.isNotNullOrEmpty(block: () -> T): T? {
    return if (!this.isNullOrEmpty()) {
        block()
    } else {
        null
    }
}