package com.tmdb.movie.data

import androidx.annotation.IntDef

@IntDef(MediaType.MOVIE, MediaType.TV, MediaType.PERSON)
@Retention(AnnotationRetention.SOURCE)
@Target(
    AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.FUNCTION, AnnotationTarget.TYPE
)
annotation class MediaType {
    companion object {
        const val MOVIE = 0
        const val TV = 1
        const val PERSON = 2
    }
}