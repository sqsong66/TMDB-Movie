package com.tmdb.movie.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.google.android.renderscript.Toolkit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun BlurHeaderBgComponent(
    modifier: Modifier = Modifier,
    useBlur: Boolean = true,
    imageUrl: String,
    scaleFactor: Int = 6,
    blurRadius: Int = 16,
) {
    val context = LocalContext.current
    var bgImageSize by remember { mutableStateOf(IntSize.Zero) }
    var blurBgBitmap by remember { mutableStateOf<android.graphics.Bitmap?>(null) }

    LaunchedEffect(key1 = imageUrl, key2 = bgImageSize) {
        if (useBlur && imageUrl.isNotEmpty() && bgImageSize != IntSize.Zero) {
            val bitmap = withContext(Dispatchers.IO) {
                val b = Glide.with(context).asBitmap().load(imageUrl).submit(bgImageSize.width / scaleFactor, bgImageSize.height / scaleFactor).get()
                Toolkit.blur(b, blurRadius)
            }
            blurBgBitmap = bitmap
        }
    }

    Box(modifier = modifier.fillMaxWidth()) {
        if (useBlur) {
            blurBgBitmap?.asImageBitmap()?.let { imageBitmap ->
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { bgImageSize = it.size },
                    bitmap = imageBitmap,
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth
                )
            } ?: AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { bgImageSize = it.size },
                model = ImageRequest.Builder(LocalContext.current)
                    .data("")
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        } else {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { bgImageSize = it.size },
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
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
                        startY = 0f,
                        endY = bgImageSize.height.toFloat()
                    )
                )
        )
    }
}