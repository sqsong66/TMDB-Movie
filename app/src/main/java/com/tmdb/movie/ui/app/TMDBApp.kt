package com.tmdb.movie.ui.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.tmdb.movie.R
import com.tmdb.movie.navigation.TMDBDestination
import com.tmdb.movie.navigation.TMDBNavHost
import com.tmdb.movie.utils.monitor.NetworkMonitor
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class, ExperimentalLayoutApi::class)
@Composable
fun TMDBApp(
    networkMonitor: NetworkMonitor,
    appState: TMDBAppState = rememberTMDBAppState(networkMonitor)
) {
    Surface(
        modifier = Modifier.navigationBarsPadding(),
        color = MaterialTheme.colorScheme.background,
    ) {
        val context = LocalContext.current
        val snackbarHostState = remember { SnackbarHostState() }
        val isOffline by appState.isOffline.collectAsStateWithLifecycle()
        var showBottomBar by rememberSaveable { mutableStateOf(true) }
        var isDelayShowBottomBar by rememberSaveable { mutableStateOf(false) }

        LaunchedEffect(isDelayShowBottomBar) {
            if (isDelayShowBottomBar) {
                appState.popBackStack()
                delay(1000)
                showBottomBar = true
                isDelayShowBottomBar = false
            }
        }

        val notConnectedMessage = stringResource(R.string.key_not_connected)
        LaunchedEffect(isOffline) {
            if (isOffline) {
                snackbarHostState.showSnackbar(
                    message = notConnectedMessage,
                    duration = SnackbarDuration.Indefinite,
                )
            }
        }

        Scaffold(
            modifier = Modifier.semantics {
                testTagsAsResourceId = true
            },
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            snackbarHost = {
                SnackbarHost(snackbarHostState) {
                    Snackbar(
                        it, containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            },
            bottomBar = {
                if (showBottomBar) {
                    TMDBBottomBar(
                        destinations = appState.materialDestinations,
                        onNavigateTo = appState::navigateToDestination,
                        currentDestination = appState.currentDestination,
                    )
                }
            },
        ) { paddingValues ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .consumeWindowInsets(paddingValues)
            ) {
                TMDBNavHost(
                    appState = appState,
                    onShowBottomBar = {
                        showBottomBar = it
                    },
                    onBackClick = {
                        if (it) {
                            isDelayShowBottomBar = true
                        } else {
                            appState.popBackStack()
                        }
                    },
                )
            }
        }
    }
}

@Composable
fun TMDBBottomBar(
    destinations: List<TMDBDestination>,
    onNavigateTo: (TMDBDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    BottomAppBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateTo(destination) },
                icon = {
                    val iconId = if (selected) {
                        destination.selectedIconId
                    } else {
                        destination.unselectedIconId
                    }
                    Icon(painter = painterResource(id = iconId), contentDescription = null)
                },
                label = { Text(stringResource(id = destination.titleTextId)) },
                enabled = true,
                alwaysShowLabel = true,
                modifier = modifier,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                ),
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TMDBDestination) = this?.hierarchy?.any {
    it.route == destination.route
} ?: false
