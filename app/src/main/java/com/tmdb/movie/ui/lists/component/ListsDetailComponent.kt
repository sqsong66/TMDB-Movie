package com.tmdb.movie.ui.lists.component

import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.tmdb.movie.R
import com.tmdb.movie.component.myiconpack.emptyDataVector
import com.tmdb.movie.component.rememberCurrentOffset
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.ListsDetail
import com.tmdb.movie.data.MediaItem
import com.tmdb.movie.data.MediaType
import com.tmdb.movie.ext.pxToDp
import com.tmdb.movie.ui.theme.LocalFixedInsets
import com.tmdb.movie.ui.theme.TMDBMovieTheme


private val titlePaddingStart = 16.dp
private val titlePaddingEnd = 72.dp
private val paddingMedium = 8.dp

private const val titleFontScaleStart = 1f
private const val titleFontScaleEnd = 0.66f

@Composable
fun ListsDetailHeader(
    modifier: Modifier = Modifier,
    headerHeight: Float,
    gridState: LazyGridState,
    coverImageUrl: String,
) {

    val scrollValue by rememberCurrentOffset(gridState)
    val context = LocalContext.current
    val placeholderBitmap =
        AppCompatResources.getDrawable(context, R.drawable.image_placeholder_horizontal)?.toBitmap()?.apply {
            eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
        }

    Box(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .graphicsLayer {
                    translationY = -scrollValue.toFloat() / 2
                    alpha = (-1f / headerHeight) * scrollValue + 1
                    Log.w("sqsong", "ListsDetailHeader compose: alpha: $alpha.....")
                },
            model = ImageRequest.Builder(LocalContext.current)
                .data(coverImageUrl)
                .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                .error(BitmapDrawable(context.resources, placeholderBitmap))
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
                        )
                    )
                )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListsDetailTopBar(
    modifier: Modifier = Modifier,
    gridState: LazyGridState,
    topBarBottom: Float,
    onBackClick: (Boolean) -> Unit,
) {
    val scrollValue by rememberCurrentOffset(gridState)

    TopAppBar(
        modifier = modifier,
        title = {},
        navigationIcon = {
            IconButton(onClick = {
                onBackClick(false)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = ""
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = if (scrollValue > topBarBottom) 1f else 0f),
        )
    )
}

@Composable
fun ListsDetailTitle(
    modifier: Modifier = Modifier,
    title: String,
    gridState: LazyGridState,
    headerHeight: Dp,
    topBarHeight: Dp,
) {
    val scrollValue by rememberCurrentOffset(gridState)
    var titleHeightPx by remember { mutableFloatStateOf(0f) }
    var titleWidthPx by remember { mutableFloatStateOf(0f) }
    val statusBarHeight = LocalFixedInsets.current.statusBarHeight

    Text(
        text = title,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
            .graphicsLayer {
                val collapseRange: Float = (headerHeight.toPx() - topBarHeight.toPx())
                val collapseFraction: Float = (scrollValue / collapseRange).coerceIn(0f, 1f)

                val scaleXY = lerp(
                    titleFontScaleStart.dp,
                    titleFontScaleEnd.dp,
                    collapseFraction
                )

                val titleExtraStartPadding = titleWidthPx.toDp() * (1 - scaleXY.value) / 2f

                val titleYFirstInterpolatedPoint = lerp(
                    headerHeight - titleHeightPx.toDp() - paddingMedium,
                    headerHeight / 2,
                    collapseFraction
                )

                val titleXFirstInterpolatedPoint = lerp(
                    titlePaddingStart,
                    (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
                    collapseFraction
                )

                val titleYSecondInterpolatedPoint = lerp(
                    headerHeight / 2,
                    (topBarHeight + statusBarHeight) / 2 - (titleHeightPx.toDp()) / 2,
                    collapseFraction
                )

                val titleXSecondInterpolatedPoint = lerp(
                    (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
                    titlePaddingEnd - titleExtraStartPadding,
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

                translationX = titleX.toPx()
                translationY = titleY.toPx()
                scaleX = scaleXY.value
                scaleY = scaleXY.value
            }
            .onGloballyPositioned {
                titleHeightPx = it.size.height.toFloat()
                titleWidthPx = it.size.width.toFloat()
            }
    )
}

@Composable
fun ListsDetailBody(
    modifier: Modifier = Modifier,
    listState: LazyGridState,
    headerHeight: Dp,
    listsDetail: ListsDetail,
    toMediaDetail: (Int, @MediaType Int) -> Unit,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
) {
    LazyVerticalGrid(
        modifier = modifier,
        state = listState,
        columns = GridCells.Fixed(2),
    ) {
        item(
            span = {
                GridItemSpan(2)
            }
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        headerHeight,
                    )
            )
        }

        item(
            span = {
                GridItemSpan(2)
            }
        ) {
            Text(
                modifier = Modifier.padding(
                    start = 16.dp, end = 16.dp,
                ),
                text = listsDetail.description ?: "",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    fontStyle = FontStyle.Italic
                )
            )
        }

        if (listsDetail.itemCount <= 0) {
            item(span = {
                GridItemSpan(2)
            }) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 150.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Image(
                        modifier = Modifier
                            .width(100.dp)
                            .align(Alignment.CenterHorizontally),
                        imageVector = emptyDataVector(
                            primaryColor = MaterialTheme.colorScheme.primary,
                            backgroundColor = MaterialTheme.colorScheme.background
                        ),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth
                    )
                    androidx.compose.material3.Text(
                        text = stringResource(R.string.key_no_items),
                        modifier = Modifier.padding(top = 16.dp),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                }
            }
        } else {
            items(listsDetail.itemCount) { index ->
                ListsDetailBodyItem(
                    modifier = Modifier.padding(
                        top = if (index < 2) 16.dp else 0.dp,
                        start = if (index % 2 == 0) 16.dp else 8.dp,
                        end = if (index % 2 == 1) 16.dp else 8.dp,
                        bottom = 16.dp
                    ),
                    mediaItem = listsDetail.items?.get(index) ?: MediaItem(),
                    onBuildImage = onBuildImage,
                    toMediaDetail = toMediaDetail,
                )
            }
        }
    }
}

