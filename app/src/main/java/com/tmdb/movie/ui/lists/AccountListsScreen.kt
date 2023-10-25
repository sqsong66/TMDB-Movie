package com.tmdb.movie.ui.lists

import androidx.compose.runtime.Composable

@Composable
fun MyListsRoute(
    toListsDetail: () -> Unit,
    onBackClick: (Boolean) -> Unit,
) {
    MyListsScreen(
        toListsDetail = toListsDetail,
        onBackClick = onBackClick,
    )
}

@Composable
fun MyListsScreen(
    toListsDetail: () -> Unit,
    onBackClick: (Boolean) -> Unit,
) {

}
