package com.tmdb.movie.ui.account.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
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
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.MediaItem
import com.tmdb.movie.data.MediaType
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun AccountMediaItem(
    modifier: Modifier,
    @MediaType mediaType: Int,
    mediaItem: MediaItem,
    toMediaDetail: (Int, Int) -> Unit,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
) {
    val context = LocalContext.current
    val placeholderBitmap =
        AppCompatResources.getDrawable(
            context,
            if (mediaType == MediaType.MOVIE) R.drawable.image_placeholder else R.drawable.image_placeholder_horizontal
        )?.toBitmap()?.apply {
            eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
        }
    Column(modifier = modifier) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small)
                .clickable {
                    toMediaDetail(mediaItem.id, mediaType)
                },
            model = ImageRequest.Builder(LocalContext.current)
                .data(
                    if (mediaType == MediaType.MOVIE) {
                        onBuildImage(
                            mediaItem.posterPath,
                            ImageType.POSTER,
                            ImageSize.MEDIUM
                        )
                    } else {
                        onBuildImage(
                            mediaItem.backdropPath,
                            ImageType.BACKDROP,
                            ImageSize.MEDIUM
                        )
                    }
                )
                .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                .error(BitmapDrawable(context.resources, placeholderBitmap))
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, end = 8.dp),
            text = mediaItem.getMovieName(mediaType),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                lineHeight = 14.sp,
                textAlign = TextAlign.Start
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier.padding(bottom = 8.dp, top = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingBar(
                modifier = Modifier,
                value = mediaItem.voteAverage / 2,
                style = RatingBarStyle.Fill(
                    activeColor = MaterialTheme.colorScheme.primary,
                    inActiveColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                ),
                size = 12.dp,
                spaceBetween = 2.dp,
                onValueChange = {},
                onRatingChanged = {},
            )
            BasicText(
                modifier = Modifier
                    .padding(start = 6.dp, end = 4.dp),
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(String.format("%.1f", mediaItem.voteAverage))
                    }
                    append("/10")
                },
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                ),
            )
        }
    }
}

@Composable
fun AccountMediaItemPlaceholder(
    modifier: Modifier,
    @MediaType mediaType: Int,

    ) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small)
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
            painter = painterResource(id = if (mediaType == MediaType.MOVIE) R.drawable.poster else R.drawable.backdrop),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, end = 8.dp)
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
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                lineHeight = 14.sp,
                textAlign = TextAlign.Start
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier.padding(bottom = 8.dp, top = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingBar(
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
                value = 0.5f,
                style = RatingBarStyle.Fill(
                    activeColor = MaterialTheme.colorScheme.primary,
                    inActiveColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                ),
                size = 12.dp,
                spaceBetween = 2.dp,
                onValueChange = {},
                onRatingChanged = {},
            )
            BasicText(
                modifier = Modifier
                    .padding(start = 6.dp, end = 4.dp)
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
                text = "xxxxx",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                ),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun AccountMediaItemPreview() {
    TMDBMovieTheme {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(10) { index ->
                /*AccountMediaItem(
                    modifier = Modifier.padding(
                        top = if (index < 2) 16.dp else 0.dp,
                        start = if (index % 2 == 0) 16.dp else 8.dp,
                        end = if (index % 2 == 1) 16.dp else 8.dp,
                        bottom = 16.dp
                    ),
                    mediaType = MediaType.TV,
                    mediaItem = MediaItem(
                        id = index,
                        title = "Title $index",
                        name = "Name $index",
                        posterPath = null,
                        backdropPath = null,
                        overview = "Overview $index",
                        releaseDate = "2021-09-01",
                        voteAverage = 7.5f,
                        voteCount = 100,
                        mediaType = "movie",
                    ),
                )*/
                AccountMediaItemPlaceholder(
                    modifier = Modifier.padding(
                        top = if (index < 2) 16.dp else 0.dp,
                        start = if (index % 2 == 0) 16.dp else 8.dp,
                        end = if (index % 2 == 1) 16.dp else 8.dp,
                        bottom = 16.dp
                    ),
                    mediaType = MediaType.TV,
                )
            }
        }
    }
}