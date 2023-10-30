package com.tmdb.movie.ui.account

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.tmdb.movie.R
import com.tmdb.movie.component.EmptyPage
import com.tmdb.movie.component.ErrorPage
import com.tmdb.movie.component.LoadingError
import com.tmdb.movie.component.LoadingFooter
import com.tmdb.movie.data.AccountMediaType
import com.tmdb.movie.data.MediaType
import com.tmdb.movie.ui.account.component.AccountMediaItem
import com.tmdb.movie.ui.account.component.AccountMediaItemPlaceholder
import com.tmdb.movie.ui.account.component.AccountMediaListsTabRow
import com.tmdb.movie.ui.account.component.AccountMediaListsTopBar
import com.tmdb.movie.ui.account.vm.AccountMediaLisViewModel
import com.tmdb.movie.ui.theme.TMDBMovieTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AccountMediaListRoute(
    @AccountMediaType accountMediaType: Int,
    onBackClick: (Boolean) -> Unit,
    toMediaDetail: (Int, Int) -> Unit,
) {

    BackHandler { onBackClick(true) }
    AccountMediaListsScreen(
        accountMediaType = accountMediaType,
        onBackClick = onBackClick,
        toMediaDetail = toMediaDetail,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AccountMediaListsScreen(
    @AccountMediaType accountMediaType: Int,
    onBackClick: (Boolean) -> Unit,
    toMediaDetail: (Int, Int) -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {

    val context = LocalContext.current

    val tabList = listOf(context.getString(R.string.key_movies), context.getString(R.string.key_tv_shows))

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { tabList.size }

    Column(modifier = Modifier.fillMaxSize()) {
        AccountMediaListsTopBar(
            modifier = Modifier,
            accountMediaType = accountMediaType,
            onBackClick = onBackClick,
        )

        AccountMediaListsTabRow(
            tabLists = tabList,
            selectedTabIndex = pagerState.currentPage,
            onTabSelected = { pageIndex ->
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pageIndex)
                }
            }
        )

        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState
        ) { pageIndex ->
            AccountMediaListsScreen(
                mediaType = pageIndex,
                toMediaDetail = toMediaDetail,
            )
        }
    }
}

@Composable
fun AccountMediaListsScreen(
    @MediaType mediaType: Int,
    toMediaDetail: (Int, Int) -> Unit,
    viewModel: AccountMediaLisViewModel = hiltViewModel(),
) {

    val configStream = viewModel.configStream.collectAsStateWithLifecycle()
    val pagingSource = if (mediaType == MediaType.MOVIE) {
        viewModel.accountMoviePagingSource.collectAsLazyPagingItems()
    } else {
        viewModel.accountTVPagingSource.collectAsLazyPagingItems()
    }

    Box(modifier = Modifier.fillMaxSize()) {

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            val itemCount = pagingSource.itemCount

            items(itemCount) { index ->
                pagingSource[index]?.let { mediaItem ->
                    AccountMediaItem(
                        modifier = Modifier.padding(
                            top = if (index < 2) 16.dp else 0.dp,
                            start = if (index % 2 == 0) 16.dp else 8.dp,
                            end = if (index % 2 == 1) 16.dp else 8.dp,
                            bottom = 16.dp
                        ),
                        mediaType = mediaType,
                        mediaItem = mediaItem,
                        onBuildImage = { url, type, size ->
                            configStream.value.buildImageUrl(url, type, size)
                        },
                        toMediaDetail = toMediaDetail,
                    )
                }
            }

            when (pagingSource.loadState.refresh) {
                is LoadState.Error -> item(
                    span = {
                        GridItemSpan(2)
                    }
                ) {
                    ErrorPage(
                        modifier = Modifier.padding(top = 120.dp),
                        onRetry = { pagingSource.retry() })
                }

                LoadState.Loading -> {
                    items(10) { index ->
                        AccountMediaItemPlaceholder(
                            modifier = Modifier.padding(
                                top = if (index < 2) 16.dp else 0.dp,
                                start = if (index % 2 == 0) 16.dp else 8.dp,
                                end = if (index % 2 == 1) 16.dp else 8.dp,
                                bottom = 16.dp
                            ),
                            mediaType = mediaType,
                        )
                    }
                }

                is LoadState.NotLoading -> {
                    if (itemCount == 0) {
                        item(
                            span = {
                                GridItemSpan(2)
                            }
                        ) {
                            EmptyPage(modifier = Modifier.padding(top = 120.dp))
                        }
                    }
                }
            }

            when (pagingSource.loadState.append) {
                is LoadState.Error -> item(
                    span = {
                        GridItemSpan(2)
                    }
                ) { LoadingError { pagingSource.retry() } }

                LoadState.Loading -> {
                    if (itemCount > 0) {
                        item(
                            span = {
                                GridItemSpan(2)
                            }
                        ) { LoadingFooter() }
                    }
                }

                else -> {}
            }
        }


    }
}


@Composable
@Preview(showBackground = true)
fun AccountMediaListsScreenPreview() {
    TMDBMovieTheme {
        AccountMediaListsScreen(
            accountMediaType = AccountMediaType.FAVORITE,
            onBackClick = {},
            toMediaDetail = { _, _ -> }
        )
    }
}