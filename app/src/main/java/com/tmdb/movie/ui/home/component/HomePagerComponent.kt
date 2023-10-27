package com.tmdb.movie.ui.home.component

import android.graphics.RenderEffect
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.placeholder.PlaceholderDefaults
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmerHighlightColor
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.tmdb.movie.R
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.MediaItem
import com.tmdb.movie.data.MediaType
import com.tmdb.movie.ui.home.vm.MovieLoadState
import com.tmdb.movie.ui.theme.TMDBMovieTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeMoviePagerComponent(
    modifier: Modifier,
    moviePopularState: MovieLoadState<MediaItem>,
    onPageChanged: (String) -> Unit,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
    navigateToMovieDetail: (Int, @MediaType Int) -> Unit = { _, _ -> },
) {
    if (moviePopularState is MovieLoadState.Success) {
        HomePagerComponent(
            modifier = modifier,
            movieList = moviePopularState.data,
            onPageChanged = onPageChanged,
            onBuildImage = onBuildImage,
            navigateToMovieDetail = navigateToMovieDetail,
        )
    } else {
        HomePagerComponentPlaceholder(
            modifier = modifier,
        )
    }
}

@Composable
@ExperimentalFoundationApi
fun HomePagerComponent(
    modifier: Modifier,
    movieList: List<MediaItem>,
    onPageChanged: (String) -> Unit,
    navigateToMovieDetail: (Int, @MediaType Int) -> Unit = { _, _ -> },
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
    val context = LocalContext.current
    val placeholderBitmap =
        AppCompatResources.getDrawable(context, R.drawable.image_placeholder)?.toBitmap()?.apply {
            eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
        }

    val pagerState = rememberPagerState(
        initialPage = movieList.size * 300,
        pageCount = { Int.MAX_VALUE }
    )

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .distinctUntilChanged()
            .collect { page ->
                val movieItem = movieList[page % movieList.size]
                onPageChanged(onBuildImage(movieItem.posterPath, ImageType.POSTER, ImageSize.SMALL) ?: "")
            }
    }

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        pageSize = PageSize.Fill,
        contentPadding = PaddingValues(horizontal = 80.dp),
        pageSpacing = (0).dp
    ) { page ->
        val movieItem = movieList[page % movieList.size]
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                    scaleX = lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                    scaleY = lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                    /*alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )*/
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        val blur = (pageOffset * 10f).coerceAtLeast(0.1f)
                        renderEffect = RenderEffect
                            .createBlurEffect(blur, blur, Shader.TileMode.DECAL)
                            .asComposeRenderEffect()
                    }
                },
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        if (pagerState.currentPage == page) {
                            navigateToMovieDetail(movieItem.id, MediaType.MOVIE)
                        } else {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(page,
                                    animationSpec = tween(500)
                                )
                            }
                        }
                    },
                model = ImageRequest.Builder(LocalContext.current)
                    .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                    .data(onBuildImage(movieItem.posterPath, ImageType.POSTER, ImageSize.MEDIUM))
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.FillWidth,
                contentDescription = ""
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .graphicsLayer {
                        val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                        alpha = lerp(
                            start = 0.0f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .padding(top = 8.dp),
                text = movieItem.getMovieName(MediaType.MOVIE),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
@ExperimentalFoundationApi
fun HomePagerComponentPlaceholder(
    modifier: Modifier,
) {
    val pagerState = rememberPagerState(
        initialPage = 200,
        pageCount = { Int.MAX_VALUE }
    )
    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        pageSize = PageSize.Fill,
        contentPadding = PaddingValues(horizontal = 80.dp),
        pageSpacing = (0).dp
    ) { page ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                    scaleX = lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                    scaleY = lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                },
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .placeholder(
                        visible = true,
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp),
                        highlight = PlaceholderHighlight.shimmer(
                            highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                                alpha = 0.25f
                            )
                        )
                    ),
                painter = painterResource(id = R.drawable.poster),
                contentScale = ContentScale.FillWidth,
                contentDescription = ""
            )
            BasicText(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        alpha = lerp(
                            start = 0.0f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .padding(top = 8.dp)
                    .placeholder(
                        visible = true,
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(4.dp),
                        highlight = PlaceholderHighlight.shimmer(
                            highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                                alpha = 0.25f
                            )
                        )
                    ),
                text = "xxxxxxxxxxxx",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview(showBackground = true)
@ExperimentalFoundationApi
@Composable
fun HomePagerComponentPreview() {
    TMDBMovieTheme {
        HomePagerComponent(
            modifier = Modifier,
            onPageChanged = {},
            movieList = listOf(
                MediaItem(
                    title = "Movie 1",
                    posterPath = "https://image.tmdb.org/t/p/w500/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                    backdropPath = "https://image.tmdb.org/t/p/w500/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                    overview = "Overview 1",
                    releaseDate = "2021-09-01",
                    voteAverage = 8.0f,
                ),
                MediaItem(
                    title = "Movie 2",
                    posterPath = "https://image.tmdb.org/t/p/w500/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                    backdropPath = "https://image.tmdb.org/t/p/w500/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                    overview = "Overview 1",
                    releaseDate = "2021-09-01",
                    voteAverage = 8.0f,
                ),
                MediaItem(
                    title = "Movie 3",
                    posterPath = "https://image.tmdb.org/t/p/w500/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                    backdropPath = "https://image.tmdb.org/t/p/w500/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                    overview = "Overview 1",
                    releaseDate = "2021-09-01",
                    voteAverage = 8.0f,
                ),
                MediaItem(
                    title = "Movie 4",
                    posterPath = "https://image.tmdb.org/t/p/w500/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                    backdropPath = "https://image.tmdb.org/t/p/w500/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                    overview = "Overview 1",
                    releaseDate = "2021-09-01",
                    voteAverage = 8.0f,
                ),
            )
        )
    }
}

@Preview(showBackground = true)
@ExperimentalFoundationApi
@Composable
fun HomePagerComponentPreview1() {
    TMDBMovieTheme {
        HomePagerComponentPlaceholder(modifier = Modifier)
    }
}