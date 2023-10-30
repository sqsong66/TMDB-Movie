package com.tmdb.movie.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeasonInfo(
    val tvId: Int,
    val tvName: String,
    val airDate: String,
    val posterPath: String,
    val backdropPath: String,
    val seasons: List<Season>,
) : Parcelable {
    override fun toString(): String {
        return "SeasonInfo(tvId=$tvId, tvName='$tvName', airDate='$airDate', posterPath='$posterPath', backdropPath='$backdropPath', seasons=$seasons)"
    }
}

@Parcelize
data class SeasonDetailParam(
    val tvId: Int,
    val tvName: String,
    val seasonNumber: Int,
    val episodeNumber: Int = 0,
) : Parcelable {
    override fun toString(): String {
        return "SeasonDetailParam(tvId=$tvId, tvName='$tvName', seasonNumber=$seasonNumber, episodeNumber=$episodeNumber)"
    }
}