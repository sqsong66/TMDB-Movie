package com.tmdb.movie.ui.home.component

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tmdb.movie.R
import com.tmdb.movie.component.LoadingError
import com.tmdb.movie.component.LoadingFooter
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.MediaType
import com.tmdb.movie.data.SearchItem
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun HistorySearchComponent(
    modifier: Modifier = Modifier,
    searchList: List<String>,
    onSearch: (String) -> Unit,
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        item {
            BasicText(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
                text = stringResource(R.string.key_recent_search),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }
        items(searchList.size) { index ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSearch(searchList[index])
                    }, verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(22.dp),
                    painter = painterResource(id = R.drawable.baseline_history_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
                BasicText(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 24.dp, top = 16.dp, bottom = 16.dp),
                    text = searchList[index],
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        }
    }
}

@Composable
fun SearchResultComponent(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    searchResult: LazyPagingItems<SearchItem>? = null,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
    navigateToDetail: (Int, @MediaType Int) -> Unit = { _, _ -> },
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(22.dp),
                painter = painterResource(id = R.drawable.baseline_manage_search_24), contentDescription = ""
            )
            BasicText(
                modifier = Modifier.padding(start = 8.dp),
                text = stringResource(R.string.key_search_movie_in_library, searchQuery),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }

        if (searchResult != null) {
            LazyColumn {
                items(searchResult.itemCount) {
                    val searchItem = searchResult[it]
                    when (searchItem?.getMovieType()) {
                        MediaType.MOVIE, MediaType.TV -> {
                            SearchMovieComponent(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                searchItem = searchItem,
                                searchQuery = searchQuery,
                                onBuildImage = onBuildImage,
                                navigateToDetail = navigateToDetail
                            )
                        }

                        MediaType.PERSON -> {
                            SearchPeopleComponent(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                searchItem = searchItem,
                                onBuildImage = onBuildImage,
                                navigateToDetail = navigateToDetail
                            )
                        }
                    }
                }

                when (searchResult.loadState.append) {
                    is LoadState.Error -> item { LoadingError { searchResult.retry() } }
                    LoadState.Loading -> item { LoadingFooter() }
                    else -> {}
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchMovieComponent(
    searchItem: SearchItem,
    searchQuery: String,
    modifier: Modifier = Modifier,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
    navigateToDetail: (Int, @MediaType Int) -> Unit = { _, _ -> },
) {
    val context = LocalContext.current
    val placeholderBitmap = AppCompatResources.getDrawable(context, R.drawable.image_placeholder)?.toBitmap()?.apply {
        eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
    }

    Card(modifier = modifier.padding(),
        onClick = {
            navigateToDetail(searchItem.id, searchItem.getMovieType())
        }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(45.dp)
                    .clip(MaterialTheme.shapes.extraSmall),
                model = ImageRequest.Builder(LocalContext.current)
                    .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                    .error(BitmapDrawable(context.resources, placeholderBitmap))
                    .data(onBuildImage(searchItem.getImagePath(), ImageType.POSTER))
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                BasicText(
                    text = searchItem.getAnnotatedName(
                        query = searchQuery,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        lineHeight = 16.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                BasicText(
                    modifier = Modifier.padding(top = 4.dp),
                    text = searchItem.getAnnotatedOverview(
                        context = context,
                        query = searchQuery,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    style = MaterialTheme.typography.bodySmall.copy(
                        lineHeight = 16.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPeopleComponent(
    searchItem: SearchItem,
    modifier: Modifier = Modifier,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
    navigateToDetail: (Int, @MediaType Int) -> Unit = { _, _ -> },
) {
    val context = LocalContext.current
    val placeholderBitmap = AppCompatResources.getDrawable(context, R.drawable.image_placeholder)?.toBitmap()?.apply {
        eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
    }

    Card(modifier = modifier.padding(),
        onClick = {
            navigateToDetail(searchItem.id, searchItem.getMovieType())
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(45.dp)
                    .border(
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        shape = CircleShape
                    )
                    .clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                    .error(BitmapDrawable(context.resources, placeholderBitmap))
                    .data(onBuildImage(searchItem.getImagePath(), ImageType.PROFILE))
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
            BasicText(
                modifier = Modifier.padding(start = 8.dp),
                text = searchItem.getSearchName() ?: "",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    lineHeight = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistorySearchComponentPreview() {
    TMDBMovieTheme {
        HistorySearchComponent(searchList = listOf("1", "2", "3"), onSearch = {})
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultComponentPreview() {
    TMDBMovieTheme {
        SearchResultComponent(
            searchQuery = "test",
            searchResult = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchMovieComponentPreview() {
    TMDBMovieTheme {
        SearchMovieComponent(
            searchQuery = "March",
            searchItem = SearchItem(
                id = 1,
                title = "March of the Penguins 2: The Next march xxxx",
                posterPath = "test",
                backdropPath = "test",
                originalTitle = "test",
                mediaType = "movie",
                overview = "A young penguin, driven by his instinct, embarks on his first major trip to march an unknown destination."
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchPeopleComponentPreview() {
    TMDBMovieTheme {
        SearchPeopleComponent(
            searchItem = SearchItem(
                id = 1,
                name = "March of the Penguins 2: The Next Step xxxx",
                posterPath = "test",
                backdropPath = "test",
                originalTitle = "test",
                mediaType = "person",
            )
        )
    }
}