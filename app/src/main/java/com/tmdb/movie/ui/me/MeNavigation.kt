package com.tmdb.movie.ui.me

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val meNavigationRoute = "me_navigation_route"

fun NavController.navigateToMe(navOptions: NavOptions? = null) {
    this.navigate(meNavigationRoute, navOptions)
}

fun NavGraphBuilder.meScreen(toAuthorize: () -> Unit) {
    composable(route = meNavigationRoute) {
        MeRoute(toAuthorize = toAuthorize)
    }
}