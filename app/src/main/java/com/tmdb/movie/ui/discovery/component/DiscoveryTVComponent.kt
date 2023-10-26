package com.tmdb.movie.ui.discovery.component

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
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
fun DiscoveryTVListComponent(
    modifier: Modifier = Modifier,
    topHeight: Dp,
    movieList: LazyPagingItems<MediaItem>? = null,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
    toDetail: (MediaItem?, Int) -> Unit = { _, _ -> },
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
    ) {
        item(
            span = {
                GridItemSpan(2)
            }
        ) { Spacer(modifier = Modifier.height(topHeight + 8.dp)) }
        items(movieList?.itemCount ?: 0) { index ->
            DiscoveryTVComponent(
                modifier = Modifier.padding(
                    start = if (index % 2 == 0) 16.dp else 8.dp,
                    end = if (index % 2 == 0) 8.dp else 16.dp,
                    top = 8.dp,
                    bottom = 8.dp
                ),
                movieItem = movieList?.get(index)!!,
                onBuildImage = onBuildImage,
                toDetail = toDetail,
            )
        }

        when (movieList?.loadState?.refresh) {
            is LoadState.Error -> item(
                span = {
                    GridItemSpan(2)
                }
            ) {
                ErrorPage(
                    modifier = Modifier.padding(top = 120.dp),
                    onRetry = { movieList.retry() })
            }

            LoadState.Loading -> {
                items(20) {
                    DiscoveryTVComponentPlaceholder(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                }
            }

            else -> {}
        }

        when (movieList?.loadState?.append) {
            is LoadState.Error -> item(
                span = {
                    GridItemSpan(2)
                }
            ) { LoadingError { movieList.retry() } }

            LoadState.Loading -> item(
                span = {
                    GridItemSpan(2)
                }
            ) { LoadingFooter() }

            else -> {}
        }

        item(
            span = {
                GridItemSpan(2)
            }
        ) { Spacer(modifier = Modifier.height(80.dp)) }
    }
}

@Composable
fun DiscoveryTVComponent(
    modifier: Modifier = Modifier,
    movieItem: MediaItem,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
    toDetail: (MediaItem?, @MediaType Int) -> Unit = { _, _ -> }
) {
    val context = LocalContext.current
    val placeholderBitmap =
        AppCompatResources.getDrawable(context, R.drawable.image_placeholder)?.toBitmap()
            ?.apply {
                eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
            }
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.medium)
                .clickable { toDetail(movieItem, MediaType.TV) },
            model = ImageRequest.Builder(LocalContext.current)
                .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                .error(BitmapDrawable(context.resources, placeholderBitmap))
                .data(onBuildImage(movieItem.posterPath ?: "", ImageType.POSTER))
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.FillWidth
        )
        BasicText(
            modifier = Modifier.padding(top = 8.dp),
            text = movieItem.getMovieName(MediaType.TV) ?: "",
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onBackground,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        BasicText(
            modifier = Modifier.padding(top = 8.dp),
            text = movieItem.getMovieOverview(context),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier.padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicText(
                modifier = Modifier
                    .padding(end = 4.dp),
                text = String.format("%.1f", movieItem.voteAverage),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                ),
            )
            RatingBar(
                modifier = Modifier.weight(1.0f),
                value = movieItem.voteAverage / 2,
                style = RatingBarStyle.Fill(
                    activeColor = MaterialTheme.colorScheme.primary,
                    inActiveColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                ),
                size = 12.dp,
                spaceBetween = 2.dp,
                numOfStars = 1,
                onValueChange = {},
                onRatingChanged = {})
            BasicText(
                modifier = Modifier,
                text = movieItem.getNiceDate(mediaType = MediaType.TV, isFormatShort = false) ?: stringResource(id = R.string.key_unknown),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                ),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun DiscoveryTVComponentPlaceholder(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .placeholder(
                    visible = true,
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                    shape = MaterialTheme.shapes.medium,
                    highlight = PlaceholderHighlight.shimmer(
                        highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                            alpha = 0.25f
                        )
                    )
                ),
            painter = painterResource(id = R.drawable.poster),
            contentDescription = "",
            contentScale = ContentScale.FillWidth
        )
        BasicText(
            modifier = Modifier
                .width(180.dp)
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
            text = "",
            style = MaterialTheme.typography.titleMedium,
        )
        BasicText(
            modifier = Modifier
                .fillMaxWidth()
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
            text = "",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            ),
        )
        Row(
            modifier = Modifier.padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicText(
                modifier = Modifier
                    .width(30.dp)
                    .padding(end = 4.dp)
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
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.weight(1f))
            BasicText(
                modifier = Modifier
                    .width(80.dp)
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
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiscoveryTVComponentPreview() {
    TMDBMovieTheme {
        DiscoveryTVComponent(
            movieItem = MediaItem(
                name = "The Continental: From the World of John Wick",
                voteAverage = 7.708f,
                posterPath = "/v1YEOdGptCyNxnc4mJSYNd4cE8E.jpg",
                firstAirDate = "2023-09-22",
                mediaType = "tv",
                overview = "Winston Scott is roped into a world of assassins and must make things right after his brother's attack on the Continental hotel."
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DiscoveryTVComponentPlaceholderPreview() {
    TMDBMovieTheme {
        DiscoveryTVComponentPlaceholder()
    }
}