package com.tmdb.movie.ui.tv.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.tmdb.movie.R
import com.tmdb.movie.data.Episode
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.ext.dpToPx
import com.tmdb.movie.ext.pxToDp
import com.tmdb.movie.ui.theme.LocalFixedInsets

private val TITLE_START_PADDING = 16.dp
private val TITLE_END_PADDING = 72.dp
private val TITLE_BOTTOM_PADDING = 8.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodeDetailTopBar(
    modifier: Modifier = Modifier,
    shouldShowBackground: Boolean = false,
    onBackClick: (Boolean) -> Unit,
) {

    TopAppBar(
        modifier = modifier
            .shadow(elevation = if (shouldShowBackground) 1.dp else 0.dp),
        title = { },
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
            containerColor = if (shouldShowBackground) MaterialTheme.colorScheme.background else Color.Transparent,
        )
    )
}

@Composable
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun PreviewEpisodeTopBar() {
    EpisodeDetailTopBar(
        onBackClick = { }
    )
}

@Composable
fun EpisodeHeader(
    modifier: Modifier = Modifier,
    stillPath: String?,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String = { _, _, _ -> "" }
) {

    var headerHeight by remember { mutableIntStateOf(0) }

    val context = LocalContext.current
    val placeholderBitmap =
        AppCompatResources.getDrawable(context, R.drawable.image_placeholder_horizontal)?.toBitmap()?.apply {
            eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
        }

    Box(modifier = modifier.fillMaxWidth()) {
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    headerHeight = it.size.height
                },
            model = ImageRequest.Builder(LocalContext.current)
                .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                .error(BitmapDrawable(context.resources, placeholderBitmap))
                .data(onBuildImage(stillPath, ImageType.STILL, ImageSize.LARGE))
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(headerHeight.pxToDp())
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background.copy(0.0f),
                            MaterialTheme.colorScheme.background.copy(1.0f),
                        ),
                        startY = headerHeight / 2f
                    )
                )
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun PreviewEpisodeHeader() {
    EpisodeHeader(
        stillPath = "",
        onBuildImage = { _, _, _ -> "" },
    )
}

@Composable
fun EpisodeDetailTitle(
    modifier: Modifier = Modifier,
    title: String,
    scrollState: ScrollState,
    headerHeight: Float,
    topBarHeight: Float,
) {

    var titleHeightPx by remember { mutableFloatStateOf(0f) }
    var titleWidthPx by remember { mutableFloatStateOf(0f) }
    val statusBarHeight = LocalFixedInsets.current.statusBarHeight.dpToPx()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(headerHeight.pxToDp())
    ) {
        Text(
            text = title,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier
                .graphicsLayer {
                    val collapseRange: Float = headerHeight - topBarHeight
                    val collapseFraction: Float = (scrollState.value.toFloat() / collapseRange).coerceIn(0f, 1f)

                    val scaleXY = lerp(
                        1.0f,
                        0.66f,
                        collapseFraction
                    )

                    val titleExtraStartPadding = titleWidthPx * (1 - scaleXY) / 2f

                    val titleXFirstInterpolatedPoint = lerp(
                        TITLE_START_PADDING.toPx(),
                        TITLE_END_PADDING.toPx() - titleExtraStartPadding,
                        collapseFraction
                    )

                    val titleYFirstInterpolatedPoint = lerp(
                        headerHeight - TITLE_BOTTOM_PADDING.toPx() - titleHeightPx,
                        headerHeight - TITLE_BOTTOM_PADDING.toPx() - titleHeightPx,
                        collapseFraction
                    )

                    val titleXSecondInterpolatedPoint = lerp(
                        TITLE_END_PADDING.toPx() - titleExtraStartPadding,
                        TITLE_END_PADDING.toPx() - titleExtraStartPadding,
                        collapseFraction
                    )

                    val titleYSecondInterpolatedPoint = lerp(
                        headerHeight - TITLE_BOTTOM_PADDING.toPx() - titleHeightPx,
                        (topBarHeight + statusBarHeight) / 2 - (titleHeightPx) / 2,
                        collapseFraction
                    )

                    val titleY = lerp(
                        titleYFirstInterpolatedPoint,
                        titleYSecondInterpolatedPoint,
                        collapseFraction
                    )

                    val titleX = lerp(
                        titleXFirstInterpolatedPoint,
                        titleXSecondInterpolatedPoint,
                        collapseFraction
                    )

                    translationX = titleX
                    translationY = titleY
                    scaleX = scaleXY
                    scaleY = scaleXY
                }
                .onGloballyPositioned {
                    titleHeightPx = it.size.height.toFloat()
                    titleWidthPx = it.size.width.toFloat()
                }
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun PreviewEpisodeDetailTitle() {
    EpisodeDetailTitle(
        scrollState = rememberScrollState(),
        title = "Loki",
        headerHeight = 500f,
        topBarHeight = 56f,
    )
}

@Composable
fun EpisodeDetailTopLayout(
    modifier: Modifier = Modifier,
    episode: Episode?,
    onImageHeight: (Int) -> Unit = {},
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String = { _, _, _ -> "" }
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        EpisodeHeader(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    onImageHeight(it.size.height)
                },
            stillPath = episode?.stillPath,
            onBuildImage = onBuildImage,
        )

        EpisodeMiddleLayout(
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
            episode = episode,
        )

        HorizontalDivider(
            thickness = 0.5.dp,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 16.dp),
            text = episode?.getEpisodeOverview(LocalContext.current) ?: "",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
        )


    }
}

@Composable
fun EpisodeMiddleLayout(
    modifier: Modifier = Modifier,
    episode: Episode?,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1.0f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                text = episode?.getDuration() ?: "",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1,
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(R.string.key_duration),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }

        Column(
            modifier = Modifier
                .weight(1.0f)
                .align(Alignment.Bottom),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RatingBar(modifier = Modifier,
                    value = (episode?.voteAverage ?: 0f) / 2f,
                    style = RatingBarStyle.Fill(
                        activeColor = MaterialTheme.colorScheme.primary,
                        inActiveColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                    ),
                    size = 12.dp,
                    spaceBetween = 1.dp,
                    numOfStars = 5,
                    onValueChange = {},
                    onRatingChanged = {})
                Text(
                    modifier = Modifier.padding(start = 3.dp),
                    text = "(${String.format("%.1f", episode?.voteAverage)})",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    ),
                )
            }

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(R.string.key_score),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }

        Column(
            modifier = Modifier.weight(1.0f), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                text = episode?.niceAirDate() ?: "",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
                ),
                maxLines = 1
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(id = R.string.key_air_date),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun PreviewEpisodeDetailTopLayout() {
    EpisodeDetailTopLayout(
        episode = Episode(
            name = "Loki",
            stillPath = "",
            airDate = "2021-06-09",
        ),
    )
}
