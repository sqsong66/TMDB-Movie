package com.tmdb.movie.ui.people.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tmdb.movie.R
import com.tmdb.movie.component.ProfileTitleComponent
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.MediaType
import com.tmdb.movie.data.PeopleCast
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleActingBottomSheet(
    name: String,
    castLists: List<List<PeopleCast>>,
    onBottomSheetDismiss: () -> Unit,
    toMovieDetail: (Int, @MediaType Int) -> Unit,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url }
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        onDismissRequest = onBottomSheetDismiss,
        dragHandle = { },
    ) {
        BottomSheetContent(
            name = name,
            castLists = castLists,
            onBuildImage = onBuildImage,
            toMovieDetail = toMovieDetail
        )
    }
}

@Composable
fun BottomSheetContent(
    name: String,
    castLists: List<List<PeopleCast>>,
    toMovieDetail: (Int, @MediaType Int) -> Unit,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url }
) {
    Column(modifier = Modifier.fillMaxSize()) {
        ProfileTitleComponent(
            modifier = Modifier.padding(top = 24.dp, bottom = 16.dp),
            title = String.format(stringResource(R.string.key_people_acting), name),
            showMoreText = false,
            moreText = "",
            onMoreTextClick = {}
        )
        LazyColumn {
            peopleActingComponent(
                sortedCasts = castLists,
                onBuildImage = onBuildImage,
                toMovieDetail = toMovieDetail
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PeopleActingBottomSheetPreview() {
    TMDBMovieTheme {
        BottomSheetContent(
            name = "Robert Downey Jr.",
            toMovieDetail = { _, _ -> },
            castLists = listOf(
                listOf(
                    PeopleCast(
                        backdropPath = "",
                        character = "Mother Malkin",
                        title = "Maggie's Plan",
                        name = "Maggie's Plan",
                        releaseDate = "",
                        firstAirDate = "2016-04-27",
                        mediaType = "movie",
                    ),
                    PeopleCast(
                        backdropPath = "",
                        character = "Mother Malkin",
                        title = "Maggie's Plan",
                        name = "Maggie's Plan",
                        releaseDate = "",
                        firstAirDate = "2016-04-27",
                        mediaType = "movie",
                        episodeCount = 3
                    ),
                ),
                listOf(
                    PeopleCast(
                        backdropPath = "",
                        character = "Mother Malkin",
                        title = "Maggie's Plan",
                        name = "Maggie's Plan",
                        releaseDate = "2016-04-27",
                        firstAirDate = "2016-04-27",
                        mediaType = "movie",
                    ),
                    PeopleCast(
                        backdropPath = "",
                        character = "Mother Malkin",
                        title = "Maggie's Plan",
                        name = "Maggie's Plan",
                        releaseDate = "2016-04-27",
                        firstAirDate = "2016-04-27",
                        mediaType = "tv",
                        episodeCount = 3
                    ),
                )
            ),
        )
    }
}