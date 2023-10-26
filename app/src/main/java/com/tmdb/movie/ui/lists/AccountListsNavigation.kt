package com.tmdb.movie.ui.lists

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

internal const val accountIdArg = "accountId"
private const val myListsNavigationRoute = "my_lists_navigation_route"

internal class AccountArgs(val accountId: Int) {
    constructor(savedStateHandle: SavedStateHandle) : this(savedStateHandle.get<Int>(accountIdArg) ?: 0)
}

fun NavController.navigateToAccountLists(accountId: Int, navOptions: NavOptions? = null) {
    this.navigate("$myListsNavigationRoute/$accountId", navOptions)
}

fun NavGraphBuilder.accountListsScreen(
    toListsDetail: (Int, String) -> Unit,
    onBackClick: (Boolean) -> Unit,
) {
    composable(
        route = "$myListsNavigationRoute/{$accountIdArg}",
        arguments = listOf(
            navArgument(accountIdArg) {
                type = NavType.IntType
            },
        )
    ) {
        AccountListsRoute(
            toListsDetail = toListsDetail,
            onBackClick = onBackClick,
        )
    }
}