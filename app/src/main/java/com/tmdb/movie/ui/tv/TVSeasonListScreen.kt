package com.tmdb.movie.ui.tv

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.Season
import com.tmdb.movie.data.SeasonDetailParam
import com.tmdb.movie.data.SeasonInfo
import com.tmdb.movie.ui.tv.component.SeasonCollapsingHeader
import com.tmdb.movie.ui.tv.component.SeasonListBody
import com.tmdb.movie.ui.tv.component.SeasonListHeader
import com.tmdb.movie.ui.tv.component.SeasonListTopBar
import com.tmdb.movie.ui.tv.vm.TVDetailViewModel

@Composable
fun TVSeasonListRoute(
    onBackClick: (Boolean) -> Unit,
    toSeasonDetail: (SeasonDetailParam) -> Unit,
    viewModel: TVDetailViewModel = hiltViewModel(),
) {

    BackHandler { onBackClick(false) }
    val config by viewModel.configStream.collectAsStateWithLifecycle()
    val seasonInfo by viewModel.seasonInfo.collectAsStateWithLifecycle()

    LaunchedEffect(config) {
        Log.w("sqsong", "TVSeasonListRoute: $config")
    }

    TVSeasonListScreen(
        seasonInfo = seasonInfo,
        onBackClick = onBackClick,
        onBuildImage = { url, type, size ->
            config.buildImageUrl(url, type, size)
        },
        toSeasonDetail = toSeasonDetail,
    )
}

@Composable
fun TVSeasonListScreen(
    seasonInfo: SeasonInfo?,
    onBackClick: (Boolean) -> Unit,
    toSeasonDetail: (SeasonDetailParam) -> Unit,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
) {
    val listState = rememberLazyListState()
    var headerHeight by remember { mutableFloatStateOf(620f) }
    var topBarHeight by remember { mutableFloatStateOf(0f) }

    Box(modifier = Modifier.fillMaxSize()) {
        SeasonListHeader(
            modifier = Modifier.onGloballyPositioned {
                headerHeight = it.size.height.toFloat()
            },
            backdropPath = seasonInfo?.backdropPath,
            headerHeight = headerHeight,
            listState = listState,
            onBuildImage = onBuildImage
        )

        SeasonListBody(
            modifier = Modifier,
            tvName = seasonInfo?.tvName ?: "",
            listState = listState,
            headerHeight = headerHeight,
            seasons = seasonInfo?.seasons,
            onBuildImage = onBuildImage,
            toSeasonDetail = {
                toSeasonDetail(SeasonDetailParam(seasonInfo?.tvId ?: 0, seasonInfo?.tvName ?: "", it))
            }
        )

        SeasonListTopBar(
            modifier = Modifier.onGloballyPositioned {
                topBarHeight = it.size.height.toFloat()
            },
            listState = listState,
            topBarBottom = headerHeight - topBarHeight,
            onBackClick = {
                onBackClick(false)
            },
        )

        SeasonCollapsingHeader(
            title = seasonInfo?.tvName ?: "",
            listState = listState,
            headerHeight = headerHeight,
            topBarHeight = topBarHeight,
            posterPath = seasonInfo?.posterPath,
            onBuildImage = onBuildImage,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewTVSeasonListScreen() {

    TVSeasonListScreen(
        seasonInfo = SeasonInfo(
            tvId = 0,
            tvName = "The Falcon",
            backdropPath = "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
            airDate = "2021-03-19",
            posterPath = "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            seasons = listOf(
                Season(
                    airDate = "2021-07-20",
                    episodeCount = 18,
                    id = 60735,
                    name = "Season 7",
                    overview = "After a thrilling cliffhanger last season which saw the new Mirror Master victorious and still-at-large in Central City, The Flash must regroup in order to stop her and find a way to make contact with his missing wife, Iris West-Allen. With help from the rest of Team Flash, which includes superheroes Caitlin Snow, Cisco Ramon, Ralph Dibny and Nash Wells, as well as the Flash’s adoptive father Joe West, Meta-Attorney Cecile Horton, tough cub reporter Allegra Garcia and brilliant tech-nerd Chester P. Runk… Flash will ultimately defeat Mirror Master. But in doing so, he’ll also unleash an even more powerful and devastating threat on Central City: one that threatens to tear his team—and his marriage—apart.",
                    posterPath = "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                    seasonNumber = 7,
                    voteAverage = 7.9f,
                ),
                Season(
                    airDate = "2021-07-20",
                    episodeCount = 18,
                    id = 60735,
                    name = "Season 7",
                    overview = "After a thrilling cliffhanger last season which saw the new Mirror Master victorious and still-at-large in Central City, The Flash must regroup in order to stop her and find a way to make contact with his missing wife, Iris West-Allen. With help from the rest of Team Flash, which includes superheroes Caitlin Snow, Cisco Ramon, Ralph Dibny and Nash Wells, as well as the Flash’s adoptive father Joe West, Meta-Attorney Cecile Horton, tough cub reporter Allegra Garcia and brilliant tech-nerd Chester P. Runk… Flash will ultimately defeat Mirror Master. But in doing so, he’ll also unleash an even more powerful and devastating threat on Central City: one that threatens to tear his team—and his marriage—apart.",
                    posterPath = "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                    seasonNumber = 7,
                    voteAverage = 7.9f,
                ),
                Season(
                    airDate = "2021-07-20",
                    episodeCount = 18,
                    id = 60735,
                    name = "Season 7",
                    overview = "After a thrilling cliffhanger last season which saw the new Mirror Master victorious and still-at-large in Central City, The Flash must regroup in order to stop her and find a way to make contact with his missing wife, Iris West-Allen. With help from the rest of Team Flash, which includes superheroes Caitlin Snow, Cisco Ramon, Ralph Dibny and Nash Wells, as well as the Flash’s adoptive father Joe West, Meta-Attorney Cecile Horton, tough cub reporter Allegra Garcia and brilliant tech-nerd Chester P. Runk… Flash will ultimately defeat Mirror Master. But in doing so, he’ll also unleash an even more powerful and devastating threat on Central City: one that threatens to tear his team—and his marriage—apart.",
                    posterPath = "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                    seasonNumber = 7,
                    voteAverage = 7.9f,
                )
            )
        ),
        onBackClick = {},
        onBuildImage = { url, type, size ->
            ""
        },
        toSeasonDetail = { }
    )
}