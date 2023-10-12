package com.tmdb.movie.data

import android.content.Context
import android.util.Log
import com.google.gson.annotations.SerializedName
import com.tmdb.movie.R
import com.tmdb.movie.utils.formatToMonthDay


data class SortedPeopleCredits(
//    val anonymousCasts: List<PeopleCast>? = null,
    val sortedCasts: List<List<PeopleCast>>? = null,
    val knownForCredits: List<PeopleCast>? = null,
    val sortedCrews: List<PeopleCrew>? = null,
)

data class PeopleCredits(
    @SerializedName("cast")
    val cast: List<PeopleCast>? = null,
    @SerializedName("crew")
    val crew: List<PeopleCrew>? = null,
    @SerializedName("id")
    val id: Int = 0
) {
    fun toSortedPeopleCredits(): SortedPeopleCredits {
        val sortedDateCasts = mutableListOf<List<PeopleCast>>()
        val knownForCredits = getKnowForCredits()
        val sortedCasts = cast?.sortedByDescending { cast -> cast.getCastDate() }
        sortedCasts?.let { casts ->
            val (cast1, cast2) = casts.partition { cast ->
                cast.getCastDate().isEmpty()
            }

            val tempMap = mutableMapOf<String, MutableList<PeopleCast>>()
            cast2.forEach { cast ->
                val year = cast.getYear()
                if (tempMap.containsKey(year)) {
                    val tempList = tempMap[year]
                    tempList?.add(cast)
                } else {
                    tempMap[year] = mutableListOf(cast)
                }
            }
            tempMap.toSortedMap(comparator = { o1, o2 ->
                o2.compareTo(o1)
            }).forEach {
                sortedDateCasts.add(it.value)
            }

            if (cast1.isNotEmpty()) sortedDateCasts.add(cast1)
        }
        sortedDateCasts.forEach { casts ->
            casts.forEach { c ->
                Log.w("sqsong", "sortedDateCasts date: ${c.getCastDate()}, name: ${c.getCastName()}, back: ${c.backdropPath}")
            }
        }

        val sortedCrews = crew?.sortedByDescending { crew -> crew.getCrewDate() }
        return SortedPeopleCredits(sortedDateCasts, knownForCredits, sortedCrews ?: emptyList())
    }

    private fun getKnowForCredits(): List<PeopleCast>? {
        val sortedCasts = cast?.sortedByDescending { cast -> cast.voteCount }
        val size = (sortedCasts?.size ?: 0).coerceAtMost(8)
        return sortedCasts?.subList(0, size)
    }
}

data class PeopleCast(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("character")
    val character: String? = null,
    @SerializedName("credit_id")
    val creditId: String? = null,
    @SerializedName("episode_count")
    val episodeCount: Int = 0,
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("media_type")
    val mediaType: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("order")
    val order: Int = 0,
    @SerializedName("origin_country")
    val originCountry: List<String>? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_name")
    val originalName: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Float = 0.0f,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Float = 0.0f,
    @SerializedName("vote_count")
    val voteCount: Int = 0
) {
    fun getCastDate(): String {
        return if (mediaType == "tv") {
            firstAirDate ?: ""
        } else {
            releaseDate ?: ""
        }
    }

    fun getDate(): String {
        return if (mediaType == "tv") {
            firstAirDate ?: ""
        } else {
            releaseDate ?: ""
        }
    }

    @MediaType
    fun getMovieType(): Int {
        return if (mediaType == "tv") {
            MediaType.TV
        } else {
            MediaType.MOVIE
        }
    }

    fun getNiceMonthDay(): String {
        val date = getDate()
        if (date.isEmpty()) return ""
        return formatToMonthDay(timeStr = date)
    }

    fun getYear(): String {
        return if (mediaType == "tv") {
            firstAirDate?.substring(0, 4) ?: ""
        } else {
            releaseDate?.substring(0, 4) ?: ""
        }
    }

    fun getSplitYear(): String {
        val date = if (mediaType == "tv") {
            firstAirDate
        } else {
            releaseDate
        }
        if (date.isNullOrEmpty()) return ""
        return StringBuilder(date.substring(0, 2))
            .append("\n ")
            .append(date.substring(2, 4))
            .toString()
    }

    fun getCastName(): String {
        return if (mediaType == "tv") {
            name ?: originalName ?: ""
        } else {
            title ?: originalTitle ?: ""
        }
    }

    fun getActCharacter(context: Context): String {
        return if (mediaType == "tv") {
            String.format(context.getString(R.string.key_act_as_1), episodeCount, character)
        } else {
            String.format(context.getString(R.string.key_act_as), character)
        }
    }
}

data class PeopleCrew(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("credit_id")
    val creditId: String? = null,
    @SerializedName("department")
    val department: String? = null,
    @SerializedName("episode_count")
    val episodeCount: Int = 0,
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("job")
    val job: String? = null,
    @SerializedName("media_type")
    val mediaType: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("origin_country")
    val originCountry: List<String?>? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_name")
    val originalName: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Float = 0.0f,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Float = 0.0f,
    @SerializedName("vote_count")
    val voteCount: Int = 0
) {
    fun getCrewDate(): String {
        return if (mediaType == "tv") {
            firstAirDate ?: ""
        } else {
            releaseDate ?: ""
        }
    }

    fun getYear(): String {
        return if (mediaType == "tv") {
            firstAirDate?.substring(0, 4) ?: ""
        } else {
            releaseDate?.substring(0, 4) ?: ""
        }
    }

    fun getCrewName(): String {
        return if (mediaType == "tv") {
            name ?: originalName ?: ""
        } else {
            title ?: originalTitle ?: ""
        }
    }
}