@Composable
fun ListsDetailBodyItem(
    modifier: Modifier = Modifier,
    mediaItem: MediaItem,
    toMediaDetail: (Int, @MediaType Int) -> Unit,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
) {
    Log.e("sqsong", "ListsDetailBodyItem compose.....")
    val context = LocalContext.current
    val placeholderBitmap =
        AppCompatResources.getDrawable(context, R.drawable.image_placeholder)?.toBitmap()?.apply {
            eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
        }
    Column(modifier = modifier) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small)
                .clickable {
                    toMediaDetail(mediaItem.id, mediaItem.getMediaType())
                },
            model = ImageRequest.Builder(LocalContext.current)
                .data(
                    onBuildImage(
                        mediaItem.posterPath,
                        ImageType.POSTER,
                        ImageSize.MEDIUM
                    )
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
            text = mediaItem.getMovieName(),
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
@Preview(showBackground = true)
fun ListsDetailBodyPreview() {
    TMDBMovieTheme {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(5) { index ->
                ListsDetailBodyItem(
                    modifier = Modifier.padding(
                        top = if (index < 2) 16.dp else 0.dp,
                        start = if (index % 2 == 0) 16.dp else 8.dp,
                        end = if (index % 2 == 1) 16.dp else 8.dp,
                        bottom = 16.dp
                    ),
                    mediaItem = MediaItem(
                        title = "The Shawshank Redemption",
                        mediaType = "movie",
                        voteAverage = 8.7f,
                    ),
                    toMediaDetail = { _, _ -> },
                )
            }
        }
    }
}