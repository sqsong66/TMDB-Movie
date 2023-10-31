package com.tmdb.movie.ui.tv

import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.tmdb.movie.data.SeasonDetailParam
import com.tmdb.movie.data.SeasonInfo

internal const val seasonInfoArg = "seasonInfo"
private const val tvSeasonListNavigationRoute = "tv_season_list_navigation_route"

internal const val seasonDetailArg = "seasonDetail"
private const val tvSeasonDetailNavigationRoute = "tv_season_detail_navigation_route"

internal const val episodeDetailArg = "episodeDetail"
private const val tvEpisodeDetailNavigationRoute = "tv_episode_detail_navigation_route"

internal class SeasonArgs(val param: SeasonDetailParam?) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        savedStateHandle.get<SeasonDetailParam>(seasonDetailArg)
    )
}

internal class SeasonInfoArgs(val seasonInfo: SeasonInfo?) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        savedStateHandle.get<SeasonInfo>(seasonInfoArg)
    )
}

internal class SeasonInfoNavType : NavType<SeasonInfo>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): SeasonInfo? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): SeasonInfo {
        return Gson().fromJson(value, SeasonInfo::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: SeasonInfo) {
        bundle.putParcelable(key, value)
    }
}

internal class SeasonDetailNavType : NavType<SeasonDetailParam>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): SeasonDetailParam? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): SeasonDetailParam {
        return Gson().fromJson(value, SeasonDetailParam::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: SeasonDetailParam) {
        bundle.putParcelable(key, value)
    }
}


/************** Season List ****************/
fun NavController.navigateToTVSeasonList(seasonInfo: SeasonInfo) {
    val seasonsInfoJson = Uri.encode(Gson().toJson(seasonInfo))
    this.navigate("$tvSeasonListNavigationRoute/$seasonsInfoJson")
}

fun NavGraphBuilder.tvSeasonListScreen(
    onBackClick: (Boolean) -> Unit,
    toSeasonDetail: (SeasonDetailParam) -> Unit,
) {
    composable(
        route = "$tvSeasonListNavigationRoute/{$seasonInfoArg}",
        arguments = listOf(
            navArgument(seasonInfoArg) {
                type = SeasonInfoNavType()
            },
        )
    ) {
        TVSeasonListRoute(
            onBackClick = onBackClick,
            toSeasonDetail = toSeasonDetail,
        )
    }
}

/************** Season Detail ****************/
fun NavController.navigateToSeasonDetail(param: SeasonDetailParam) {
    val seasonDetailParamJson = Uri.encode(Gson().toJson(param))
    this.navigate("$tvSeasonDetailNavigationRoute/$seasonDetailParamJson")
}

fun NavGraphBuilder.tvSeasonDetailScreen(
    onBackClick: (Boolean) -> Unit,
    toEpisodeDetail: (SeasonDetailParam) -> Unit,
) {
    composable(
        route = "$tvSeasonDetailNavigationRoute/{$seasonDetailArg}",
        arguments = listOf(
            navArgument(seasonDetailArg) {
                type = SeasonDetailNavType()
            },
        )
    ) {
        val seasonParam = it.arguments?.getParcelable<SeasonDetailParam>(seasonDetailArg)

        TVSeasonDetailRoute(
            tvName = seasonParam?.tvName ?: "",
            onBackClick = onBackClick,
            toEpisodeDetail = { seasonNumber, episodeNumber ->
                toEpisodeDetail(SeasonDetailParam(
                    tvId = seasonParam?.tvId ?: 0,
                    tvName = seasonParam?.tvName ?: "",
                    seasonNumber = seasonNumber,
                    episodeNumber = episodeNumber,
                ))
            }
        )
    }
}

/************** Episode Detail ****************/
fun NavController.navigateToEpisodeDetail(param: SeasonDetailParam) {
    val seasonDetailParamJson = Uri.encode(Gson().toJson(param))
    this.navigate("$tvEpisodeDetailNavigationRoute/$seasonDetailParamJson")
}

fun NavGraphBuilder.tvEpisodeDetailScreen(
    onBackClick: (Boolean) -> Unit,
) {
    composable(
        route = "$tvEpisodeDetailNavigationRoute/{$seasonDetailArg}",
        arguments = listOf(
            navArgument(seasonDetailArg) {
                type = SeasonDetailNavType()
            },
        )
    ) {
        TVEpisodeDetailRoute(
            onBackClick = onBackClick,
        )
    }
}
