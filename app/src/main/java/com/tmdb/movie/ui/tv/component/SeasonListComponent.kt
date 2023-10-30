package com.tmdb.movie.ui.tv.component

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
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
import androidx.compose.ui.util.lerp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.google.android.renderscript.Toolkit
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.tmdb.movie.R
import com.tmdb.movie.component.rememberCurrentOffset
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.Season
import com.tmdb.movie.ext.dpToPx
import com.tmdb.movie.ext.pxToDp
import com.tmdb.movie.ui.theme.LocalFixedInsets
import com.tmdb.movie.ui.theme.TMDBMovieTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private val titlePaddingStart = 16.dp
private val titlePaddingEnd = 56.dp
private val paddingMedium = 8.dp

private const val titleFontScaleStart = 1f
private const val titleFontScaleEnd = 0.66f

private val IMAGE_START_PADDING = 16.dp
private val IMAGE_BOTTOM_PADDING = 8.dp
private const val IMAGE_START_SCALE = 1f
private const val IMAGE_END_SCALE = 0.25f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeasonListTopBar(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    topBarBottom: Float,
    onBackClick: (Boolean) -> Unit,
) {
    val scrollValue by rememberCurrentOffset(listState)
    val elevation = if (scrollValue > topBarBottom) 1.dp else 0.dp
    TopAppBar(
        modifier = modifier
            .shadow(elevation),
        title = {
            // Text(text = "Seasons")
        },
        navigationIcon = {
            IconButton(onClick = {
                onBackClick(false)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = ""
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = if (elevation > 0.dp) 1f else 0f),
        )
    )
}

@Composable
@Preview(showBackground = true)
fun SeasonListTopBarPreview() {
    TMDBMovieTheme {
        SeasonListTopBar(listState = LazyListState(), topBarBottom = 0f, onBackClick = {})
    }
}

@Composable
fun SeasonListItem(
    modifier: Modifier = Modifier,
    tvName: String,
    season: Season,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
    toSeasonDetail: (Int) -> Unit,
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
            .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.small)
            .clickable {
                toSeasonDetail(season.seasonNumber)
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
                    model = ImageRequest.Builder(LocalContext.current).placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                        .error(BitmapDrawable(context.resources, placeholderBitmap))
                        .data(onBuildImage(season.posterPath, ImageType.POSTER, ImageSize.MEDIUM)).crossfade(true).listener(onError = { _, _ ->
                            isImageError = true
                        }).build(),
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
                        .padding(top = 8.dp, start = 16.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = season.name ?: "",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, start = 16.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    if (season.voteAverage != 0f) {
                        RatingBar(
                            modifier = Modifier,
                            value = season.voteAverage / 10,
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
                            modifier = Modifier.padding(start = 3.dp, end = 10.dp),
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
                            },
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = MaterialTheme.colorScheme.onBackground,
                            ),
                        )
                    }

                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 16.dp), text = String.format(
                            stringResource(R.string.key_year_episodes), season.getTVAirYear(), season.episodeCount
                        ), style = MaterialTheme.typography.labelMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f), fontWeight = FontWeight.Bold
                        ), textAlign = TextAlign.Start
                    )
                }

                Text(
                    text = if (!season.overview.isNullOrEmpty()) {
                        season.getSeasonOverview(context = LocalContext.current)
                    } else {
                        String.format(context.getString(R.string.key_season_desc), season.name, tvName, season.niceAirDate())
                    },
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                        .weight(1f),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    ),
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SeasonListItemPreview() {
    TMDBMovieTheme {
        SeasonListItem(
            tvName = "The Flash",
            season = Season(
                airDate = "2021-07-20",
                episodeCount = 18,
                id = 60735,
                name = "Season 7",
                overview = "After a thrilling cliffhanger last season which saw the new Mirror Master victorious and still-at-large in Central City, The Flash must regroup in order to stop her and find a way to make contact with his missing wife, Iris West-Allen. With help from the rest of Team Flash, which includes superheroes Caitlin Snow, Cisco Ramon, Ralph Dibny and Nash Wells, as well as the Flash’s adoptive father Joe West, Meta-Attorney Cecile Horton, tough cub reporter Allegra Garcia and brilliant tech-nerd Chester P. Runk… Flash will ultimately defeat Mirror Master. But in doing so, he’ll also unleash an even more powerful and devastating threat on Central City: one that threatens to tear his team—and his marriage—apart.",
                posterPath = "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                seasonNumber = 7,
                voteAverage = 7.9f,
            ),
            toSeasonDetail = {},
        )
    }
}

