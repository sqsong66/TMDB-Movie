package com.tmdb.movie.ui.home.component

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest.Builder
import com.google.accompanist.placeholder.PlaceholderDefaults
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmerHighlightColor
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.tmdb.movie.R
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.MediaItem
import com.tmdb.movie.data.MediaType
import com.tmdb.movie.ui.home.vm.MovieLoadState
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun TrendingComponent(
    modifier: Modifier = Modifier,
    @MediaType mediaType: Int = MediaType.MOVIE,
    title: String,
    trendingState: MovieLoadState<MediaItem>,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
    navigateToMovieDetail: (Int, @MediaType Int) -> Unit = { _, _ -> },
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        )
        if (trendingState is MovieLoadState.Success) {
            LazyRow {
                items(trendingState.data.size) { index ->
                    PosterComponent(
                        modifier = Modifier
                            .padding(
                                top = 16.dp,
                                start = if (index == 0) 16.dp else 8.dp,
                                end = if (index == trendingState.data.size - 1) 16.dp else 8.dp
                            ),
                        mediaType = mediaType,
                        movieItem = trendingState.data[index],
                        onBuildImage = onBuildImage,
                        navigateToMovieDetail = navigateToMovieDetail
                    )
                }
            }
        } else {
            LazyRow {
                items(10) { index ->
                    PosterComponentPlaceholder(
                        Modifier
                            .padding(
                                top = 16.dp,
                                start = if (index == 0) 16.dp else 8.dp,
                                end = if (index == 9) 16.dp else 8.dp
                            ),
                        mediaType = mediaType
                    )
                }
            }
        }
    }
}

@Composable
fun PosterComponent(
    movieItem: MediaItem?,
    modifier: Modifier = Modifier,
    @MediaType mediaType: Int = MediaType.MOVIE,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
    navigateToMovieDetail: (Int, @MediaType Int) -> Unit = { _, _ -> },
) {
    val context = LocalContext.current
    val placeholderBitmap =
        AppCompatResources.getDrawable(
            context,
            if (mediaType == MediaType.MOVIE) R.drawable.image_placeholder else R.drawable.image_placeholder_horizontal
        )?.toBitmap()
            ?.apply {
                eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
            }

    Column(
        modifier = modifier.width(if (mediaType == MediaType.MOVIE) 100.dp else 180.dp),
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .clickable { navigateToMovieDetail(movieItem?.id ?: 0, mediaType) },
            model = Builder(LocalContext.current)
                .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                .data(
                    if (mediaType == MediaType.MOVIE) onBuildImage(
                        movieItem?.posterPath,
                        ImageType.POSTER
                    ) else onBuildImage(
                        movieItem?.backdropPath,
                        ImageType.BACKDROP
                    )
                )
                .crossfade(true)
                .build(),
            contentScale = ContentScale.FillWidth,
            contentDescription = ""
        )
        Text(
            modifier = Modifier
                .padding(top = 4.dp),
            text = movieItem?.getMovieName(mediaType) ?: "",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            maxLines = 1,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
        )
        Row(
            modifier = Modifier
                .padding(top = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1.0f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier,
                    text = String.format("%.1f", movieItem?.voteAverage),
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal)
                )
                RatingBar(
                    modifier = Modifier.padding(start = 4.dp),
                    value = (movieItem?.voteAverage ?: 0f) / (if (mediaType == MediaType.MOVIE) 10 else 2),
                    style = RatingBarStyle.Fill(
                        activeColor = MaterialTheme.colorScheme.primary,
                        inActiveColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                    ),
                    size = 12.dp,
                    numOfStars = if (mediaType == MediaType.MOVIE) 1 else 5,
                    spaceBetween = 1.dp,
                    onValueChange = {},
                    onRatingChanged = {},
                )
            }
            Text(
                modifier = Modifier
                    .padding(start = 4.dp),
                text = movieItem?.getNiceDate(mediaType = mediaType, isFormatShort = mediaType == MediaType.MOVIE)
                    ?: stringResource(id = R.string.key_unknown),
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal)
            )
        }
    }
}

@Composable
fun PosterComponentPlaceholder(
    modifier: Modifier = Modifier,
    @MediaType mediaType: Int = MediaType.MOVIE
) {
    Column(
        modifier = modifier.width(if (mediaType == MediaType.MOVIE) 100.dp else 180.dp),
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
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = if (mediaType == MediaType.MOVIE) R.drawable.poster else R.drawable.backdrop),
            contentDescription = ""
        )
        Text(
            modifier = Modifier
                .padding(top = 6.dp)
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
            text = "Blue Beetle",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp)
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
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
                text = "2023/09/23",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal)
            )
        }
    }
}

@Preview(showBackground = true)
//@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PosterComponentPreview() {
    TMDBMovieTheme {
        PosterComponent(
            movieItem = MediaItem(
                id = 1,
                title = "Blue Beetle",
                posterPath = "/blue_beetle.jpg",
                backdropPath = "/blue_beetle_backdrop.jpg",
                voteAverage = 8.5f,
                releaseDate = "2023-09-23",
                firstAirDate = "2023-09-23",
                mediaType = "movie"
            )
        )
    }
}

@Preview(showBackground = true)
//@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PosterTVComponentPreview() {
    TMDBMovieTheme {
        PosterComponent(
            movieItem = MediaItem(
                id = 1,
                title = "Blue Beetle",
                name = "Blue Beetle",
                posterPath = "/blue_beetle.jpg",
                backdropPath = "/blue_beetle_backdrop.jpg",
                voteAverage = 8.5f,
                releaseDate = "2023-09-23",
                firstAirDate = "2023-09-23",
                mediaType = "tv"
            ),
            mediaType = MediaType.TV
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PosterComponentPreviewPlaceholder() {
    TMDBMovieTheme {
        PosterComponentPlaceholder(mediaType = MediaType.MOVIE)
    }
}