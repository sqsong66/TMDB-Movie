package com.tmdb.movie.ui.tv.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.tmdb.movie.R
import com.tmdb.movie.data.Episode
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.SeasonDetailParam
import com.tmdb.movie.ext.pxToDp
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun SeasonEpisodeItem(
    modifier: Modifier = Modifier,
    episode: Episode,
    toEpisodeDetail: (Int, Int) -> Unit,
    placeholderColor: Color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url }
) {
    val context = LocalContext.current
    val placeholder by remember {
        val placeholderBitmap = AppCompatResources.getDrawable(context, R.drawable.image_placeholder_horizontal)?.toBitmap()
            ?.apply { eraseColor(placeholderColor.toArgb()) }
        mutableStateOf(BitmapDrawable(context.resources, placeholderBitmap))
    }

    var imageHeight by remember { mutableIntStateOf(0) }
    var isImageError by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium)
            .clickable {
                toEpisodeDetail(episode.seasonNumber, episode.episodeNumber)
            },
    ) {

        Column {
            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned {
                            imageHeight = it.size.height
                        },
                    model = ImageRequest.Builder(LocalContext.current)
                        .placeholder(placeholder)
                        .error(placeholder)
                        .data(onBuildImage(episode.stillPath, ImageType.STILL, ImageSize.LARGE))
                        .crossfade(true)
                        .listener(onError = { _, _ ->
                            isImageError = true
                        })
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight.pxToDp())
                        .align(Alignment.BottomCenter)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.surfaceVariant.copy(0f),
                                    MaterialTheme.colorScheme.surfaceVariant,
                                ),
                                startY = imageHeight / 2f,
                            )
                        ),
                )
                if (isImageError) {
                    Image(
                        modifier = Modifier
                            .padding(top = 56.dp)
                            .size(45.dp)
                            .align(Alignment.TopCenter),
                        painter = painterResource(id = R.drawable.baseline_imagesmode_24),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                ) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = "${episode.episodeNumber} ${episode.name}",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Bold
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RatingBar(
                            modifier = Modifier,
                            value = episode.voteAverage / 2,
                            style = RatingBarStyle.Fill(
                                activeColor = MaterialTheme.colorScheme.primary,
                                inActiveColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                            ),
                            size = 14.dp,
                            numOfStars = 5,
                            spaceBetween = 1.dp,
                            onValueChange = {},
                            onRatingChanged = {},
                        )
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                    )
                                ) {
                                    append(String.format("%.1f", episode.voteAverage))
                                }
                                append(" /10")
                            },
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                            ),
                        )

                        if (!episode.airDate.isNullOrEmpty()) {
                            Icon(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .size(16.dp),
                                painter = painterResource(id = R.drawable.baseline_schedule_24),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                            )
                            Text(
                                modifier = Modifier.padding(start = 4.dp),
                                text = episode.niceAirDate(),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                                ),
                            )
                        }

                        if (episode.runtime != 0) {
                            Text(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .padding(horizontal = 8.dp, vertical = 2.dp),
                                text = episode.getDuration(),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            )
                        }
                    }
                }
            }

            Text(
                modifier = Modifier.padding(start = 16.dp, top = 6.dp, bottom = 8.dp, end = 16.dp),
                text = episode.getEpisodeOverview(LocalContext.current),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = false)
fun SeasonEpisodeItemPreview() {
    val episodes = mutableListOf<Episode>()
    repeat(5) {
        episodes.add(
            Episode(
                id = it,
                name = "Episode",
                overview = "With the TVA on the verge of a temporal meltdown, Loki & Mobius will stop at nothing to find Sylvie.",
                stillPath = null,
                voteAverage = 8.0f,
                voteCount = 0,
                airDate = "2021-09-01",
                episodeNumber = it + 1,
                seasonNumber = 1,
                runtime = 45,
            )
        )
    }
    TMDBMovieTheme {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(episodes.size) { index ->
                SeasonEpisodeItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp, end = 16.dp, top = 16.dp, bottom = if (index == episodes.size - 1) 16.dp else 0.dp
                        ),
                    episode = episodes[index],
                    onBuildImage = { _, _, _ -> null },
                    toEpisodeDetail = { _, _ -> }
                )
            }
        }
    }
}