package com.tmdb.movie.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: TMDBDispatchers)

enum class TMDBDispatchers {
    Default,
    IO,
}