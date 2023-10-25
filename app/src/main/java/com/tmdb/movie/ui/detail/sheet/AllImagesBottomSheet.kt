package com.tmdb.movie.ui.detail.sheet

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tmdb.movie.R
import com.tmdb.movie.component.ProfileTitleComponent
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.MovieImage
import com.tmdb.movie.ui.theme.TMDBMovieTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllImagesBottomSheet(
    @ImageType imageType: Int,
    images: List<MovieImage>,
    onBottomSheetDismiss: () -> Unit,
    coroutinesScope: CoroutineScope = rememberCoroutineScope(),
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        modifier = Modifier
            .fillMaxSize(),
        sheetState = sheetState,
        onDismissRequest = onBottomSheetDismiss,
        windowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        dragHandle = { },
    ) {
        BottomSheetContent(
            imageType = imageType,
            images = images,
            onClose = {
                coroutinesScope.launch {
                    sheetState.hide()
                    onBottomSheetDismiss()
                }
            },
            onBuildImage = onBuildImage,
        )
    }
}

@Composable
fun BottomSheetContent(
    @ImageType imageType: Int,
    images: List<MovieImage>,
    onClose: () -> Unit,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 45.dp)
    ) {
        ProfileTitleComponent(
            modifier = Modifier,
            title = stringResource(id = if (imageType == ImageType.BACKDROP) R.string.key_backdrops else R.string.key_poster),
            showMoreText = false,
            moreText = "",
            onMoreTextClick = {},
            leadingIcon = {
                IconButton(
                    modifier = Modifier.padding(start = 8.dp),
                    onClick = { onClose() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_close_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
        )

        if (images.isEmpty()) {
            EmptyVideosComponent()
        } else {
            if (imageType == ImageType.BACKDROP) {
                BackdropImages(
                    modifier = Modifier.padding(top = 8.dp),
                    images = images,
                    onBuildImage = onBuildImage,
                )
            } else {
                PosterImages(
                    modifier = Modifier.padding(top = 8.dp),
                    images = images,
                    onBuildImage = onBuildImage
                )
            }
        }
    }
}


@Composable
fun BackdropImages(
    modifier: Modifier = Modifier,
    images: List<MovieImage>,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
) {
    val placeholderBitmap = AppCompatResources.getDrawable(LocalContext.current, R.drawable.image_placeholder_horizontal)?.toBitmap()?.apply {
        eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
    }
    LazyColumn(modifier = modifier) {
        items(images.size) { index ->
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = if (index == images.size - 1) 16.dp else 8.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .clickable { },
                model = ImageRequest.Builder(LocalContext.current)
                    .placeholder(BitmapDrawable(LocalContext.current.resources, placeholderBitmap))
                    .error(BitmapDrawable(LocalContext.current.resources, placeholderBitmap))
                    .data(onBuildImage(images[index].filePath, ImageType.BACKDROP))
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun PosterImages(
    modifier: Modifier,
    images: List<MovieImage>,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
) {

    val placeholderBitmap = AppCompatResources.getDrawable(LocalContext.current, R.drawable.image_placeholder)?.toBitmap()?.apply {
        eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
    }

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
    ) {
        items(images.size) { index ->
            AsyncImage(
                modifier = Modifier
                    .padding(
                        start = if (index % 2 == 0) 16.dp else 8.dp,
                        end = if (index % 2 == 1) 16.dp else 8.dp,
                        top = 8.dp,
                        bottom = if (index == images.size - 1) 16.dp else 8.dp
                    )
                    .clip(MaterialTheme.shapes.medium)
                    .clickable { },
                model = ImageRequest.Builder(LocalContext.current)
                    .placeholder(BitmapDrawable(LocalContext.current.resources, placeholderBitmap))
                    .error(BitmapDrawable(LocalContext.current.resources, placeholderBitmap))
                    .data(onBuildImage(images[index].filePath, ImageType.POSTER))
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewAllImagesBottomSheet() {
    TMDBMovieTheme {
        BottomSheetContent(
            imageType = ImageType.POSTER,
            images = listOf(
                MovieImage(
                    filePath = "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
                    height = 1080,
                    width = 1920,
                    aspectRatio = 1.7777778f,
                    voteAverage = 5.0f,
                    voteCount = 1,
                ),
                MovieImage(
                    filePath = "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
                    height = 1080,
                    width = 1920,
                    aspectRatio = 1.7777778f,
                    voteAverage = 5.0f,
                    voteCount = 1,
                ),
                MovieImage(
                    filePath = "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
                    height = 1080,
                    width = 1920,
                    aspectRatio = 1.7777778f,
                    voteAverage = 5.0f,
                    voteCount = 1,
                ),
                MovieImage(
                    filePath = "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
                    height = 1080,
                    width = 1920,
                    aspectRatio = 1.7777778f,
                    voteAverage = 5.0f,
                    voteCount = 1,
                ),
            ),
            onClose = {},
        )
    }
}