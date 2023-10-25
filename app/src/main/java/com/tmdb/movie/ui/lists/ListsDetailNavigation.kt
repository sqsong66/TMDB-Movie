package com.tmdb.movie.ui.lists

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tmdb.movie.data.MediaType

internal const val listsIdArg = "listsId"
private const val listsDetailNavigationRoute = "lists_detail_navigation_route"

internal class ListsIdArgs(val listId: Int) {
    constructor(savedStateHandle: SavedStateHandle) : this(savedStateHandle.get<Int>(listsIdArg) ?: 0)
}

fun NavController.navigateToListsDetail(listId: Int) {
    this.navigate("$listsDetailNavigationRoute/$listId")
}

fun NavGraphBuilder.listsDetailScreen(
    toMediaDetail: (Int, @MediaType Int) -> Unit,
    onBackClick: (Boolean) -> Unit,
) {
    composable(
        route = "$listsDetailNavigationRoute/{$listsIdArg}",
        arguments = listOf(
            navArgument(listsIdArg) {
                type = NavType.IntType
            },
        )
    ) {
        ListsDetailRoute(
            toMediaDetail = toMediaDetail,
            onBackClick = onBackClick,
        )
    }
}