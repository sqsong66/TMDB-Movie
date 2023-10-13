package com.tmdb.movie.network

import java.io.IOException

class TMDBNetworkException(val httpCode: Int, val errorCode: Int, val errorMessage: String?) : IOException(errorMessage)