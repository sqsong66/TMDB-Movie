package com.tmdb.movie.ui.discovery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.MediaItem
import com.tmdb.movie.data.MediaType
import com.tmdb.movie.data.TMDBConfig
import com.tmdb.movie.ui.discovery.component.DiscoveryMovieListComponent
import com.tmdb.movie.ui.discovery.component.DiscoveryTVListComponent
import com.tmdb.movie.ui.discovery.vm.DiscoveryMovieViewModel
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun DiscoveryMovieRoute(
    modifier: Modifier = Modifier,
    topHeight: Dp = 0.dp,
    @MediaType mediaType: Int = MediaType.MOVIE, // 0-Movie 1-TV
    onPullRefreshProgress: (Float) -> Unit = {},
    toDetail: (MediaItem?, Int) -> Unit = { _, _ -> },
    viewModel: DiscoveryMovieViewModel = hiltViewModel(),
) {

    val config: TMDBConfig by viewModel.configStream.collectAsStateWithLifecycle()
    val discoveryMoviePagingSource = if (mediaType == 0) {
        viewModel.discoveryMoviePagingSource.collectAsLazyPagingItems()
    } else {
        viewModel.discoveryTvPagingSource.collectAsLazyPagingItems()
    }

    DiscoveryMoviePage(
        modifier = modifier,
        topHeight = topHeight,
        mediaType = mediaType,
        onPullRefreshProgress = onPullRefreshProgress,
        movieList = discoveryMoviePagingSource,
        onBuildImage = { path, type ->
            config.buildImageUrl(path, type)
        },
        toDetail = toDetail,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DiscoveryMoviePage(
    modifier: Modifier = Modifier,
    topHeight: Dp = 0.dp,
    @MediaType mediaType: Int = MediaType.MOVIE, // 0-Movie 1-TV
    onPullRefreshProgress: (Float) -> Unit = {},
    movieList: LazyPagingItems<MediaItem>? = null,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
    toDetail: (MediaItem?, Int) -> Unit = { _, _ -> },
) {

    // 是否是刷新状态
    var isRefreshing by remember { mutableStateOf(false) }

    val refreshState = rememberPullRefreshState(isRefreshing, onRefresh = {
        movieList?.refresh()
    })

//    LaunchedEffect(refreshState.progress) {
//        onPullRefreshProgress(refreshState.progress)
//    }

    LaunchedEffect(movieList?.loadState) {
        isRefreshing = movieList?.loadState?.refresh is LoadState.Loading
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(state = refreshState),
        contentAlignment = Alignment.TopCenter
    ) {
        if (mediaType == MediaType.MOVIE) {
            DiscoveryMovieListComponent(
                topHeight = topHeight,
                movieList = movieList,
                onBuildImage = onBuildImage,
                toDetail = toDetail,
            )
        } else {
            DiscoveryTVListComponent(
                topHeight = topHeight,
                movieList = movieList,
                onBuildImage = onBuildImage,
                toDetail = toDetail,
            )
        }

        Column {
            Spacer(modifier = Modifier.height(topHeight))
            PullRefreshIndicator(
                isRefreshing,
                refreshState,
                contentColor = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiscoveryMoviePagePreview() {
    TMDBMovieTheme {
        DiscoveryMoviePage()
    }
}
