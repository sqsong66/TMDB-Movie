package com.tmdb.movie.ui.discovery.component

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.placeholder.PlaceholderDefaults
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmerHighlightColor
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.tmdb.movie.R
import com.tmdb.movie.component.ErrorPage
import com.tmdb.movie.component.LoadingError
import com.tmdb.movie.component.LoadingFooter
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.MediaItem
import com.tmdb.movie.data.MediaType
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun DiscoveryMovieListComponent(
    modifier: Modifier = Modifier,
    topHeight: Dp,
    movieList: LazyPagingItems<MediaItem>? = null,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
    toDetail: (MediaItem?, Int) -> Unit = { _, _ -> },
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item { Spacer(modifier = Modifier.height(topHeight + 8.dp)) }
        items(movieList?.itemCount ?: 0) { index ->
            DiscoveryMovieComponent(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                movieItem = movieList?.get(index),
                onBuildImage = onBuildImage,
                toDetail = toDetail,
            )
        }

        when (movieList?.loadState?.refresh) {
            is LoadState.Error -> item {
                ErrorPage(
                    modifier = Modifier.padding(top = 120.dp),
                    onRetry = { movieList.retry() })
            }

            LoadState.Loading -> {
                items(10) {
                    DiscoveryMovieComponentPlaceholder(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                }
            }

            else -> {}
        }

        when (movieList?.loadState?.append) {
            is LoadState.Error -> item { LoadingError { movieList.retry() } }
            LoadState.Loading -> item { LoadingFooter() }
            else -> {}
        }
        item { Spacer(modifier = Modifier.height(80.dp)) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoveryMovieComponent(
    modifier: Modifier = Modifier,
    movieItem: MediaItem?,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
    toDetail: (MediaItem?, @MediaType Int) -> Unit = { _, _ -> },
) {
    val context = LocalContext.current
    val placeholderBitmap =
        AppCompatResources.getDrawable(context, R.drawable.image_placeholder)?.toBitmap()
            ?.apply {
                eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
            }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { },
        shape = MaterialTheme.shapes.medium,
        onClick = { toDetail(movieItem, MediaType.MOVIE) }
    ) {
        Row {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                    .error(BitmapDrawable(context.resources, placeholderBitmap))
                    .data(onBuildImage(movieItem?.posterPath ?: "", ImageType.POSTER))
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.FillHeight,
                contentDescription = ""
            )
            Column(modifier = Modifier.padding(start = 16.dp, top = 8.dp)) {
                BasicText(
                    text = movieItem?.getMovieName(MediaType.MOVIE) ?: "",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                BasicText(
                    modifier = Modifier
                        .padding(top = 8.dp, end = 16.dp)
                        .weight(1f),
                    text = movieItem?.getMovieOverview(context) ?: "",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                    ),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.padding(bottom = 8.dp, top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicText(
                        modifier = Modifier
                            .padding(end = 4.dp),
                        text = String.format("%.1f", movieItem?.voteAverage),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                        ),
                    )
                    RatingBar(
                        modifier = Modifier.weight(1.0f),
                        value = (movieItem?.voteAverage ?: 0f) / 2,
                        style = RatingBarStyle.Fill(
                            activeColor = MaterialTheme.colorScheme.primary,
                            inActiveColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                        ),
                        size = 12.dp,
                        spaceBetween = 2.dp,
                        onValueChange = {},
                        onRatingChanged = {})
                    BasicText(
                        modifier = Modifier
                            .padding(end = 16.dp),
                        text = movieItem?.getNiceDate(MediaType.MOVIE, false) ?: stringResource(id = R.string.key_unknown),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                        ),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoveryMovieComponentPlaceholder(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = MaterialTheme.shapes.medium,
        onClick = { }
    ) {
        Row {
            Image(
                modifier = Modifier.placeholder(
                    visible = true,
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp),
                    highlight = PlaceholderHighlight.shimmer(
                        highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                            alpha = 0.25f
                        )
                    )
                ),
                painter = painterResource(id = R.drawable.poster),
                contentDescription = "",
                contentScale = ContentScale.FillHeight,
            )
            Column(modifier = Modifier.padding(start = 16.dp, top = 8.dp)) {
                BasicText(
                    modifier = Modifier.placeholder(
                        visible = true,
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(4.dp),
                        highlight = PlaceholderHighlight.shimmer(
                            highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                                alpha = 0.25f
                            )
                        )
                    ),
                    text = "Tagesschau",
                    style = MaterialTheme.typography.titleMedium
                )

                BasicText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, end = 16.dp)
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
                    text = "",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                BasicText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, end = 16.dp)
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
                    text = "",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                BasicText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, end = 16.dp)
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
                    text = "",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1.0f))
                Row(
                    modifier = Modifier.padding(bottom = 8.dp, top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicText(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .weight(1.0f)
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
                        text = "4.5",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    BasicText(
                        modifier = Modifier
                            .padding(end = 16.dp)
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
                        text = "2022-11-28",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DiscoveryMovieComponentPreview() {
    TMDBMovieTheme {
        DiscoveryMovieComponent(Modifier, null)
    }
}

@Preview
@Composable
fun DiscoveryMovieComponentPlaceholderPreview() {
    TMDBMovieTheme {
        DiscoveryMovieComponentPlaceholder()
    }
}