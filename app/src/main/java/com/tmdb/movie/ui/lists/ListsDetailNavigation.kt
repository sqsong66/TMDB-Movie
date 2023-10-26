package com.tmdb.movie.ui.lists

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tmdb.movie.data.MediaType
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

internal const val listsIdArg = "listsId"
internal const val listsCoverImageArg = "listsCoverImage"
private const val listsDetailNavigationRoute = "lists_detail_navigation_route"

internal class ListsIdArgs(val listId: Int, val coverImageUrl: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        savedStateHandle.get<Int>(listsIdArg) ?: 0,
        savedStateHandle.get<String>(listsCoverImageArg) ?: ""
    )
}

fun NavController.navigateToListsDetail(listId: Int, coverImageUrl: String) {
    val encodedUrl = URLEncoder.encode(coverImageUrl, StandardCharsets.UTF_8.toString())
    this.navigate("$listsDetailNavigationRoute/$listId/$encodedUrl")
}

fun NavGraphBuilder.listsDetailScreen(
    toMediaDetail: (Int, @MediaType Int) -> Unit,
    onBackClick: (Boolean) -> Unit,
) {
    composable(
        route = "$listsDetailNavigationRoute/{$listsIdArg}/{$listsCoverImageArg}",
        arguments = listOf(
            navArgument(listsIdArg) {
                type = NavType.IntType
            },
            navArgument(listsCoverImageArg) {
                type = NavType.StringType
            },
        )
    ) {
        val coverImageUrl = it.arguments?.getString(listsCoverImageArg) ?: ""
        ListsDetailRoute(
            coverImageUrl = coverImageUrl,
            toMediaDetail = toMediaDetail,
            onBackClick = onBackClick,
        )
    }
}