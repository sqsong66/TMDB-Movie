package com.tmdb.movie.ui.detail.component

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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.tmdb.movie.component.ProfileTitleComponent
import com.tmdb.movie.data.EpisodeToAir
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.Season
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun LatestSeasonComponent(
    modifier: Modifier = Modifier,
    tvName: String,
    episodeToAir: EpisodeToAir?,
    lastSeason: Season,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
    toSeasonDetail: (Int) -> Unit,
    toEpisodeDetail: (Int, Int) -> Unit,
    toSeasonList: () -> Unit,
) {

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        ProfileTitleComponent(
            modifier = Modifier,
            title = stringResource(if (episodeToAir?.isLastEpisode() == true) R.string.key_view_last_season else R.string.key_view_current_season),
            showMoreText = true,
            moreText = stringResource(id = R.string.key_view_all_seasons),
            onMoreTextClick = { toSeasonList() },
        )
        TVSeasonCard(
            tvName = tvName,
            episodeToAir = episodeToAir,
            lastSeason = lastSeason,
            toSeasonDetail = toSeasonDetail,
            toEpisodeDetail = toEpisodeDetail,
            onBuildImage = onBuildImage,
        )
    }
}

@Composable
fun TVSeasonCard(
    modifier: Modifier = Modifier,
    tvName: String,
    episodeToAir: EpisodeToAir?,
    lastSeason: Season,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
    toSeasonDetail: (Int) -> Unit,
    toEpisodeDetail: (Int, Int) -> Unit,
) {
    val context = LocalContext.current
    var isImageError by rememberSaveable { mutableStateOf(false) }
    val placeholderBitmap = AppCompatResources.getDrawable(context, R.drawable.image_placeholder)?.toBitmap()?.apply {
        eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(top = 12.dp, start = 16.dp, end = 16.dp)
            .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.small)
            .clickable {
                toSeasonDetail(lastSeason.seasonNumber)
            },
        shape = MaterialTheme.shapes.small,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxHeight(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                        .error(BitmapDrawable(context.resources, placeholderBitmap))
                        .data(onBuildImage(lastSeason.posterPath, ImageType.POSTER, ImageSize.MEDIUM))
                        .crossfade(true)
                        .listener(onError = { _, _ ->
                            isImageError = true
                        })
                        .build(),
                    contentScale = ContentScale.FillHeight,
                    contentDescription = null,
                )
                if (isImageError) {
                    Image(
                        modifier = Modifier
                            .size(45.dp)
                            .align(Alignment.Center),
                        painter = painterResource(id = R.drawable.baseline_imagesmode_24),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = lastSeason.name ?: "",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    if (episodeToAir?.isLastEpisode() == true) {
                        Text(
                            modifier = Modifier
                                .padding(start = 4.dp, end = 16.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .padding(horizontal = 6.dp, vertical = 3.dp),
                            text = stringResource(id = R.string.key_season_finale),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onPrimary,
                            ),
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (lastSeason.voteAverage != 0f) {
                        RatingBar(
                            modifier = Modifier,
                            value = lastSeason.voteAverage / 10,
                            style = RatingBarStyle.Fill(
                                activeColor = MaterialTheme.colorScheme.primary,
                                inActiveColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                            ),
                            size = 14.dp,
                            numOfStars = 1,
                            spaceBetween = 1.dp,
                            onValueChange = {},
                            onRatingChanged = {},
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 3.dp, end = 10.dp),
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                    )
                                ) {
                                    append(String.format("%.1f", lastSeason.voteAverage))
                                }
                            },
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = MaterialTheme.colorScheme.onBackground,
                            ),
                        )
                    }

                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 16.dp),
                        text = String.format(
                            stringResource(R.string.key_year_episodes),
                            lastSeason.getTVAirYear(),
                            lastSeason.episodeCount
                        ),
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Start
                    )
                }

                Text(
                    text = if (!lastSeason.overview.isNullOrEmpty()) {
                        lastSeason.getSeasonOverview(context = LocalContext.current)
                    } else {
                        String.format(context.getString(R.string.key_season_desc), lastSeason.name, tvName, lastSeason.niceAirDate())
                    },
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                        .weight(1f),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, start = 16.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(14.dp),
                        painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    )
                    val lineColor = MaterialTheme.colorScheme.primary
                    Text(
                        modifier = Modifier
                            .weight(1f, false)
                            .padding(start = 4.dp, end = 4.dp, top = 6.dp, bottom = 6.dp)
                            .drawBehind {
                                val strokeWidth = 1.dp.toPx()
                                drawLine(
                                    color = lineColor,
                                    start = Offset(x = 0f, y = size.height + strokeWidth),
                                    end = Offset(x = size.width, y = size.height + strokeWidth),
                                    strokeWidth = strokeWidth
                                )
                            }
                            .clip(RoundedCornerShape(4.dp))
                            .clickable {
                                toEpisodeDetail(lastSeason.seasonNumber, episodeToAir?.episodeNumber ?: 0)
                            },
                        text = episodeToAir?.name ?: "",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 4.dp, top = 4.dp, bottom = 4.dp, end = 16.dp),
                        text = buildAnnotatedString {
                            append("(${episodeToAir?.seasonNumber}x${episodeToAir?.episodeNumber},  ")
                            append("${episodeToAir?.niceAirDate()})")
                        },
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        ),
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun LatestSeasonComponentPreview() {
    TMDBMovieTheme {
        LatestSeasonComponent(
            modifier = Modifier.padding(bottom = 16.dp),
            tvName = "Game of Thrones",
            episodeToAir = EpisodeToAir(
                seasonNumber = 8,
                episodeNumber = 6,
                airDate = "2019-05-19",
                name = "The Iron Throne",
                episodeType = "finale",
            ),
            lastSeason = Season(
                name = "Season 8",
                posterPath = "/3OcQhbrecf4F4pYss2gSirTGPvD.jpg",
                overview = "The Great War has come, the Wall has fallen and the Night King's army of the dead marches towards Westeros. The end is here, but who will take the Iron Throne?",
                seasonNumber = 8,
                episodeCount = 6,
                voteAverage = 6.4f,
                airDate = "2019-04-14"
            ),
            toSeasonDetail = { },
            toEpisodeDetail = { _, _ -> },
            toSeasonList = { },
        )
    }
}


