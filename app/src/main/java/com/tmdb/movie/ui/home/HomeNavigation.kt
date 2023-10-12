package com.tmdb.movie.ui.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tmdb.movie.data.MediaType

const val homeNavigationRoute = "home_navigation_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeNavigationRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onShowBottomBar: (Boolean) -> Unit,
    navigateToMovieDetail: (Int, @MediaType Int, Int) -> Unit,
    onNavigateToPeopleDetail: (Int, Int) -> Unit,
) {
    composable(route = homeNavigationRoute) {
        HomeRoute(
            onShowBottomBar = onShowBottomBar,
            navigateToMovieDetail = navigateToMovieDetail,
            onNavigateToPeopleDetail = onNavigateToPeopleDetail
        )
    }
}