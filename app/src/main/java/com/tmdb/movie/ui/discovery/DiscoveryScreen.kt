package com.tmdb.movie.ui.discovery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.tmdb.movie.R
import com.tmdb.movie.data.MediaItem
import com.tmdb.movie.data.MediaType
import com.tmdb.movie.ext.pxToDp
import com.tmdb.movie.ui.discovery.component.DiscoveryTabRow
import com.tmdb.movie.ui.theme.TMDBMovieTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun DiscoveryRoute(navigateToDetail: (MediaItem?, @MediaType Int) -> Unit) {
    DiscoveryScreen(toDetail = navigateToDetail)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiscoveryScreen(toDetail: (MediaItem?, @MediaType Int) -> Unit = { _, _ -> }) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val tabList = listOf(context.getString(R.string.key_movies), context.getString(R.string.key_tv_shows))

    // 顶部搜索区域高度
    var toolbarHeight by remember { mutableIntStateOf(283) }

    // 顶部搜索区域偏移量
    val toolbarOffsetHeightPx = remember { mutableFloatStateOf(0f) }

    /*var pullRefreshProgress by remember { mutableFloatStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val newOffset = toolbarOffsetHeightPx.floatValue + available.y
                val offset = newOffset.coerceIn(-toolbarHeight.toFloat(), 0f)
                toolbarOffsetHeightPx.floatValue = if (pullRefreshProgress == 0f) offset else 0f
                return Offset.Zero
            }
        }
    }*/

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { tabList.size }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        // .nestedScroll(nestedScrollConnection),
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState
        ) { pageIndex ->
            DiscoveryMovieRoute(
                topHeight = toolbarHeight.pxToDp(),
                mediaType = pageIndex,
                onPullRefreshProgress = { },
                toDetail = toDetail,
            )
        }
        DiscoveryTabRow(tabLists = tabList,
            modifier = Modifier
            .onGloballyPositioned {
                toolbarHeight = it.size.height
            }
            .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx.floatValue.roundToInt()) }
            .background(color = MaterialTheme.colorScheme.background),
            selectedTabIndex = pagerState.currentPage,
            onTabSelected = { index ->
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            })
    }
}

@Preview(showBackground = true)
@Composable
fun DiscoveryScreenPreview() {
    TMDBMovieTheme {
        DiscoveryScreen()
    }
}