@Composable
fun LatestSeasonComponentPlaceholder(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = R.string.key_view_last_season),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 6.dp)
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
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(top = 12.dp, start = 16.dp, end = 16.dp),
            shape = MaterialTheme.shapes.small,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .placeholder(
                            visible = true,
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                            highlight = PlaceholderHighlight.shimmer(
                                highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                                    alpha = 0.25f
                                )
                            )
                        ),
                    painter = painterResource(id = R.drawable.poster),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = 8.dp, start = 16.dp)
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
                        text = "xxxxxxxxxxxxxx",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                        ),
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, start = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
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
                            text = "xxxxxxxxxxxxxx",
                            style = MaterialTheme.typography.labelSmall,
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            modifier = Modifier
                                .padding(start = 3.dp, end = 16.dp)
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
                            text = "xxxxxxxxxxx",
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }

                    repeat(2) { index ->
                        Text(
                            text = "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, top = if (index == 0) 8.dp else 3.dp, end = 16.dp)
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
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                    Text(
                        text = "xxxxxxxxxxxxxxxxxx",
                        modifier = Modifier
                            .padding(start = 16.dp, top = 3.dp, end = 16.dp)
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
                        style = MaterialTheme.typography.bodySmall,
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, start = 16.dp, bottom = 8.dp, end = 16.dp)
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
                        style = MaterialTheme.typography.labelSmall,
                        text = "xxxxxxxx",
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun LatestSeasonComponentPlaceholderPreview() {
    TMDBMovieTheme {
        LatestSeasonComponentPlaceholder(
            modifier = Modifier.padding(bottom = 16.dp),
        )
    }
}