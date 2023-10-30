package com.tmdb.movie.ui.tv

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.Season
import com.tmdb.movie.ui.theme.TMDBMovieTheme
import com.tmdb.movie.ui.tv.component.SeasonDetailHeader
import com.tmdb.movie.ui.tv.component.SeasonDetailTopBar
import com.tmdb.movie.ui.tv.vm.TVDetailViewModel

@Composable
fun TVSeasonDetailRoute(
    tvName: String,
    onBackClick: (Boolean) -> Unit,
    viewModel: TVDetailViewModel = hiltViewModel(),
) {

    val seasonDetail by viewModel.seasonDetail.collectAsStateWithLifecycle()
    val configStream by viewModel.configStream.collectAsStateWithLifecycle()

    TVSeasonDetailScreen(
        tvName = tvName,
        onBackClick = onBackClick,
        seasonDetail = seasonDetail,
        onBuildImage = { url, type, size ->
            configStream.buildImageUrl(url, type, size)
        },
    )
}

@Composable
fun TVSeasonDetailScreen(
    tvName: String,
    onBackClick: (Boolean) -> Unit,
    seasonDetail: Season? = null,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
) {

    Box(modifier = Modifier.fillMaxSize()) {
        if (seasonDetail != null) {
            SeasonDetailHeader(
                tvName = tvName,
                season = seasonDetail,
                onBuildImage = onBuildImage,
            )
        }

        SeasonDetailTopBar(
            title = seasonDetail?.name ?: "",
            onBackClick = onBackClick,
        )
    }

}

@Composable
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun TVSeasonDetailScreenPreview() {
    TMDBMovieTheme {
        TVSeasonDetailScreen(
            onBackClick = {},
            tvName = "TV Name",
            seasonDetail = Season(
                id = 1,
                name = "Season 1",
                overview = "Season 1 overview",
                posterPath = "/poster",
                seasonNumber = 1,
                airDate = "2021-01-01",
                episodeCount = 10,
                voteAverage = 8.0f,
            )
        )
    }
}