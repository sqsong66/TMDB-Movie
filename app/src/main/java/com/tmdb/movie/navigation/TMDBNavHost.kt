package com.tmdb.movie.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import com.tmdb.movie.R
import com.tmdb.movie.ui.app.TMDBAppState
import com.tmdb.movie.ui.detail.movieDetailScreen
import com.tmdb.movie.ui.detail.navigateToMovieDetail
import com.tmdb.movie.ui.discovery.discoveryScreen
import com.tmdb.movie.ui.home.homeNavigationRoute
import com.tmdb.movie.ui.home.homeScreen
import com.tmdb.movie.ui.lists.accountListsScreen
import com.tmdb.movie.ui.lists.listsDetailScreen
import com.tmdb.movie.ui.lists.navigateToAccountLists
import com.tmdb.movie.ui.lists.navigateToListsDetail
import com.tmdb.movie.ui.main.component.LoginTipsDialog
import com.tmdb.movie.ui.main.vm.MainViewModel
import com.tmdb.movie.ui.me.dialog.AuthenticationDialog
import com.tmdb.movie.ui.me.meScreen
import com.tmdb.movie.ui.media.createListScreen
import com.tmdb.movie.ui.media.navigateToCreateList
import com.tmdb.movie.ui.people.navigateToPeopleDetail
import com.tmdb.movie.ui.people.peopleDetailScreen

@Composable
fun TMDBNavHost(
    appState: TMDBAppState,
    onShowBottomBar: (Boolean) -> Unit,
    onBackClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    startDestination: String = homeNavigationRoute,
) {

    val context = LocalContext.current
    val navController = appState.navController
    var showLoginTipsDialog by rememberSaveable { mutableStateOf(false) }
    val authorizeState by viewModel.authorizeState.collectAsStateWithLifecycle()
    var isShowAuthenticationDialog by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(authorizeState) {
        if (authorizeState.isNotEmpty()) {
            if (authorizeState.startsWith("success")) {
                Toast.makeText(context, context.getString(R.string.key_auth_success), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, context.getString(R.string.key_auth_error), Toast.LENGTH_SHORT).show()
            }
            viewModel.updateRequestToken("")
        }
    }

    if (showLoginTipsDialog) {
        LoginTipsDialog(
            onDismiss = { showLoginTipsDialog = false },
            toLogin = {
                showLoginTipsDialog = false
                isShowAuthenticationDialog = true
            },
        )
    }

    if (isShowAuthenticationDialog) {
        AuthenticationDialog(
            onGetUserData = viewModel::updateRequestToken,
            onDismiss = {
                isShowAuthenticationDialog = false
            },
        )
    }

    NavHost(
        navController = appState.navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeScreen(
            onShowBottomBar = onShowBottomBar,
            navigateToMovieDetail = { id, type, from ->
                onShowBottomBar(false)
                navController.navigateToMovieDetail(id, type, from)
            },
            onNavigateToPeopleDetail = { id, from ->
                onShowBottomBar(false)
                navController.navigateToPeopleDetail(id, from)
            },
        )
        discoveryScreen(
            navigateToDetail = { movieItem, type ->
                onShowBottomBar(false)
                navController.navigateToMovieDetail(movieItem?.id ?: 0, type)
            },
            nestedGraphs = {
                movieDetailScreen(
                    onBackClick = onBackClick,
                    onNavigateToPeopleDetail = {
                        navController.navigateToPeopleDetail(it, detailType = 1)
                    },
                    toLogin = {
                        showLoginTipsDialog = true
                    },
                    onCreateList = {
                        navController.navigateToCreateList()
                    }
                )
            })
        meScreen(
            toAuthorize = {
                isShowAuthenticationDialog = true
            },
            toMyLists = {
                onShowBottomBar(false)
                navController.navigateToAccountLists(it)
            },
        )
        peopleDetailScreen(onBackClick = onBackClick, toMovieDetail = { movieId, type ->
            navController.navigateToMovieDetail(movieId, type, from = 1)
        })
        createListScreen(onBackClick = onBackClick)
        accountListsScreen(
            toListsDetail = {
                navController.navigateToListsDetail(it)
            },
            onBackClick = onBackClick
        )
        listsDetailScreen(
            toMediaDetail = { id, type ->
                navController.navigateToMovieDetail(id, type, from = 1)
            },
            onBackClick = onBackClick
        )
    }
}