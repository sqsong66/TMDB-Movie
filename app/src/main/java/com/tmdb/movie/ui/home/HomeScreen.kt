package com.tmdb.movie.ui.home

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.tmdb.movie.R
import com.tmdb.movie.component.BlurHeaderBgComponent
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.MediaType
import com.tmdb.movie.data.MediaItem
import com.tmdb.movie.data.People
import com.tmdb.movie.data.SearchHistory
import com.tmdb.movie.data.SearchItem
import com.tmdb.movie.ext.pxToDp
import com.tmdb.movie.ui.home.component.HistorySearchComponent
import com.tmdb.movie.ui.home.component.HomeMoviePagerComponent
import com.tmdb.movie.ui.home.component.PopularPeopleComponent
import com.tmdb.movie.ui.home.component.SearchResultComponent
import com.tmdb.movie.ui.home.component.TrendingComponent
import com.tmdb.movie.ui.home.vm.HomeViewModel
import com.tmdb.movie.ui.home.vm.MovieLoadState
import com.tmdb.movie.ui.theme.TMDBMovieTheme
import kotlin.math.roundToInt

@Composable
fun HomeRoute(
    onShowBottomBar: (Boolean) -> Unit,
    navigateToMovieDetail: (Int, @MediaType Int, Int) -> Unit,
    onNavigateToPeopleDetail: (Int, Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val config by viewModel.configStream.collectAsStateWithLifecycle()
    val searchResult = viewModel.searchResult.collectAsLazyPagingItems()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val tvPopularState by viewModel.tvPopularState.collectAsStateWithLifecycle()
    val tvTrendingState by viewModel.tvTrendingState.collectAsStateWithLifecycle()
    val tvAirTodayState by viewModel.tvAirTodayState.collectAsStateWithLifecycle()
    val recentSearchList by viewModel.recentSearchList.collectAsStateWithLifecycle()
    val isSearchBarActive by viewModel.isSearchBarActive.collectAsStateWithLifecycle()
    val moviePopularState by viewModel.moviePopularState.collectAsStateWithLifecycle()
    val popularPeopleState by viewModel.popularPeopleState.collectAsStateWithLifecycle()
    val moviesTrendingState by viewModel.movieTrendingState.collectAsStateWithLifecycle()
    val movieNowPlayingState by viewModel.movieNowPlayingState.collectAsStateWithLifecycle()

    HomeScreen(
        moviesTrendingState = moviesTrendingState,
        tvTrendingState = tvTrendingState,
        popularPeopleState = popularPeopleState,
        movieNowPlayingState = movieNowPlayingState,
        tvAirTodayState = tvAirTodayState,
        moviePopularState = moviePopularState,
        tvPopularState = tvPopularState,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onSearchTriggered = viewModel::onSearchTriggered,
        onRefreshData = viewModel::toggleRefresh,
        onActiveStateChanged = {
            onShowBottomBar(!it)
            viewModel.changeSearchBarActiveState(it)
        },
        isRefreshing = moviesTrendingState is MovieLoadState.Loading,
        onBuildImage = { url, type, size ->
            config.buildImageUrl(url, type, size)
        },
        navigateToMovieDetail = { id, type ->
            navigateToMovieDetail(id, type, 0)
        },
        onNavigateToPeopleDetail = { id ->
            onNavigateToPeopleDetail(id, 0)
        },
        searchQuery = searchQuery,
        isSearchBarActive = isSearchBarActive,
        recentSearchList = recentSearchList,
        searchResult = searchResult,
        navigateToDetail = { id, type ->
            when (type) {
                MediaType.MOVIE, MediaType.TV -> navigateToMovieDetail(id, type, 1)
                MediaType.PERSON -> onNavigateToPeopleDetail(id, 1)
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    moviesTrendingState: MovieLoadState<MediaItem> = MovieLoadState.Loading(),
    tvTrendingState: MovieLoadState<MediaItem> = MovieLoadState.Loading(),
    popularPeopleState: MovieLoadState<People> = MovieLoadState.Loading(),
    movieNowPlayingState: MovieLoadState<MediaItem> = MovieLoadState.Loading(),
    tvAirTodayState: MovieLoadState<MediaItem> = MovieLoadState.Loading(),
    moviePopularState: MovieLoadState<MediaItem> = MovieLoadState.Loading(),
    tvPopularState: MovieLoadState<MediaItem> = MovieLoadState.Loading(),
    onSearchQueryChanged: (String) -> Unit = {},
    onSearchTriggered: (String) -> Unit = {},
    onActiveStateChanged: (Boolean) -> Unit = {},
    onRefreshData: () -> Unit = {},
    isRefreshing: Boolean = false,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
    navigateToMovieDetail: (Int, @MediaType Int) -> Unit = { _, _ -> },
    onNavigateToPeopleDetail: (Int) -> Unit = {},
    searchQuery: String = "",
    isSearchBarActive: Boolean,
    recentSearchList: List<SearchHistory> = emptyList(),
    searchResult: LazyPagingItems<SearchItem>? = null,
    navigateToDetail: (Int, @MediaType Int) -> Unit = { _, _ -> },
) {

    var imageUrl by remember { mutableStateOf("") }

    var searchBarActiveState by remember { mutableStateOf(false) }

    // 顶部搜索区域高度
    var toolbarHeight by remember { mutableIntStateOf(283) }

    // 顶部搜索区域偏移量
    val toolbarOffsetHeightPx = remember { mutableFloatStateOf(0f) }

    val refreshState = rememberPullRefreshState(isRefreshing, onRefresh = onRefreshData)

    LaunchedEffect(key1 = isSearchBarActive, block = {
        searchBarActiveState = isSearchBarActive
    })

    Log.w("sqsong", "HomeScreen: ReCompose")

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (searchBarActiveState) return Offset.Zero
                val newOffset = toolbarOffsetHeightPx.floatValue + available.y
                val offset = newOffset.coerceIn(-toolbarHeight.toFloat(), 0f)
                toolbarOffsetHeightPx.floatValue = if (refreshState.progress == 0f) offset else 0f
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(state = refreshState)
            .nestedScroll(nestedScrollConnection),
    ) {
        if (imageUrl.isNotEmpty()) BlurHeaderBgComponent(imageUrl = imageUrl, useBlur = true)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(toolbarHeight.pxToDp()))

            HomeMoviePagerComponent(
                modifier = Modifier.padding(top = 16.dp),
                moviePopularState = moviePopularState,
                onBuildImage = onBuildImage,
                navigateToMovieDetail = navigateToMovieDetail,
                onPageChanged = { imagePath ->
                    imageUrl = imagePath
                }
            )

            PopularPeopleComponent(
                title = stringResource(R.string.key_popular_people),
                trendingState = popularPeopleState,
                onBuildImage = { url, type ->
                    onBuildImage(url, type, ImageSize.MEDIUM)
                },
                onNavigateToPeopleDetail = onNavigateToPeopleDetail
            )
            TrendingComponent(
                title = stringResource(R.string.key_popular_tv),
                mediaType = MediaType.TV,
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                trendingState = tvPopularState,
                onBuildImage = { url, type ->
                    onBuildImage(url, type, ImageSize.MEDIUM)
                },
                navigateToMovieDetail = navigateToMovieDetail
            )
            TrendingComponent(
                title = stringResource(R.string.key_movies_trending),
                mediaType = MediaType.MOVIE,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                trendingState = moviesTrendingState,
                onBuildImage = { url, type ->
                    onBuildImage(url, type, ImageSize.MEDIUM)
                },
                navigateToMovieDetail = navigateToMovieDetail
            )
            TrendingComponent(
                title = stringResource(R.string.key_tv_trending),
                mediaType = MediaType.TV,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                trendingState = tvTrendingState,
                onBuildImage = { url, type ->
                    onBuildImage(url, type, ImageSize.MEDIUM)
                },
                navigateToMovieDetail = navigateToMovieDetail
            )
            TrendingComponent(
                title = stringResource(R.string.key_movies_now_playing),
                mediaType = MediaType.MOVIE,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                trendingState = movieNowPlayingState,
                onBuildImage = { url, type ->
                    onBuildImage(url, type, ImageSize.MEDIUM)
                },
                navigateToMovieDetail = navigateToMovieDetail
            )
            TrendingComponent(
                title = stringResource(R.string.key_tv_air_today),
                mediaType = MediaType.TV,
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                trendingState = tvAirTodayState,
                onBuildImage = { url, type ->
                    onBuildImage(url, type, ImageSize.MEDIUM)
                },
                navigateToMovieDetail = navigateToMovieDetail
            )
            Spacer(modifier = Modifier.height(80.dp))
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopSearchBar(
                modifier = Modifier
                    .background(Color.Transparent)
                    .onGloballyPositioned {
                        toolbarHeight = it.size.height
                    }
                    .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx.floatValue.roundToInt()) },
                onSearchQueryChanged = onSearchQueryChanged,
                onSearchTriggered = onSearchTriggered,
                onActiveStateChanged = onActiveStateChanged,
                searchQuery = searchQuery,
                isSearchBarActive = isSearchBarActive,
                recentSearchList = recentSearchList,
                searchResult = searchResult,
                onBuildImage = { url, type ->
                    onBuildImage(url, type, ImageSize.MEDIUM)
                },
                navigateToDetail = navigateToDetail
            )
            PullRefreshIndicator(
                isRefreshing,
                refreshState,
                contentColor = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopSearchBar(
    modifier: Modifier,
    onSearchQueryChanged: (String) -> Unit = {},
    onSearchTriggered: (String) -> Unit = {},
    onActiveStateChanged: (Boolean) -> Unit = {},
    searchQuery: String = "",
    isSearchBarActive: Boolean = false,
    recentSearchList: List<SearchHistory> = emptyList(),
    searchResult: LazyPagingItems<SearchItem>? = null,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
    navigateToDetail: (Int, @MediaType Int) -> Unit = { _, _ -> },
) {

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val isSearchEmpty by remember(searchQuery) { derivedStateOf { searchQuery.isEmpty() } }
    val avatarAlpha = animateFloatAsState(
        targetValue = if (isSearchBarActive) 0f else 1f,
        label = "",
        animationSpec = tween(300, easing = LinearEasing)
    )
    val horizontalPadding: Dp by animateDpAsState(
        targetValue = if (isSearchBarActive) 0.dp else 16.dp,
        label = "",
        animationSpec = tween(300, easing = LinearEasing)
    )
    val bottomPadding: Dp by animateDpAsState(
        targetValue = if (isSearchBarActive) 0.dp else 8.dp,
        label = "",
        animationSpec = tween(300, easing = LinearEasing)
    )
    SearchBar(
        modifier = modifier
            .padding(
                start = horizontalPadding,
                end = horizontalPadding,
                bottom = bottomPadding
            )
            .focusRequester(focusRequester),
        query = searchQuery,
        tonalElevation = 8.dp,
        onQueryChange = onSearchQueryChanged,
        onSearch = {
            onSearchTriggered(it)
            keyboardController?.hide()
        },
        active = isSearchBarActive,
        onActiveChange = {
            Log.w("sqsong", "TopSearchBar onActiveChange: $it")
            onActiveStateChanged(it)
        },
        leadingIcon = {
            AnimatedContent(targetState = isSearchBarActive,
                label = "",
                transitionSpec = {
                    if (targetState) {
                        fadeIn().togetherWith(fadeOut())
                    } else {
                        fadeIn() togetherWith fadeOut()
                    }
                }) {
                IconButton(onClick = {
                    if (isSearchBarActive) {
                        focusRequester.requestFocus()
                    }
                    onActiveStateChanged(!isSearchBarActive)
                }) {
                    Icon(
                        modifier = Modifier,
                        painter = painterResource(id = if (it) R.drawable.baseline_arrow_back_24 else R.drawable.baseline_search_24),
                        contentDescription = "Search"
                    )
                }
            }
        },
        trailingIcon = {
            if (isSearchBarActive) {
                if (!isSearchEmpty) {
                    IconButton(onClick = {
                        onSearchQueryChanged("")
                    }) {
                        Icon(
                            modifier = Modifier,
                            painter = painterResource(id = R.drawable.baseline_close_24),
                            contentDescription = "Close"
                        )
                    }
                }
            } else {
                Image(
                    modifier = Modifier.clip(CircleShape),
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "Avatar",
                    alpha = avatarAlpha.value
                )
            }
        },
        placeholder = {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = stringResource(id = R.string.key_search_movie),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        0.6f
                    )
                ),
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .background(color = MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isSearchEmpty) {
                if (recentSearchList.isNotEmpty()) HistorySearchComponent(
                    modifier = Modifier.fillMaxWidth(),
                    searchList = recentSearchList.map { it.query },
                    onSearch = {
                        onSearchQueryChanged(it)
                        onSearchTriggered(it)
                    }
                )
            } else {
                SearchResultComponent(
                    searchQuery = searchQuery,
                    searchResult = searchResult,
                    onBuildImage = onBuildImage,
                    navigateToDetail = navigateToDetail
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TMDBMovieTheme {
        HomeScreen(
            moviesTrendingState = MovieLoadState.Loading(),
            tvTrendingState = MovieLoadState.Loading(),
            popularPeopleState = MovieLoadState.Loading(),
            movieNowPlayingState = MovieLoadState.Loading(),
            tvAirTodayState = MovieLoadState.Loading(),
            isSearchBarActive = false,
        )
    }
}