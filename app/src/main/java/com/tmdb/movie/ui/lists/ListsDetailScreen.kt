package com.tmdb.movie.ui.lists

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tmdb.movie.component.rememberCurrentOffset
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.ListsDetail
import com.tmdb.movie.data.MediaItem
import com.tmdb.movie.data.MediaType
import com.tmdb.movie.ext.pxToDp
import com.tmdb.movie.ui.lists.component.ListsDetailBody
import com.tmdb.movie.ui.lists.component.ListsDetailHeader
import com.tmdb.movie.ui.lists.component.ListsDetailTitle
import com.tmdb.movie.ui.lists.component.ListsDetailTopBar
import com.tmdb.movie.ui.lists.vm.ListsDetailUiState
import com.tmdb.movie.ui.lists.vm.ListsDetailViewModel
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun ListsDetailRoute(
    toMediaDetail: (Int, @MediaType Int) -> Unit,
    onBackClick: (Boolean) -> Unit,
    viewModel: ListsDetailViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val config by viewModel.configStream.collectAsStateWithLifecycle()

    ListsDetailScreen(
        uiState = uiState,
        toMediaDetail = toMediaDetail,
        onBackClick = onBackClick,
        onBuildImage = { url, type, size ->
            config.buildImageUrl(type, url, size)
        }
    )
}

@Composable
fun ListsDetailScreen(
    uiState: ListsDetailUiState,
    toMediaDetail: (Int, @MediaType Int) -> Unit,
    onBackClick: (Boolean) -> Unit,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
) {

    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is ListsDetailUiState.Error -> TODO()
            ListsDetailUiState.Loading -> {
                ListsDetailTopBar(
                    modifier = Modifier,
                    scrollValue = -1f,
                    topBarBottom = 0f,
                    onBackClick = onBackClick,
                )
            }

            is ListsDetailUiState.Success -> ListsDetailContent(
                onBackClick = onBackClick,
                listsDetail = uiState.data,
                onBuildImage = onBuildImage,
                toMediaDetail = toMediaDetail,
            )
        }
    }

}

@Composable
fun ListsDetailContent(
    onBackClick: (Boolean) -> Unit,
    listsDetail: ListsDetail,
    toMediaDetail: (Int, @MediaType Int) -> Unit,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
) {

    val listState = rememberLazyGridState()
    val scrollValue = rememberCurrentOffset(listState)
    var headerHeight by remember { mutableFloatStateOf(600f) }
    var topBarHeight by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(headerHeight) {
        listState.scrollToItem(0)
    }

    ListsDetailHeader(
        modifier = Modifier
            .onGloballyPositioned {
                headerHeight = it.size.height.toFloat()
            },
        headerHeight = headerHeight,
        scrollValue = scrollValue.value.toFloat(),
        mediaItem = listsDetail.items?.firstOrNull(),
        onBuildImage = onBuildImage,
    )

    ListsDetailBody(
        modifier = Modifier,
        listState = listState,
        headerHeight = headerHeight.pxToDp(),
        listsDetail = listsDetail,
        onBuildImage = onBuildImage,
        toMediaDetail = toMediaDetail
    )

    ListsDetailTopBar(
        modifier = Modifier.onGloballyPositioned {
            topBarHeight = it.size.height.toFloat()
        },
        scrollValue = scrollValue.value.toFloat(),
        topBarBottom = headerHeight - topBarHeight,
        onBackClick = onBackClick,
    )

    ListsDetailTitle(
        title = listsDetail.name ?: "",
        scrollValue = scrollValue.value.toFloat(),
        headerHeight = headerHeight.pxToDp(),
        topBarHeight = topBarHeight.pxToDp(),
    )
}

@Composable
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun ListsDetailScreenPreview() {
    TMDBMovieTheme {
        ListsDetailScreen(
            toMediaDetail = { _, _ -> },
            onBackClick = { },
            uiState = ListsDetailUiState.Success(
                ListsDetail(
                    id = 1,
                    name = "Loved Movies",
                    description = "My love moive lists.",
                    posterPath = "",
                    itemCount = 1,
                    favoriteCount = 0,
                    iso6391 = "",
                    createdBy = "",
                    items = listOf(
                        MediaItem(
                            id = 1,
                            title = "Expendables 3",
                            overview = "test",
                            posterPath = "",
                            backdropPath = "",
                            voteAverage = 0f,
                            voteCount = 0,
                            releaseDate = "",
                            mediaType = "movie",
                        )
                    )
                )
            )
        )
    }
}
