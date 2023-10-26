package com.tmdb.movie.ui.account

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tmdb.movie.data.AccountMediaType

internal const val accountIdArg = "accountId"
internal const val accountMediaTypeArg = "accountMediaType"
private const val accountMediaListNavigationRoute = "account_media_list_navigation_route"

internal class AccountMediaListArgs(val accountId: Int, val accountMediaType: Int) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        accountId = savedStateHandle.get<Int>(accountIdArg) ?: 0,
        accountMediaType = savedStateHandle.get<Int>(accountMediaTypeArg) ?: 0,
    )
}

fun NavController.navigateToAccountMediaList(accountId: Int, @AccountMediaType accountMediaType: Int) {
    this.navigate("$accountMediaListNavigationRoute/$accountId/$accountMediaType")
}


fun NavGraphBuilder.accountMediaListScreen(
    onBackClick: (Boolean) -> Unit,
    toMediaDetail: (Int, Int) -> Unit,
) {
    composable(
        route = "$accountMediaListNavigationRoute/{$accountIdArg}/{$accountMediaTypeArg}",
        arguments = listOf(
            navArgument(accountIdArg) {
                type = NavType.IntType
            },
            navArgument(accountMediaTypeArg) {
                type = NavType.IntType
            },
        )
    ) {
        val accountMediaType = it.arguments?.getInt(accountMediaTypeArg) ?: 0
        AccountMediaListRoute(
            accountMediaType = accountMediaType,
            onBackClick = onBackClick,
            toMediaDetail = toMediaDetail,
        )
    }
}
