package com.tmdb.movie.ui.tv.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tmdb.movie.R
import com.tmdb.movie.data.Cast
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.MovieImage
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun EpisodeTabRow(
    modifier: Modifier = Modifier,
    tabItems: List<String>,
    selectedTabIndex: Int,
    onSelected: (Int) -> Unit
) {
    PrimaryTabRow(
        modifier = modifier,
        selectedTabIndex = selectedTabIndex,
    ) {
        tabItems.forEachIndexed { index, title ->
            Tab(
                text = { Text(text = title) },
                selected = index == selectedTabIndex,
                onClick = { onSelected(index) }
            )
        }
    }

}

@Composable
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun PreviewEpisodeTabRow() {
    TMDBMovieTheme {
        EpisodeTabRow(
            tabItems = listOf("Guest Stars", "Episode Images"),
            selectedTabIndex = 0,
            onSelected = {}
        )
    }
}

@Composable
fun EpisodeGuestStarList(
    modifier: Modifier = Modifier,
    guestStars: List<Cast>,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(guestStars.size) { index ->
            val guestStar = guestStars[index]
            EpisodeGuestItem(
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        top = if (index < 3) 8.dp else 16.dp,
                    ),
                guestStar = guestStar,
                onBuildImage = onBuildImage,
            )
        }

        item(span = {
            GridItemSpan(3)
        }) {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
fun EpisodeGuestItem(
    modifier: Modifier,
    guestStar: Cast,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String
) {
    val placeholder = BitmapDrawable(LocalContext.current.resources,
        AppCompatResources.getDrawable(LocalContext.current, R.drawable.image_placeholder)?.toBitmap()?.apply {
            eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
        }
    )
    var isImageError by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = MaterialTheme.shapes.small)
                    .clickable { },
                model = ImageRequest.Builder(LocalContext.current)
                    .placeholder(placeholder)
                    .error(placeholder)
                    .data(onBuildImage(guestStar.profilePath, ImageType.PROFILE, ImageSize.MEDIUM))
                    .crossfade(true)
                    .listener(onError = { _, _ ->
                        isImageError = true
                    })
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            if (isImageError) {
                Image(
                    modifier = Modifier
                        .size(46.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                )
            }
        }

        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = guestStar.name ?: "",
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            modifier = Modifier.padding(top = 2.dp),
            text = guestStar.character ?: "",
            style = MaterialTheme.typography.labelSmall.copy(
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.6f
                ),
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun PreviewEpisodeGuestStarList() {
    val guestStars = mutableListOf<Cast>()
    repeat(30) {
        guestStars.add(Cast(id = 1, name = "Jeremy Crawford", profilePath = "", character = "Yarpen Zigrin"))
    }
    TMDBMovieTheme {
        EpisodeGuestStarList(
            guestStars = guestStars,
            onBuildImage = { _, _, _ -> "" }
        )
    }
}

@Composable
fun EpisodeImageLists(
    modifier: Modifier = Modifier,
    images: List<MovieImage>,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String
) {

    val placeholder = BitmapDrawable(LocalContext.current.resources,
        AppCompatResources.getDrawable(LocalContext.current, R.drawable.image_placeholder_horizontal)?.toBitmap()?.apply {
            eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
        }
    )

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(images.size) { index ->
            val image = images[index]
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = if (index == images.size - 1) 16.dp else 0.dp
                    )
                    .clip(shape = MaterialTheme.shapes.small)
                    .clickable {

                    },
                model = ImageRequest.Builder(LocalContext.current)
                    .placeholder(placeholder)
                    .error(placeholder)
                    .data(onBuildImage(image.filePath, ImageType.STILL, ImageSize.LARGE))
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }
    }

}

@Composable
@Preview(showBackground = true)
fun PreviewEpisodeImageLists() {
    val images = mutableListOf<MovieImage>()
    repeat(30) {
        images.add(MovieImage(filePath = "", aspectRatio = 1.0f))
    }
    TMDBMovieTheme {
        EpisodeImageLists(
            images = images,
            onBuildImage = { _, _, _ -> "" }
        )
    }
}