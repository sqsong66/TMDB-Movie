package com.tmdb.movie.data

import android.content.Context
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.tmdb.movie.R
import com.tmdb.movie.ext.formatWithCommasAndDecimals
import com.tmdb.movie.utils.formatMinuteToHM
import com.tmdb.movie.utils.niceDate
import kotlinx.parcelize.Parcelize
import java.text.DateFormat

data class MovieDetails(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any? = null,
    @SerializedName("budget")
    val budget: Int = 0,
    @SerializedName("created_by")
    val createdBy: List<CreatedBy>? = null,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>? = null,
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @SerializedName("genres")
    val genres: List<Genre>? = null,
    @SerializedName("homepage")
    val homepage: String? = null,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("in_production")
    val inProduction: Boolean = false,
    @SerializedName("imdb_id")
    val imdbId: String? = null,
    @SerializedName("languages")
    val languages: List<String>? = null,
    @SerializedName("last_air_date")
    val lastAirDate: String? = null,
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: EpisodeToAir? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("networks")
    val networks: List<Network>? = null,
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: EpisodeToAir? = null,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int = 0,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int = 0,
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
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>? = null,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry?>? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("revenue")
    val revenue: Double = 0.0,
    @SerializedName("runtime")
    val runtime: Int = 0,
    @SerializedName("seasons")
    val seasons: List<Season>? = null,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("tagline")
    val tagline: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("video")
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Float = 0.0f,
    @SerializedName("vote_count")
    val voteCount: Int = 0,
    @SerializedName("credits")
    val credits: Credits? = null,
    @SerializedName("videos")
    val videos: Videos? = null,
) {
    fun getMovieName(@MediaType mediaType: Int): String? {
        return if (mediaType == MediaType.MOVIE) {
            title ?: originalTitle
        } else {
            name ?: originalName
        }
    }

    fun getNiceDate(@MediaType mediaType: Int, isFormatShort: Boolean): String? {
        return niceDate(
            if (mediaType == MediaType.MOVIE) releaseDate else firstAirDate,
            dateFormat = if (isFormatShort) DateFormat.SHORT else DateFormat.MEDIUM
        )
    }

    fun getMovieOverview(context: Context): String {
        return if (overview.isNullOrEmpty()) {
            context.getString(R.string.key_no_overview)
        } else {
            overview.trim()
        }
    }

    fun getReleaseYear(@MediaType mediaType: Int): String {
        val date = if (mediaType == MediaType.MOVIE) releaseDate else firstAirDate
        if (date.isNullOrEmpty() || date.length < 4) return ""
        return date.substring(0, 4)
    }

    fun getFormatRevenue(): String {
        return revenue.formatWithCommasAndDecimals(2)
    }
}

data class Genre(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String? = null
)

data class ProductionCompany(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("logo_path")
    val logoPath: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("origin_country")
    val originCountry: String? = null
)

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso31661: String? = null,
    @SerializedName("name")
    val name: String? = null
)

data class SpokenLanguage(
    @SerializedName("english_name")
    val englishName: String? = null,
    @SerializedName("iso_639_1")
    val iso6391: String? = null,
    @SerializedName("name")
    val name: String? = null
)

data class Credits(
    @SerializedName("cast")
    val cast: List<Cast>? = null,
    @SerializedName("crew")
    val crew: List<Crew>? = null,
)

data class Videos(
    @SerializedName("results")
    val results: List<Video>? = null,
)

data class CreatedBy(
    @SerializedName("credit_id")
    val creditId: String? = null,
    @SerializedName("gender")
    val gender: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("profile_path")
    val profilePath: String? = null
)

data class EpisodeToAir(
    @SerializedName("air_date")
    val airDate: String? = null,
    @SerializedName("episode_number")
    val episodeNumber: Int = 0,
    @SerializedName("episode_type")
    val episodeType: String? = null,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("production_code")
    val productionCode: String? = null,
    @SerializedName("runtime")
    val runtime: Int = 0,
    @SerializedName("season_number")
    val seasonNumber: Int = 0,
    @SerializedName("show_id")
    val showId: Int = 0,
    @SerializedName("still_path")
    val stillPath: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Float = 0.0f,
    @SerializedName("vote_count")
    val voteCount: Int = 0
) {
    fun isLastEpisode(): Boolean {
        return episodeType == "finale"
    }

    fun getSeasonOverview(context: Context): String {
        return if (overview.isNullOrEmpty()) {
            context.getString(R.string.key_no_overview)
        } else {
            overview.trim()
        }
    }

    fun niceAirDate(): String {
        return niceDate(airDate, format = "yyyy-MM-dd", dateFormat = DateFormat.MEDIUM) ?: "--"
    }
}

data class Network(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("logo_path")
    val logoPath: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("origin_country")
    val originCountry: String? = null
)

@Parcelize
data class Season(
    @SerializedName("air_date")
    val airDate: String? = null,
    @SerializedName("episode_count")
    val episodeCount: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("season_number")
    val seasonNumber: Int = 0,
    @SerializedName("vote_average")
    val voteAverage: Float = 0.0f,
    @SerializedName("episodes")
    val episodes: List<Episode>? = null
) : Parcelable {

    fun getSeasonOverview(context: Context): String {
        return if (overview.isNullOrEmpty()) {
            context.getString(R.string.key_no_overview)
        } else {
            overview.trim()
        }
    }

    fun niceAirDate(): String {
        return niceDate(airDate, format = "yyyy-MM-dd", dateFormat = DateFormat.MEDIUM) ?: "--"
    }

    override fun toString(): String {
        return "Season(airDate=$airDate, episodeCount=$episodeCount, id=$id, name=$name, overview=$overview, posterPath=$posterPath, seasonNumber=$seasonNumber, voteAverage=$voteAverage)"
    }

    fun getTVAirYear(): String {
        if (airDate.isNullOrEmpty() || airDate.length < 4) return "--"
        return airDate.substring(0, 4)
    }

}

@Parcelize
data class Episode(
    @SerializedName("air_date")
    val airDate: String? = null,
    @SerializedName("episode_number")
    val episodeNumber: Int = 0,
    @SerializedName("episode_type")
    val episodeType: String? = null,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("production_code")
    val productionCode: String? = null,
    @SerializedName("runtime")
    val runtime: Int = 0,
    @SerializedName("season_number")
    val seasonNumber: Int= 0,
    @SerializedName("show_id")
    val showId: Int = 0,
    @SerializedName("still_path")
    val stillPath: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Float = 0f,
    @SerializedName("vote_count")
    val voteCount: Int = 0,
    @SerializedName("crew")
    val crew: List<Crew>? = null,
    @SerializedName("guest_stars")
    val guestStars: List<Cast>? = null,
    @SerializedName("images")
    val images: StillImage? = null,
) : Parcelable {
    fun niceAirDate(): String {
        return niceDate(airDate, format = "yyyy-MM-dd", dateFormat = DateFormat.MEDIUM) ?: "--"
    }

    fun getDuration(): String {
        return formatMinuteToHM(runtime)
    }

    fun getEpisodeOverview(context: Context): String {
        return if (overview.isNullOrEmpty()) {
            context.getString(R.string.key_no_overview)
        } else {
            overview.trim()
        }
    }
}