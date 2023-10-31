package com.tmdb.movie.ui.tv.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.Season
import com.tmdb.movie.ext.pxToDp
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeasonDetailTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: (Boolean) -> Unit,
) {

    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                color = Color.Transparent
            )
        },
        navigationIcon = {
            Box(modifier = Modifier
                .padding(8.dp)
                .background(
                    MaterialTheme.colorScheme.background.copy(alpha = 0.8f),
                    shape = CircleShape,
                )
                .clip(CircleShape)
                .clickable { onBackClick(false) }) {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = null,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )

}

@Composable
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun SeasonDetailTopBarPreview() {
    TMDBMovieTheme {
        SeasonDetailTopBar(
            onBackClick = {},
            title = "Season 1",
        )
    }
}

@Composable
fun SeasonDetailHeader(
    modifier: Modifier = Modifier,
    tvName: String,
    season: Season,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
) {
    var imageHeight by remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    val placeholderBitmap = AppCompatResources.getDrawable(context, R.drawable.image_placeholder)?.toBitmap()?.apply {
        eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
    }

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    imageHeight = it.size.height
                },
            model = ImageRequest.Builder(LocalContext.current)
                .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                .error(BitmapDrawable(context.resources, placeholderBitmap))
                .data(onBuildImage(season.posterPath, ImageType.POSTER, ImageSize.LARGE))
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(imageHeight.pxToDp() / 2)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background.copy(alpha = 0.0f),
                            MaterialTheme.colorScheme.background,
                        ),
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = season.name ?: "",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground
                ),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                if (!season.airDate.isNullOrEmpty()) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.baseline_schedule_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                    )
                    Text(
                        modifier = Modifier.padding(start = 4.dp, end = 16.dp),
                        text = season.niceAirDate(),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                        ),
                    )
                }

                RatingBar(
                    modifier = Modifier,
                    value = season.voteAverage / 2,
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
                    modifier = Modifier.padding(start = 4.dp, end = 10.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                            )
                        ) {
                            append(String.format("%.1f", season.voteAverage))
                        }
                        append(" /10")
                    },
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    ),
                )
            }

            Text(
                text = String.format(LocalContext.current.getString(R.string.key_season_desc), season.name, tvName, season.niceAirDate()),
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
            )

            if (!season.overview.isNullOrEmpty()) {
                Text(
                    text = season.getSeasonOverview(context = LocalContext.current),
                    modifier = Modifier.padding(start = 16.dp, top = 6.dp, end = 16.dp),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    ),
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun SeasonDetailHeaderPreview() {
    TMDBMovieTheme {
        SeasonDetailHeader(
            tvName = "Loki", season = Season(
                id = 1,
                name = "Season 1",
                overview = "Loki, the God of Mischief, steps out of his brother's shadow to embark on an adventure that takes place after the events of \"Avengers: Endgame.\"",
                posterPath = "/poster",
                seasonNumber = 1,
                airDate = "2021-01-01",
                episodeCount = 10,
                voteAverage = 8.0f,
            )
        )
    }
}