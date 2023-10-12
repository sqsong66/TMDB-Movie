package com.tmdb.movie.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.tmdb.movie.ui.app.TMDBAppState
import com.tmdb.movie.ui.detail.movieDetailScreen
import com.tmdb.movie.ui.detail.navigateToMovieDetail
import com.tmdb.movie.ui.discovery.discoveryScreen
import com.tmdb.movie.ui.home.homeNavigationRoute
import com.tmdb.movie.ui.home.homeScreen
import com.tmdb.movie.ui.me.meScreen
import com.tmdb.movie.ui.people.navigateToPeopleDetail
import com.tmdb.movie.ui.people.peopleDetailScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TMDBNavHost(
    appState: TMDBAppState,
    onShowToast: (String) -> Unit,
    onShowBottomBar: (Boolean) -> Unit,
    onBackClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = homeNavigationRoute,
) {

    val navController = appState.navController
    NavHost(
        navController = appState.navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeScreen(
            onShowBottomBar = onShowBottomBar,
            navigateToMovieDetail = { id, type, from ->
                onShowBottomBar(false)
                appState.coroutineScope.launch {
                    delay(300)
                    navController.navigateToMovieDetail(id, type, from)
                }
            },
            onNavigateToPeopleDetail = { id, from ->
                onShowBottomBar(false)
                appState.coroutineScope.launch {
                    delay(300)
                    navController.navigateToPeopleDetail(id, from)
                }
            },
        )
        discoveryScreen(
            navigateToDetail = { movieItem, type ->
                onShowBottomBar(false)
                appState.coroutineScope.launch {
                    delay(300)
                    navController.navigateToMovieDetail(movieItem?.id ?: 0, type)
                }
            },
            nestedGraphs = {
                movieDetailScreen(onBackClick = onBackClick, onNavigateToPeopleDetail = {
                    navController.navigateToPeopleDetail(it, detailType = 1)
                })
            })
        meScreen(onShowToast)
        peopleDetailScreen(onBackClick = onBackClick, toMovieDetail = { movieId, type ->
            navController.navigateToMovieDetail(movieId, type, from = 1)
        })
    }
}