package com.tmdb.movie.ui.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.tmdb.movie.navigation.TMDBDestination
import com.tmdb.movie.ui.discovery.discoveryNavigationRoute
import com.tmdb.movie.ui.discovery.navigateToDiscoveryGraph
import com.tmdb.movie.ui.home.homeNavigationRoute
import com.tmdb.movie.ui.home.navigateToHome
import com.tmdb.movie.ui.me.meNavigationRoute
import com.tmdb.movie.ui.me.navigateToMe
import com.tmdb.movie.utils.monitor.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberTMDBAppState(
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): TMDBAppState {
    return remember(
        navController,
        networkMonitor,
        coroutineScope
    ) {
        TMDBAppState(
            navController = navController,
            networkMonitor = networkMonitor,
            coroutineScope = coroutineScope
        )
    }
}

@Stable
class TMDBAppState(
    val navController: NavHostController,
    val networkMonitor: NetworkMonitor,
    val coroutineScope: CoroutineScope,
) {

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TMDBDestination?
        @Composable get() = when (currentDestination?.route) {
            homeNavigationRoute -> TMDBDestination.HOME
            discoveryNavigationRoute -> TMDBDestination.DISCOVERY
            meNavigationRoute -> TMDBDestination.ME
            else -> null
        }

    val materialDestinations: List<TMDBDestination> = TMDBDestination.entries

    fun navigateToDestination(destination: TMDBDestination) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true

        }
        when (destination) {
            TMDBDestination.HOME -> navController.navigateToHome(navOptions)
            TMDBDestination.DISCOVERY -> navController.navigateToDiscoveryGraph(navOptions)
            TMDBDestination.ME -> navController.navigateToMe(navOptions)
        }
    }

    fun popBackStack() {
        navController.popBackStack()
    }
}