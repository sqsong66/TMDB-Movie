package com.tmdb.movie.ui.detail.sheet

import android.content.res.Configuration
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tmdb.movie.R
import com.tmdb.movie.component.ProfileTitleComponent
import com.tmdb.movie.data.Cast
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.ui.theme.TMDBMovieTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllCastsBottomSheet(
    castList: List<Cast>,
    onPeopleDetail: (Int) -> Unit,
    onBottomSheetDismiss: () -> Unit,
    coroutinesScope: CoroutineScope = rememberCoroutineScope(),
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        onDismissRequest = onBottomSheetDismiss,
        windowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        dragHandle = { },
    ) {
        BottomSheetContent(
            castList = castList,
            onClose = {
                coroutinesScope.launch {
                    sheetState.hide()
                    onBottomSheetDismiss()
                }
            },
            onPeopleDetail = onPeopleDetail,
            onBuildImage = onBuildImage,
        )
    }
}

@Composable
fun BottomSheetContent(
    castList: List<Cast>,
    onClose: () -> Unit,
    onPeopleDetail: (Int) -> Unit,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 45.dp)
    ) {
        ProfileTitleComponent(
            modifier = Modifier,
            title = stringResource(id = R.string.key_casts),
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

        if (castList.isEmpty()) {
            EmptyVideosComponent()
        } else {
            MediaCasts(
                modifier = Modifier,
                casts = castList,
                onPeopleDetail = onPeopleDetail,
                onBuildImage = onBuildImage,
            )
        }
    }
}

@Composable
fun MediaCasts(
    modifier: Modifier,
    casts: List<Cast>,
    onPeopleDetail: (Int) -> Unit,
    onBuildImage: (String?, @ImageType Int) -> String?,
) {
    val placeholderBitmap = AppCompatResources.getDrawable(LocalContext.current, R.drawable.image_placeholder)?.toBitmap()?.apply {
        eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
    }

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
    ) {
        items(casts.size) { index ->
            CastItemComponent(
                modifier = Modifier
                    .padding(
                        start = if (index % 2 == 0) 16.dp else 8.dp,
                        end = if (index % 2 == 1) 16.dp else 8.dp,
                        top = 8.dp,
                        bottom = if (index == casts.size - 1) 16.dp else 8.dp
                    )
                    .clip(MaterialTheme.shapes.medium)
                    .clickable { onPeopleDetail(casts[index].id) },
                cast = casts[index],
                onBuildImage = onBuildImage,
                placeholderDrawable = BitmapDrawable(LocalContext.current.resources, placeholderBitmap),
            )
        }
    }
}

@Composable
fun CastItemComponent(
    modifier: Modifier,
    cast: Cast,
    placeholderDrawable: BitmapDrawable?,
    onBuildImage: (String?, @ImageType Int) -> String?,
) {
    var isImageError by rememberSaveable { mutableStateOf(false) }
    Card(
        modifier = modifier,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .placeholder(placeholderDrawable)
                    .error(placeholderDrawable)
                    .data(onBuildImage(cast.profilePath, ImageType.PROFILE))
                    .crossfade(true)
                    .listener(onError = { _, _ ->
                        isImageError = true
                    })
                    .build(),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )

            if (isImageError) {
                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0f),
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                            )
                        )
                    ),
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 4.dp),
                    text = cast.name ?: "",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 18.sp,
                    ),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAllCastsBottomSheet() {
    TMDBMovieTheme {
        BottomSheetContent(
            castList = listOf(
                Cast(
                    id = 1,
                    name = "name",
                    profilePath = "",
                    character = "character",
                ),
                Cast(
                    id = 1,
                    name = "name",
                    profilePath = "",
                    character = "character",
                ),
                Cast(
                    id = 1,
                    name = "name",
                    profilePath = "",
                    character = "character",
                ),
                Cast(
                    id = 1,
                    name = "name",
                    profilePath = "",
                    character = "character",
                ),
            ),
            onPeopleDetail = {},
            onBuildImage = { _, _ -> null },
            onClose = {},
        )
    }
}