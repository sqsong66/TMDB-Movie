package com.tmdb.movie.ui.detail.component

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tmdb.movie.R
import com.tmdb.movie.component.ProfileTitleComponent
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.MovieImage
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun MovieDetailImageComponent(
    modifier: Modifier = Modifier,
    @ImageType imageType: Int,
    imageList: List<MovieImage>,
    onPreviewImage: (String?) -> Unit,
    onMoreImages: (@ImageType Int, List<MovieImage>) -> Unit = { _, _ -> },
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
) {
    val context = LocalContext.current
    val backdropSize = imageList.size.coerceAtMost(5)
    var isImageError by rememberSaveable { mutableStateOf(false) }
    val placeholderBitmap = AppCompatResources.getDrawable(
        context,
        if (imageType == ImageType.BACKDROP) R.drawable.image_placeholder_horizontal else R.drawable.image_placeholder
    )
        ?.toBitmap()?.apply {
            eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
        }

    Column(modifier = modifier.fillMaxWidth()) {
        ProfileTitleComponent(
            modifier = Modifier,
            title = stringResource(if (imageType == ImageType.BACKDROP) R.string.key_backdrops else R.string.key_poster),
            showMoreText = imageList.size > 5,
            moreText = stringResource(R.string.key_view_all),
            onMoreTextClick = { onMoreImages(imageType, imageList) }
        )

        LazyRow(modifier = Modifier.padding(top = 10.dp)) {
            items(backdropSize) { index ->
                AsyncImage(
                    modifier = Modifier
                        .height(160.dp)
                        .padding(
                            start = if (index == 0) 16.dp else 8.dp,
                            end = if (index == backdropSize - 1) 16.dp else 8.dp
                        )
                        .clip(MaterialTheme.shapes.medium)
                        .clickable { onPreviewImage(onBuildImage(imageList[index].filePath, imageType, ImageSize.LARGE)) },
                    model = ImageRequest.Builder(LocalContext.current)
                        .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                        .error(BitmapDrawable(context.resources, placeholderBitmap))
                        .data(onBuildImage(imageList[index].filePath, imageType, ImageSize.MEDIUM))
                        .crossfade(true).listener(onError = { _, _ ->
                            isImageError = true
                        }).build(),
                    contentScale = ContentScale.FillHeight,
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieBackdropComponentPreview() {
    TMDBMovieTheme {
        MovieDetailImageComponent(
            modifier = Modifier.padding(bottom = 24.dp),
            imageType = ImageType.BACKDROP,
            imageList = listOf(
                MovieImage(),
                MovieImage(),
                MovieImage(),
            ),
            onPreviewImage = {},
        )
    }
}