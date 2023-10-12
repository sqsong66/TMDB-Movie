package com.tmdb.movie.ui.people

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tmdb.movie.data.MediaType

internal const val peopleIdArg = "peopleId"
internal const val peopleTypeArg = "peopleType"
private const val peopleDetailNavigationRoute = "people_detail_navigation_route"

internal class PeopleArgs(val peopleId: Int, val type: Int) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(savedStateHandle.get<Int>(peopleIdArg) ?: 0, savedStateHandle.get<Int>(peopleTypeArg) ?: 0)
}

fun NavController.navigateToPeopleDetail(peopleId: Int, detailType: Int = 0) {
    this.navigate("$peopleDetailNavigationRoute/$peopleId/$detailType") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.peopleDetailScreen(
    onBackClick: (Boolean) -> Unit,
    toMovieDetail: (Int, @MediaType Int) -> Unit,
) {
    composable(
        route = "$peopleDetailNavigationRoute/{$peopleIdArg}/{$peopleTypeArg}",
        arguments = listOf(
            navArgument(peopleIdArg) {
                type = NavType.IntType
            },
            navArgument(peopleTypeArg) {
                type = NavType.IntType
            },
        )
    ) {
        val detailType = it.arguments?.getInt(peopleTypeArg) ?: 0
        PeopleDetailRoute(
            onBackClick = onBackClick,
            detailType = detailType,
            toMovieDetail = toMovieDetail
        )
    }
}