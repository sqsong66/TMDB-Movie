package com.tmdb.movie.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tmdb.movie.data.MediaType

internal const val movieIdArg = "movieId"
internal const val movieTypeArg = "movieType"
internal const val movieFromArg = "movieFrom"
private const val movieDetailNavigationRoute = "movie_detail_navigation_route"

internal class MovieArgs(val movieId: Int, @MediaType val type: Int) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                savedStateHandle.get<Int>(movieIdArg) ?: 0,
                savedStateHandle.get<Int>(movieTypeArg) ?: MediaType.MOVIE
            )
}

// from 0->home,discovery 1->people home、discovery需要设置显示隐藏BottomBar
fun NavController.navigateToMovieDetail(movieId: Int, type: Int = MediaType.MOVIE, from: Int = 0) {
    this.navigate("$movieDetailNavigationRoute/$movieId/$type/$from") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.movieDetailScreen(
    onBackClick: (Boolean) -> Unit,
    onNavigateToPeopleDetail: (Int) -> Unit,
) {
    composable(
        route = "$movieDetailNavigationRoute/{$movieIdArg}/{$movieTypeArg}/{$movieFromArg}",
        arguments = listOf(
            navArgument(movieIdArg) {
                type = NavType.IntType
            },
            navArgument(movieTypeArg) {
                type = NavType.IntType
            },
            navArgument(movieFromArg) {
                type = NavType.IntType
            },
        )
    ) {
        val movieType = it.arguments?.getInt(movieTypeArg) ?: 0
        val movieFrom = it.arguments?.getInt(movieFromArg) ?: 0
        val mediaId = it.arguments?.getInt(movieIdArg) ?: 0
        MovieDetailRoute(
            mediaId = mediaId,
            mediaType = movieType,
            movieFrom = movieFrom,
            onBackClick = onBackClick,
            onNavigateToPeopleDetail = onNavigateToPeopleDetail
        )
    }
}