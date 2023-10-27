package com.tmdb.movie.ui.tv

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

internal const val tvBackdropPathArg = "tvBackdropPath"
internal const val tvIdArg = "tvId"
internal const val tvSeasonNumArg = "tvSeasonNum"
internal const val tvEpisodeNumArg = "tvEpisodeNum"
private const val tvSeasonNavigationRoute = "tv_season_navigation_route"

internal class TVArgs(
    val tvId: Int,
    val tvSeasonNum: Int,
    val tvEpisodeNum: Int,
) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        savedStateHandle.get<Int>(tvIdArg) ?: 0,
        savedStateHandle.get<Int>(tvSeasonNumArg) ?: 0,
        savedStateHandle.get<Int>(tvEpisodeNumArg) ?: 0,
    )
}

fun NavController.navigateToTVSeasonDetail(
    tvId: Int,
    tvBackdropPath: String,
    tvSeasonNum: Int,
) {
    this.navigate("$tvSeasonNavigationRoute/$tvBackdropPath/$tvId/$tvSeasonNum")
}

fun NavGraphBuilder.tvSeasonDetailScreen(
    onBackClick: (Boolean) -> Unit,
    toTVEpisodeDetail: (Int, String, Int, Int) -> Unit,
) {
    composable(
        route = "$tvSeasonNavigationRoute/{$tvBackdropPathArg}/{$tvIdArg}/{$tvSeasonNumArg}",
        arguments = listOf(
            navArgument(tvBackdropPathArg) {
                type = NavType.StringType
            },
            navArgument(tvIdArg) {
                type = NavType.IntType
            },
            navArgument(tvSeasonNumArg) {
                type = NavType.IntType
            },
        )
    ) {
        val backdropPath = it.arguments?.getString(tvBackdropPathArg) ?: ""

    }
}

fun NavController.navigateToTVEpisodeDetail(
    tvId: Int,
    tvBackdropPath: String,
    tvSeasonNum: Int,
    tvEpisodeNum: Int,
) {
    this.navigate("$tvSeasonNavigationRoute/$tvBackdropPath/$tvId/$tvSeasonNum/$tvEpisodeNum")
}

fun NavGraphBuilder.tvEpisodeDetailScreen(
    onBackClick: (Boolean) -> Unit,
) {
    composable(
        route = "$tvSeasonNavigationRoute/{$tvBackdropPathArg}/{$tvIdArg}/{$tvSeasonNumArg}/{$tvEpisodeNumArg}",
        arguments = listOf(
            navArgument(tvBackdropPathArg) {
                type = NavType.StringType
            },
            navArgument(tvIdArg) {
                type = NavType.IntType
            },
            navArgument(tvSeasonNumArg) {
                type = NavType.IntType
            },
            navArgument(tvEpisodeNumArg) {
                type = NavType.IntType
            },
        )
    ) {

    }
}