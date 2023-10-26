package com.tmdb.movie.ui.lists.component

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.google.accompanist.placeholder.PlaceholderDefaults
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmerHighlightColor
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.google.android.renderscript.Toolkit
import com.tmdb.movie.R
import com.tmdb.movie.data.HomePopularMovie
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.MediaList
import com.tmdb.movie.ui.theme.TMDBMovieTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun AccountListsItem(
    modifier: Modifier = Modifier,
    mediaList: MediaList,
    cachedMovies: List<HomePopularMovie>,
    toListsDetail: (Int, String) -> Unit,
    scaleFactor: Int = 2,
    blurRadius: Int = 6,
    maskColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
) {

    val context = LocalContext.current
    val imageUrl = if (cachedMovies.isEmpty()) {
        ""
    } else {
        cachedMovies[mediaList.id % cachedMovies.size].backdropPath?.let { onBuildImage(it, ImageType.BACKDROP, ImageSize.SMALL) } ?: ""
    }
    var bgImageSize by remember { mutableStateOf(IntSize.Zero) }
    var blurBgBitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(key1 = imageUrl, key2 = bgImageSize) {
        if (imageUrl.isNotEmpty() && bgImageSize != IntSize.Zero) {
            val bitmap = withContext(Dispatchers.IO) {
                val b = Glide.with(context).asBitmap().load(imageUrl).submit(bgImageSize.width / scaleFactor, bgImageSize.height / scaleFactor).get()
                val canvas = Canvas(b)
                val destColor = maskColor.copy(alpha = 0.5f)
                canvas.drawColor(destColor.toArgb())
                Toolkit.blur(b, blurRadius)
            }
            blurBgBitmap = bitmap
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                toListsDetail(mediaList.id, cachedMovies[mediaList.id % cachedMovies.size].backdropPath?.let { onBuildImage(it, ImageType.BACKDROP, ImageSize.MEDIUM) } ?: "")
            },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned {
                    bgImageSize = it.size
                },
            contentAlignment = Alignment.Center
        ) {
            blurBgBitmap?.let {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    bitmap = it.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                BasicText(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    text = mediaList.name ?: "",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    overflow = TextOverflow.Ellipsis,
                )

                BasicText(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 2.dp),
                    text = stringResource(id = R.string.key_list_items, mediaList.itemCount),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    overflow = TextOverflow.Ellipsis,
                )

                BasicText(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 4.dp),
                    text = mediaList.description ?: "",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
fun AccountListsItemPlaceholder(
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                BasicText(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(horizontal = 16.dp)
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
                    text = "",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    overflow = TextOverflow.Ellipsis,
                )

                BasicText(
                    modifier = Modifier
                        .width(80.dp)
                        .padding(start = 16.dp, end = 16.dp, top = 2.dp)
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
                    text = "",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    overflow = TextOverflow.Ellipsis,
                )

                BasicText(
                    modifier = Modifier
                        .width(260.dp)
                        .padding(start = 16.dp, end = 16.dp, top = 4.dp)
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
                    text = "",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun AccountListsItemPreview() {
    TMDBMovieTheme {
        AccountListsItem(
            mediaList = MediaList(
                name = "Action Movies",
                description = "My favorite action movie lists.",
                itemCount = 10,
            ),
            cachedMovies = listOf(
                HomePopularMovie(id = 299054, title = "Expend4bles", backdropPath = "/rMvPXy8PUjj1o8o1pzgQbdNCsvj.jpg", updatedAt = "2023-10-23"),
                HomePopularMovie(id = 385687, title = "Fast X", backdropPath = "/4XM8DUTQb3lhLemJC51Jx4a2EuA.jpg", updatedAt = "2023-10-23"),
                HomePopularMovie(
                    id = 554600,
                    title = "Uri: The Surgical Strike",
                    backdropPath = "/S3EIcOUQYxgd3QzjOo2rZJ2MN8.jpg",
                    updatedAt = "2023-10-23"
                ),
            ),
            toListsDetail = { _, _ -> },
        )
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun AccountListsItemPlaceholderPreview() {
    TMDBMovieTheme {
        AccountListsItemPlaceholder(
            modifier = Modifier.padding(16.dp)
        )
    }
}