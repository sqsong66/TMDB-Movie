package com.tmdb.movie.ui.tv

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tmdb.movie.data.Episode
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.Season
import com.tmdb.movie.data.SeasonDetailParam
import com.tmdb.movie.ui.theme.TMDBMovieTheme
import com.tmdb.movie.ui.tv.component.SeasonDetailHeader
import com.tmdb.movie.ui.tv.component.SeasonDetailTopBar
import com.tmdb.movie.ui.tv.component.SeasonEpisodeItem
import com.tmdb.movie.ui.tv.vm.TVDetailViewModel

@Composable
fun TVSeasonDetailRoute(
    tvName: String,
    onBackClick: (Boolean) -> Unit,
    toEpisodeDetail: (Int, Int) -> Unit,
    viewModel: TVDetailViewModel = hiltViewModel(),
) {

    BackHandler { onBackClick(false) }
    val seasonDetail by viewModel.seasonDetail.collectAsStateWithLifecycle()
    val configStream by viewModel.configStream.collectAsStateWithLifecycle()

    TVSeasonDetailScreen(
        tvName = tvName,
        onBackClick = onBackClick,
        seasonDetail = seasonDetail,
        onBuildImage = { url, type, size ->
            configStream.buildImageUrl(url, type, size)
        },
        toEpisodeDetail = toEpisodeDetail,
    )
}

@Composable
fun TVSeasonDetailScreen(
    tvName: String,
    onBackClick: (Boolean) -> Unit,
    seasonDetail: Season? = null,
    toEpisodeDetail: (Int, Int) -> Unit,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
) {

    Box(modifier = Modifier.fillMaxSize()) {
        if (seasonDetail != null) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    SeasonDetailHeader(
                        modifier = Modifier,
                        tvName = tvName,
                        season = seasonDetail,
                        onBuildImage = onBuildImage,
                    )
                }
                val episodeSize = seasonDetail.episodes?.size ?: 0
                items(episodeSize) { index ->
                    seasonDetail.episodes?.get(index)?.let { episode ->
                        SeasonEpisodeItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    top = if (index == 0) 8.dp else 16.dp,
                                    bottom = if (index == episodeSize - 1) 16.dp else 0.dp
                                ),
                            episode = episode,
                            onBuildImage = onBuildImage,
                            toEpisodeDetail = toEpisodeDetail,
                        )
                    }
                }
            }
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
    val episodes = mutableListOf<Episode>()
    repeat(5) {
        episodes.add(
            Episode(
                id = it,
                name = "Episode $it",
                overview = "With the TVA on the verge of a temporal meltdown, Loki & Mobius will stop at nothing to find Sylvie.",
                stillPath = null,
                voteAverage = 8.0f,
                voteCount = 0,
                airDate = "2021-09-01",
                episodeNumber = it,
                seasonNumber = 1,
                runtime = 45,
            )
        )
    }

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
                episodes = episodes
            ),
            toEpisodeDetail = { _, _ -> },
        )
    }
}