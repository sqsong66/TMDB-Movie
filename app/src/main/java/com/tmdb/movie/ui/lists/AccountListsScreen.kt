package com.tmdb.movie.ui.lists

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.tmdb.movie.R
import com.tmdb.movie.component.ErrorPage
import com.tmdb.movie.component.LoadingError
import com.tmdb.movie.component.LoadingFooter
import com.tmdb.movie.data.HomePopularMovie
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.MediaList
import com.tmdb.movie.ui.lists.component.AccountListsItem
import com.tmdb.movie.ui.lists.component.AccountListsItemPlaceholder
import com.tmdb.movie.ui.lists.vm.AccountListsViewModel

@Composable
fun AccountListsRoute(
    toListsDetail: (Int, String) -> Unit,
    onBackClick: (Boolean) -> Unit,
    viewModel: AccountListsViewModel = hiltViewModel(),
) {

    BackHandler { onBackClick(true) }
    val config by viewModel.configStream.collectAsStateWithLifecycle()
    val cachedMovies by viewModel.cachedMovies.collectAsStateWithLifecycle()
    val accountLists = viewModel.accountListsPagingSource.collectAsLazyPagingItems()

    AccountListsScreen(
        toListsDetail = toListsDetail,
        onBackClick = onBackClick,
        accountLists = accountLists,
        cachedMovies = cachedMovies,
        onBuildImage = { url, type, size ->
            config.buildImageUrl(url, type, size)
        }
    )
}

@Composable
fun AccountListsScreen(
    toListsDetail: (Int, String) -> Unit,
    onBackClick: (Boolean) -> Unit,
    cachedMovies: List<HomePopularMovie>,
    accountLists: LazyPagingItems<MediaList>,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
) {

    Column(modifier = Modifier.fillMaxSize()) {
        AccountListsTopBar(
            onBackClick = onBackClick,
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(accountLists.itemCount) { index ->
                val item = accountLists[index]
                if (item != null) {
                    AccountListsItem(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp,
                            bottom = if (index == accountLists.itemCount - 1) 16.dp else 0.dp
                        ),
                        mediaList = item,
                        cachedMovies = cachedMovies,
                        onBuildImage = onBuildImage,
                        toListsDetail = toListsDetail
                    )
                }
            }

            when (accountLists.loadState.refresh) {
                is LoadState.Error -> item {
                    ErrorPage(
                        modifier = Modifier.padding(top = 120.dp),
                        onRetry = { accountLists.retry() })
                }

                LoadState.Loading -> {
                    items(5) {
                        AccountListsItemPlaceholder(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp))
                    }
                }

                else -> {}
            }

            when (accountLists.loadState.append) {
                is LoadState.Error -> item { LoadingError { accountLists.retry() } }
                LoadState.Loading -> item { LoadingFooter() }
                else -> {}
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountListsTopBar(
    onBackClick: (Boolean) -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.key_lists),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onBackClick(true)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = ""
                )
            }
        },
        /*colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        )*/
    )
}
