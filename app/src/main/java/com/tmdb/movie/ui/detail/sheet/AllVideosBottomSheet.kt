package com.tmdb.movie.ui.detail.sheet

import android.content.res.Configuration
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
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
import com.tmdb.movie.component.myiconpack.emptyDataVector
import com.tmdb.movie.data.Video
import com.tmdb.movie.ui.theme.TMDBMovieTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllVideosBottomSheet(
    videos: List<Video>,
    onBottomSheetDismiss: () -> Unit,
    onVideoClick: (String?, Boolean) -> Unit,
    coroutinesScope: CoroutineScope = rememberCoroutineScope(),
) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        onDismissRequest = onBottomSheetDismiss,
        windowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        dragHandle = { },
    ) {
        VideosBottomSheetContent(
            videos = videos,
            onVideoClick = onVideoClick,
            onClose = {
                coroutinesScope.launch {
                    sheetState.hide()
                    onBottomSheetDismiss()
                }
            },
        )
    }
}


@Composable
fun VideosBottomSheetContent(
    videos: List<Video>,
    onClose: () -> Unit,
    onVideoClick: (String?, Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 45.dp)
    ) {

        ProfileTitleComponent(
            modifier = Modifier.padding(top = 0.dp, bottom = 8.dp),
            title = stringResource(id = R.string.key_videos),
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
                    )
                }
            }
        )

        if (videos.isEmpty()) {
            EmptyVideosComponent()
        } else {
            LazyColumn {
                items(videos.size) { index ->
                    VideosComponent(
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = if (index == videos.size - 1) 16.dp else 8.dp),
                        video = videos[index],
                        onVideoClick = onVideoClick,
                    )
                }
            }
        }
    }
}

@Composable
fun VideosComponent(
    modifier: Modifier,
    video: Video,
    onVideoClick: (String?, Boolean) -> Unit,
) {
    val context = LocalContext.current
    val placeholderBitmap = AppCompatResources.getDrawable(context, R.drawable.image_placeholder_horizontal)?.toBitmap()?.apply {
        eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
    }
    Box(
        modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(MaterialTheme.shapes.medium)
                .clickable { onVideoClick(video.key, video.isYoutube()) },
            model = ImageRequest.Builder(LocalContext.current)
                .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                .error(BitmapDrawable(context.resources, placeholderBitmap))
                .data(String.format(context.getString(R.string.key_youtube_video_preview_url), video.key))
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )

        Image(
            modifier = Modifier
                .size(width = 60.dp, height = 40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
                )
                .padding(horizontal = 16.dp, vertical = if (video.isYoutube()) 6.dp else 10.dp),
            painter = painterResource(id = if (video.isYoutube()) R.drawable.logo_youtube else R.drawable.logo_vimeo),
            contentDescription = "",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f))
        )
    }
}

@Composable
fun EmptyVideosComponent() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
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
        Text(
            text = stringResource(R.string.key_no_videos),
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onBackground
            )
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewVideosBottomSheet() {
    TMDBMovieTheme {
        VideosBottomSheetContent(
            videos = listOf(
                Video(
                    id = "1",
                    key = "key",
                    name = "name",
                    site = "YouTube",
                    size = 1,
                    type = "Trailer"
                ),
                Video(
                    id = "1",
                    key = "key",
                    name = "name",
                    site = "Vimeo",
                    size = 1,
                    type = "Trailer"
                ),
                Video(
                    id = "1",
                    key = "key",
                    name = "name",
                    site = "YouTube",
                    size = 1,
                    type = "Trailer"
                ),
            ),
            onVideoClick = { _, _ -> },
            onClose = {}
        )
    }
}