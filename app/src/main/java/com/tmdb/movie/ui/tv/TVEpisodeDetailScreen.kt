package com.tmdb.movie.ui.tv

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tmdb.movie.R
import com.tmdb.movie.data.Cast
import com.tmdb.movie.data.Episode
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.ext.pxToDp
import com.tmdb.movie.ui.theme.TMDBMovieTheme
import com.tmdb.movie.ui.tv.component.EpisodeDetailTitle
import com.tmdb.movie.ui.tv.component.EpisodeDetailTopBar
import com.tmdb.movie.ui.tv.component.EpisodeDetailTopLayout
import com.tmdb.movie.ui.tv.component.EpisodeGuestStarList
import com.tmdb.movie.ui.tv.component.EpisodeImageLists
import com.tmdb.movie.ui.tv.component.EpisodeTabRow
import com.tmdb.movie.ui.tv.vm.TVDetailViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TVEpisodeDetailRoute(
    onBackClick: (Boolean) -> Unit,
    viewModel: TVDetailViewModel = hiltViewModel(),
) {

    val configStream by viewModel.configStream.collectAsStateWithLifecycle()
    val episodeDetail by viewModel.episodeDetail.collectAsStateWithLifecycle()

    TVEpisodeDetailScreen(
        onBackClick = onBackClick,
        episode = episodeDetail,
        onBuildImage = { path, type, size ->
            configStream.buildImageUrl(path, type, size)
        },
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TVEpisodeDetailScreen(
    onBackClick: (Boolean) -> Unit,
    episode: Episode?,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String,
) {
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState { 2 }
    var topBarHeight by remember { mutableIntStateOf(0) }
    var headerHeight by remember { mutableIntStateOf(0) }
    var imageHeight by remember { mutableIntStateOf(0) }
    val shouldShowBackground by remember(scrollState.value) {
        derivedStateOf { scrollState.value >= imageHeight - topBarHeight }
    }
    val stickHeader by remember(scrollState.value) {
        derivedStateOf { scrollState.value >= headerHeight - topBarHeight }
    }
    val tabItems = listOf(stringResource(R.string.key_guest_stars), stringResource(R.string.key_episode_images))
    val pagerNestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                return if (available.y > 0 || stickHeader) {
                    Offset.Zero
                } else {
                    Offset(
                        x = 0f,
                        y = -scrollState.dispatchRawDelta(-available.y)
                    )
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints {
            val remainHeight = maxHeight
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                EpisodeDetailTopLayout(
                    modifier = Modifier.onGloballyPositioned {
                        headerHeight = it.size.height
                    },
                    episode = episode,
                    onBuildImage = onBuildImage,
                    onImageHeight = { imageHeight = it },
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(remainHeight - topBarHeight.pxToDp())
                ) {
                    EpisodeTabRow(
                        tabItems = tabItems,
                        selectedTabIndex = pagerState.currentPage,
                        onSelected = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(it)
                            }
                        }
                    )

                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxHeight()
                            .nestedScroll(pagerNestedScrollConnection)
                    ) { pagerIndex ->
                        if (pagerIndex == 0) {
                            EpisodeGuestStarList(
                                guestStars = episode?.guestStars ?: emptyList(),
                                onBuildImage = onBuildImage,
                            )
                        } else {
                            EpisodeImageLists(
                                images = episode?.images?.stills ?: emptyList(),
                                onBuildImage = onBuildImage,
                            )
                        }
                    }
                }
            }
        }

        EpisodeDetailTopBar(
            modifier = Modifier.onGloballyPositioned {
                topBarHeight = it.size.height
            },
            shouldShowBackground = shouldShowBackground,
            onBackClick = onBackClick,
        )

        EpisodeDetailTitle(
            scrollState = scrollState,
            title = episode?.name ?: "",
            headerHeight = imageHeight.toFloat(),
            topBarHeight = topBarHeight.toFloat(),
        )

    }
}

@Composable
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun TVEpisodeDetailScreenPreview() {
    val guestStars = mutableListOf<Cast>()
    repeat(30) {
        guestStars.add(Cast(id = 1, name = "Jeremy Crawford", profilePath = "", character = "Yarpen Zigrin"))
    }
    TMDBMovieTheme {
        TVEpisodeDetailScreen(
            onBackClick = {},
            episode = Episode(
                name = "Loki",
                stillPath = "",
                guestStars = guestStars,
            ),
            onBuildImage = { _, _, _ -> "" },
        )
    }
}
