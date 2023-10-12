package com.tmdb.movie.navigation

import com.tmdb.movie.R
import com.tmdb.movie.ui.discovery.discoveryNavigationRoute
import com.tmdb.movie.ui.home.homeNavigationRoute
import com.tmdb.movie.ui.me.meNavigationRoute


enum class TMDBDestination(
    val selectedIconId: Int,
    val unselectedIconId: Int,
    val titleTextId: Int,
    val route: String,
) {
    HOME(
        selectedIconId = R.drawable.baseline_home_24,
        unselectedIconId = R.drawable.outline_home_24,
        titleTextId = R.string.key_home,
        route = homeNavigationRoute
    ),
    DISCOVERY(
        selectedIconId = R.drawable.baseline_explore_24,
        unselectedIconId = R.drawable.outline_explore_24,
        titleTextId = R.string.key_discovery,
        route = discoveryNavigationRoute
    ),
    ME(
        selectedIconId = R.drawable.baseline_account_circle_24,
        unselectedIconId = R.drawable.outline_account_circle_24,
        titleTextId = R.string.key_me,
        route = meNavigationRoute
    )
}