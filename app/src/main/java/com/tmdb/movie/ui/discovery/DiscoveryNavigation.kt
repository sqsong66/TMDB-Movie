package com.tmdb.movie.ui.discovery

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tmdb.movie.data.MediaItem
import com.tmdb.movie.data.MediaType

private const val DISCOVERY_GRAPH = "discovery_graph"
const val discoveryNavigationRoute = "discovery_navigation_route"

fun NavController.navigateToDiscoveryGraph(navOptions: NavOptions? = null) {
    this.navigate(DISCOVERY_GRAPH, navOptions)
}

fun NavGraphBuilder.discoveryScreen(
    navigateToDetail: (MediaItem?, @MediaType Int) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = DISCOVERY_GRAPH,
        startDestination = discoveryNavigationRoute,
    ) {
        composable(route = discoveryNavigationRoute) {
            DiscoveryRoute(navigateToDetail)
        }
        nestedGraphs()
    }
}