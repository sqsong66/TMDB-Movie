package com.tmdb.movie.data

import androidx.annotation.IntDef

@IntDef(AccountMediaType.FAVORITE, AccountMediaType.WATCHLIST, AccountMediaType.RATED)
@Retention(AnnotationRetention.SOURCE)
@Target(
    AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.FUNCTION, AnnotationTarget.TYPE
)
annotation class AccountMediaType() {
    companion object {
        const val FAVORITE = 0
        const val WATCHLIST = 1
        const val RATED = 2
    }
}
