package com.tmdb.movie.ui.detail.sheet

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tmdb.movie.R
import com.tmdb.movie.component.myiconpack.emptyDataVector
import com.tmdb.movie.data.MediaList
import com.tmdb.movie.ui.theme.TMDBMovieTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaListBottomSheet(
    onDismiss: () -> Unit,
    mediaList: List<MediaList>? = null,
    onMediaListClick: (MediaList) -> Unit,
    onCreateList: () -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismiss() },
        dragHandle = {},
        windowInsets = WindowInsets(0, 0, 0, 0),
    ) {
        MediaListBottomSheetContent(
            mediaList = mediaList,
            onMediaListClick = onMediaListClick,
            onCreateList = {
                coroutineScope.launch {
                    sheetState.hide()
                    onCreateList()
                }
            },
        )
    }
}

@Composable
fun MediaListBottomSheetContent(
    mediaList: List<MediaList>? = null,
    onMediaListClick: (MediaList) -> Unit,
    onCreateList: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 360.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.key_my_lists),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )

            Text(
                text = stringResource(R.string.key_create_list),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .background(
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = MaterialTheme.shapes.large
                    )
                    .clip(MaterialTheme.shapes.large)
                    .clickable { onCreateList() }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSecondary
                )
            )
        }

        if (mediaList.isNullOrEmpty()) {
            EmptyListsComponent()
        } else {
            LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                items(mediaList.size) { index ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onMediaListClick(mediaList[index]) },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            modifier = Modifier.padding(start = 16.dp),
                            painter = painterResource(id = R.drawable.outline_movie_24),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = mediaList[index].name ?: "",
                                modifier = Modifier
                                    .weight(1f, fill = false)
                                    .padding(start = 16.dp, top = 20.dp, bottom = 20.dp),
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Text(
                                text = String.format("(%s)", mediaList[index].itemCount),
                                modifier = Modifier
                                    .padding(start = 6.dp, end = 16.dp),
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyListsComponent() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier
                .width(100.dp)
                .padding(top = 60.dp)
                .align(Alignment.CenterHorizontally),
            imageVector = emptyDataVector(
                primaryColor = MaterialTheme.colorScheme.primary,
                backgroundColor = MaterialTheme.colorScheme.background
            ),
            contentDescription = "",
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = stringResource(R.string.key_no_lists),
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Text(
            text = stringResource(R.string.key_no_list_hint),
            modifier = Modifier.padding(top = 5.dp, start = 24.dp, end = 24.dp),
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, locale = "zh")
@Composable
fun MediaListBottomSheetPreview() {
    TMDBMovieTheme {
        MediaListBottomSheetContent(
            mediaList = null,
//            mediaList = listOf(
//                MediaList(name = "My List 1", itemCount = 10),
//                MediaList(name = "My List 2", itemCount = 10),
//                MediaList(name = "My List 3", itemCount = 10),
//                MediaList(name = "My List 4", itemCount = 10),
//            ),
            onMediaListClick = {},
            onCreateList = {}
        )
    }
}