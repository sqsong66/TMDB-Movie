package com.tmdb.movie.ui.me

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tmdb.movie.data.AccountMediaType

const val meNavigationRoute = "me_navigation_route"

fun NavController.navigateToMe(navOptions: NavOptions? = null) {
    this.navigate(meNavigationRoute, navOptions)
}

fun NavGraphBuilder.meScreen(
    toAuthorize: () -> Unit,
    toAccountLists: (Int) -> Unit,
    toAccountMediaLists: (Int, @AccountMediaType Int) -> Unit,
) {
    composable(route = meNavigationRoute) {
        MeRoute(
            toAuthorize = toAuthorize,
            toAccountLists = toAccountLists,
            toAccountMediaLists = toAccountMediaLists,
        )
    }
}