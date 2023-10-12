package com.tmdb.movie.ui.home.component

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.transformation.blur.BlurTransformationPlugin
import com.tmdb.movie.R

@Composable
fun HomeBgComponent(
    modifier: Modifier = Modifier,
    useBlur: Boolean = false,
    imageUrl: String
) {
    val placeholderBitmap =
        AppCompatResources.getDrawable(LocalContext.current, R.drawable.image_placeholder)?.toBitmap()?.apply {
            eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
        }
    var bgImageSize by remember { mutableStateOf(IntSize.Zero) }
    Box(modifier = modifier.fillMaxWidth()) {
        if (useBlur) {
            CoilImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { bgImageSize = it.size },
                imageModel = { imageUrl },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillWidth,
                ),
                component = rememberImageComponent {
                    +BlurTransformationPlugin(radius = 20)
                }
            )
        } else {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { bgImageSize = it.size },
                model = ImageRequest.Builder(LocalContext.current)
                    .placeholder(BitmapDrawable(LocalContext.current.resources, placeholderBitmap))
                    .data(imageUrl)
                    .crossfade(true)
                    .build(), contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
                            MaterialTheme.colorScheme.background
                        ),
                        startY = 0f, // bgImageSize.height.toFloat() / 3,
                        endY = bgImageSize.height.toFloat()
                    )
                )
        )
    }
}