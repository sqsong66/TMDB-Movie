package com.tmdb.movie.ui.home.component

import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.placeholder.PlaceholderDefaults
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmerHighlightColor
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.tmdb.movie.R
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.People
import com.tmdb.movie.ui.home.vm.MovieLoadState
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun PopularPeopleComponent(
    modifier: Modifier = Modifier,
    title: String,
    trendingState: MovieLoadState<People>,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
    onNavigateToPeopleDetail: (Int) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        )
        if (trendingState is MovieLoadState.Success) {
            LazyRow {
                items(trendingState.data.size) { index ->
                    PeopleComponent(
                        modifier = Modifier
                            .padding(
                                top = 16.dp,
                                start = if (index == 0) 16.dp else 8.dp,
                                end = if (index == trendingState.data.size - 1) 16.dp else 8.dp
                            ),
                        people = trendingState.data[index],
                        onBuildImage = onBuildImage,
                        onNavigateToPeopleDetail = onNavigateToPeopleDetail,
                    )
                }
            }
        } else {
            LazyRow {
                items(10) { index ->
                    PeopleComponentPlaceholder(
                        Modifier
                            .padding(
                                top = 16.dp,
                                start = if (index == 0) 16.dp else 8.dp,
                                end = if (index == 9) 16.dp else 8.dp
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun PeopleComponent(
    modifier: Modifier = Modifier,
    people: People? = null,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
    onNavigateToPeopleDetail: (Int) -> Unit = {},
) {
    val context = LocalContext.current
    var isImageError by rememberSaveable { mutableStateOf(false) }
    val placeholderBitmap = AppCompatResources.getDrawable(context, R.drawable.image_placeholder)?.toBitmap()?.apply {
        eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
    }
    Column(modifier = modifier.width(80.dp)) {
        if (isImageError) {
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .border(
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .clickable { onNavigateToPeopleDetail(people?.id ?: 0) },
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.baseline_account_circle_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
            )
        } else {
            AsyncImage(
                modifier = Modifier
                    .size(80.dp)
                    .border(
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .clickable { onNavigateToPeopleDetail(people?.id ?: 0) },
                model = ImageRequest.Builder(LocalContext.current)
                    .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                    .error(BitmapDrawable(context.resources, placeholderBitmap))
                    .data(onBuildImage(people?.profilePath, ImageType.PROFILE))
                    .crossfade(true)
                    .listener(onError = { _, _ ->
                        isImageError = true
                    })
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        }

        Text(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth(),
            text = people?.name ?: "",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium, lineHeight = 16.sp),
            maxLines = 2,
            minLines = 2,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun PeopleComponentPlaceholder(modifier: Modifier = Modifier) {
    Column(modifier = modifier.width(80.dp)) {
        Image(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .placeholder(
                    visible = true,
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp),
                    highlight = PlaceholderHighlight.shimmer(
                        highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                            alpha = 0.25f
                        )
                    )
                ),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.avatar_people),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
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
            text = "Julianne Moore",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            maxLines = 1,
            minLines = 1,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            // fontSizeRange = FontSizeRange(10.sp, 14.sp)
        )
    }
}

@Preview
@Composable
fun PeopleComponentPreview() {
    TMDBMovieTheme {
        PeopleComponent()
    }
}

@Preview
@Composable
fun PeopleComponentPlaceholderPreview() {
    TMDBMovieTheme {
        PeopleComponentPlaceholder()
    }
}