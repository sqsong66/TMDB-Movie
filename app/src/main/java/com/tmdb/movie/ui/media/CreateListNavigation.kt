package com.tmdb.movie.ui.media

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val createListNavigationRoute = "create_list_navigation_route"

fun NavController.navigateToCreateList() {
    this.navigate(createListNavigationRoute)
}

fun NavGraphBuilder.createListScreen(onBackClick: (Boolean) -> Unit,) {
    composable(route = createListNavigationRoute) {
        CreateListRoute(
            onBackClick = onBackClick
        )
    }
}