@Composable
fun SeasonListHeader(
    modifier: Modifier,
    backdropPath: String?,
    headerHeight: Float,
    listState: LazyListState,
    scaleFactor: Int = 1,
    blurRadius: Int = 12,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
) {
    val scrollValue by rememberCurrentOffset(listState)
    val context = LocalContext.current
    var blurBgBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val imagePath = onBuildImage(backdropPath, ImageType.BACKDROP, ImageSize.SMALL)

    LaunchedEffect(key1 = imagePath) {
        if (!imagePath.isNullOrEmpty()) {
            val bitmap = withContext(Dispatchers.IO) {
                val b = Glide.with(context).asBitmap().load(imagePath).submit().get()
                val destBitmap = Bitmap.createScaledBitmap(b, b.width / scaleFactor, b.height / scaleFactor, true)
                Log.w(
                    "sqsong",
                    "image path : $imagePath, bitmap size: ${b.width} * ${b.height}, dest bitmap size: ${destBitmap.width} * ${destBitmap.height}"
                )
                b.recycle()
                Toolkit.blur(destBitmap, blurRadius)
            }
            blurBgBitmap = bitmap
        }
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        blurBgBitmap?.let { bitmap ->
            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .graphicsLayer {
                        translationY = -scrollValue.toFloat() / 2
                        alpha = (-1f / headerHeight) * scrollValue + 1
                    }, bitmap = bitmap.asImageBitmap(), contentDescription = null, contentScale = ContentScale.Crop
            )
        } ?: Spacer(
            modifier = modifier
                .fillMaxWidth()
                .height(260.dp)
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
                        )
                    )
                )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SeasonListHeaderPreview() {
    TMDBMovieTheme {
        SeasonListHeader(
            modifier = Modifier,
            backdropPath = "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
            headerHeight = 0f,
            listState = LazyListState(),
        )
    }
}

