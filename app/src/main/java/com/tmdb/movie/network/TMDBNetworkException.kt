package com.tmdb.movie.network

import java.io.IOException

class TMDBNetworkException(httpCode: Int, errorCode: Int, errorMessage: String?) : IOException(errorMessage)