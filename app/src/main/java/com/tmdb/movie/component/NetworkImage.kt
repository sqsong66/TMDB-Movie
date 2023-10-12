package com.tmdb.movie.component

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.google.accompanist.placeholder.PlaceholderDefaults
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmerHighlightColor
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.tmdb.movie.R
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun NetworkHorizontalImage(
    modifier: Modifier,
    imageUrl: String? = null,
) {
    val context = LocalContext.current
    val placeholderBitmap = AppCompatResources.getDrawable(context, R.drawable.backdrop)?.toBitmap()?.apply {
        eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
    }

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
            .error(BitmapDrawable(context.resources, placeholderBitmap))
            .data(imageUrl)
            .crossfade(true)
            .size(Size.ORIGINAL)
            .build()
    )
    when (painter.state) {
        is AsyncImagePainter.State.Error -> Box(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.baseline_broken_image),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f))
            )
        }

        is AsyncImagePainter.State.Loading -> Image(
            modifier = modifier.placeholder(
                visible = true,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(8.dp),
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                        alpha = 0.25f
                    )
                )
            ),
            painter = painter,
            contentDescription = ""
        )

        else -> Image(
            modifier = modifier
                .fillMaxWidth(),
            painter = painter,
            contentDescription = "",
            contentScale = ContentScale.FillWidth
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NetworkHorizontalImagePreview() {
    TMDBMovieTheme {
        NetworkHorizontalImage(
            modifier = Modifier,
            imageUrl = "",
        )
    }
}