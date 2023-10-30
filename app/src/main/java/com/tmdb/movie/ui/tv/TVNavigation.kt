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
import com.tmdb.movie.data.SeasonInfo


internal const val tvBackdropPathArg = "tvBackdropPath"
internal const val tvIdArg = "tvId"
internal const val tvEpisodeNumArg = "tvEpisodeNum"

internal const val seasonInfoArg = "seasonInfo"
private const val tvSeasonListNavigationRoute = "tv_season_list_navigation_route"

internal class TVArgs(
    val tvId: Int,
    val tvEpisodeNum: Int,
) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        savedStateHandle.get<Int>(tvIdArg) ?: 0,
        savedStateHandle.get<Int>(tvEpisodeNumArg) ?: 0,
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

fun NavController.navigateToTVSeasonList(seasonInfo: SeasonInfo) {
    val seasonsInfoJson = Uri.encode(Gson().toJson(seasonInfo))
    this.navigate("$tvSeasonListNavigationRoute/$seasonsInfoJson")
}

fun NavGraphBuilder.tvSeasonListScreen(
    onBackClick: (Boolean) -> Unit,
    toTVEpisodeDetail: (Int, String, Int, Int) -> Unit,
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
        )
    }
}


//fun NavController.navigateToTVEpisodeDetail(
//    tvId: Int,
//    tvBackdropPath: String,
//    tvSeasonNum: Int,
//    tvEpisodeNum: Int,
//) {
//    this.navigate("$tvSeasonNavigationRoute/$tvBackdropPath/$tvId/$tvSeasonNum/$tvEpisodeNum")
//}
//
//fun NavGraphBuilder.tvEpisodeDetailScreen(
//    onBackClick: (Boolean) -> Unit,
//) {
//    composable(
//        route = "$tvSeasonNavigationRoute/{$tvBackdropPathArg}/{$tvIdArg}/{$tvSeasonsArg}/{$tvEpisodeNumArg}",
//        arguments = listOf(
//            navArgument(tvBackdropPathArg) {
//                type = NavType.StringType
//            },
//            navArgument(tvIdArg) {
//                type = NavType.IntType
//            },
//            navArgument(tvSeasonsArg) {
//                type = NavType.IntType
//            },
//            navArgument(tvEpisodeNumArg) {
//                type = NavType.IntType
//            },
//        )
//    ) {
//
//    }
//}