@Composable
fun SeasonListBody(
    modifier: Modifier,
    tvName: String,
    listState: LazyListState,
    headerHeight: Float,
    seasons: List<Season>?,
    toSeasonDetail: (Int) -> Unit,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
) {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState,
    ) {
        item { Spacer(modifier = Modifier.height(headerHeight.pxToDp())) }
        items(seasons?.size ?: 0) { index ->
            seasons?.get(index)?.let { season ->
                SeasonListItem(
                    modifier = Modifier.padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = if (index == seasons.size - 1) 16.dp else 0.dp
                    ),
                    tvName = tvName,
                    season = season,
                    toSeasonDetail = { toSeasonDetail(season.seasonNumber) },
                    onBuildImage = onBuildImage,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SeasonListBodyPreview() {
    TMDBMovieTheme {
        SeasonListBody(
            modifier = Modifier, tvName = "The Flash",
            listState = LazyListState(),
            headerHeight = 200f,
            toSeasonDetail = {},
            seasons = listOf(
                Season(
                    airDate = "2021-07-20",
                    episodeCount = 18,
                    id = 60735,
                    name = "Season 7",
                    overview = "After a thrilling cliffhanger last season which saw the new Mirror Master victorious and still-at-large in Central City, The Flash must regroup in order to stop her and find a way to make contact with his missing wife, Iris West-Allen. With help from the rest of Team Flash, which includes superheroes Caitlin Snow, Cisco Ramon, Ralph Dibny and Nash Wells, as well as the Flash’s adoptive father Joe West, Meta-Attorney Cecile Horton, tough cub reporter Allegra Garcia and brilliant tech-nerd Chester P. Runk… Flash will ultimately defeat Mirror Master. But in doing so, he’ll also unleash an even more powerful and devastating threat on Central City: one that threatens to tear his team—and his marriage—apart.",
                    posterPath = "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                    seasonNumber = 7,
                    voteAverage = 7.9f,
                ),
                Season(
                    airDate = "2021-07-20",
                    episodeCount = 18,
                    id = 60735,
                    name = "Season 7",
                    overview = "After a thrilling cliffhanger last season which saw the new Mirror Master victorious and still-at-large in Central City, The Flash must regroup in order to stop her and find a way to make contact with his missing wife, Iris West-Allen. With help from the rest of Team Flash, which includes superheroes Caitlin Snow, Cisco Ramon, Ralph Dibny and Nash Wells, as well as the Flash’s adoptive father Joe West, Meta-Attorney Cecile Horton, tough cub reporter Allegra Garcia and brilliant tech-nerd Chester P. Runk… Flash will ultimately defeat Mirror Master. But in doing so, he’ll also unleash an even more powerful and devastating threat on Central City: one that threatens to tear his team—and his marriage—apart.",
                    posterPath = "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                    seasonNumber = 7,
                    voteAverage = 7.9f,
                ),
                Season(
                    airDate = "2021-07-20",
                    episodeCount = 18,
                    id = 60735,
                    name = "Season 7",
                    overview = "After a thrilling cliffhanger last season which saw the new Mirror Master victorious and still-at-large in Central City, The Flash must regroup in order to stop her and find a way to make contact with his missing wife, Iris West-Allen. With help from the rest of Team Flash, which includes superheroes Caitlin Snow, Cisco Ramon, Ralph Dibny and Nash Wells, as well as the Flash’s adoptive father Joe West, Meta-Attorney Cecile Horton, tough cub reporter Allegra Garcia and brilliant tech-nerd Chester P. Runk… Flash will ultimately defeat Mirror Master. But in doing so, he’ll also unleash an even more powerful and devastating threat on Central City: one that threatens to tear his team—and his marriage—apart.",
                    posterPath = "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                    seasonNumber = 7,
                    voteAverage = 7.9f,
                ),
            )
        )
    }
}

@Composable
fun SeasonCollapsingHeader(
    modifier: Modifier = Modifier,
    title: String,
    listState: LazyListState,
    headerHeight: Float,
    topBarHeight: Float,
    posterPath: String?,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
) {
    val scrollValue by rememberCurrentOffset(listState)
    var titleHeightPx by remember { mutableFloatStateOf(0f) }
    var titleWidthPx by remember { mutableFloatStateOf(0f) }
    val statusBarHeight = LocalFixedInsets.current.statusBarHeight.dpToPx()

    var imageHeightPx by remember { mutableFloatStateOf(0f) }
    var imageWidthPx by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(scrollValue) {
        Log.d("sqsong", "scrollValue: $scrollValue")
    }

    val context = LocalContext.current
    val placeholderBitmap = AppCompatResources.getDrawable(context, R.drawable.image_placeholder)?.toBitmap()
        ?.apply {
            eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
        }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(headerHeight.pxToDp()),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                .data(onBuildImage(posterPath, ImageType.BACKDROP, ImageSize.MEDIUM))
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(140.dp)
                .graphicsLayer {
                    val collapseRange: Float = headerHeight - topBarHeight
                    val collapseFraction: Float = (scrollValue / collapseRange).coerceIn(0f, 1f)

                    val scaleXY = lerp(
                        IMAGE_START_SCALE,
                        IMAGE_END_SCALE,
                        collapseFraction
                    )

                    val extraStartPadding = imageWidthPx * (1 - scaleXY) / 2f

                    val tx1 = lerp(
                        IMAGE_START_PADDING.toPx(),
                        (IMAGE_START_PADDING.toPx() - extraStartPadding) + imageWidthPx / 2,
                        collapseFraction
                    )

                    val py = (headerHeight - IMAGE_BOTTOM_PADDING.toPx() - imageHeightPx) * scaleXY

                    val ty1 = lerp(
                        headerHeight - IMAGE_BOTTOM_PADDING.toPx() - imageHeightPx,
                        py,
                        collapseFraction
                    )

                    val tx2 = lerp(
                        IMAGE_START_PADDING.toPx() - extraStartPadding + imageWidthPx / 2,
                        titlePaddingEnd.toPx() - extraStartPadding,
                        collapseFraction
                    )

                    val ty2 = lerp(
                        py,
                        (statusBarHeight + topBarHeight) / 2 - imageHeightPx / 2,
                        collapseFraction
                    )

                    val tx = lerp(tx1, tx2, collapseFraction)

                    val ty: Float = lerp(ty1, ty2, collapseFraction)

                    translationX = tx
                    translationY = ty

                    scaleX = scaleXY
                    scaleY = scaleXY
                }
                .clip(RoundedCornerShape(8.dp))
                .onGloballyPositioned {
                    imageHeightPx = it.size.height.toFloat()
                    imageWidthPx = it.size.width.toFloat()
                },
        )

        Text(
            text = title,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier
                .graphicsLayer {
                    /*val collapseRange: Float = headerHeight - topBarHeight
                    val collapseFraction: Float = (scrollValue / collapseRange).coerceIn(0f, 1f)

                    val scaleXY = lerp(
                        titleFontScaleStart,
                        titleFontScaleEnd,
                        collapseFraction
                    )

                    val titleExtraStartPadding = titleWidthPx * (1 - scaleXY) / 2f

                    val titleYFirstInterpolatedPoint = lerp(
                        headerHeight - titleHeightPx - paddingMedium.toPx(),
                        headerHeight / 2,
                        collapseFraction
                    )

                    val titleXFirstInterpolatedPoint = lerp(
                        titlePaddingStart.toPx(),
                        (titlePaddingEnd.toPx() - titleExtraStartPadding) * 5 / 4,
                        collapseFraction
                    )

                    val titleYSecondInterpolatedPoint = lerp(
                        headerHeight / 2,
                        (topBarHeight + statusBarHeight) / 2 - (titleHeightPx) / 2,
                        collapseFraction
                    )

                    val titleXSecondInterpolatedPoint = lerp(
                        (titlePaddingEnd.toPx() - titleExtraStartPadding) * 5 / 4,
                        titlePaddingEnd.toPx() - titleExtraStartPadding,
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
                    scaleY = scaleXY*/

                    val collapseRange: Float = headerHeight - topBarHeight
                    val collapseFraction: Float = (scrollValue / collapseRange).coerceIn(0f, 1f)

                    val scaleXY = lerp(
                        titleFontScaleStart,
                        titleFontScaleEnd,
                        collapseFraction
                    )

                    val titleExtraStartPadding = titleWidthPx * (1 - scaleXY) / 2f

                    val titleXFirstInterpolatedPoint = lerp(
                        titlePaddingStart.toPx() * 2f + imageWidthPx,
                        (titlePaddingEnd.toPx() - titleExtraStartPadding) + imageWidthPx * scaleXY,
                        collapseFraction
                    )

                    val titleYFirstInterpolatedPoint = lerp(
                        headerHeight - paddingMedium.toPx() - (titleHeightPx + imageHeightPx) / 2f,
                        headerHeight / 2,
                        collapseFraction
                    )

                    val titleXSecondInterpolatedPoint = lerp(
                        (titlePaddingEnd.toPx() - titleExtraStartPadding) + imageWidthPx * scaleXY,
                        titlePaddingEnd.toPx() - titleExtraStartPadding + imageWidthPx / 2f * scaleXY,
                        collapseFraction
                    )

                    val titleYSecondInterpolatedPoint = lerp(
                        headerHeight / 2,
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
fun SeasonCollapsingHeaderPreview() {
    TMDBMovieTheme {
        SeasonCollapsingHeader(
            title = "The Flash",
            listState = LazyListState(),
            headerHeight = 260.dp.dpToPx(),
            topBarHeight = 50.dp.dpToPx(),
            posterPath = "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
        )
    }
}