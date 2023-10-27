package com.tmdb.movie.ui.detail.component

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
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
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.tmdb.movie.R
import com.tmdb.movie.component.AutoResizeText
import com.tmdb.movie.component.FontSizeRange
import com.tmdb.movie.component.ProfileTitleComponent
import com.tmdb.movie.data.Cast
import com.tmdb.movie.data.Credits
import com.tmdb.movie.data.Genre
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.MediaType
import com.tmdb.movie.data.MovieDetails
import com.tmdb.movie.data.Video
import com.tmdb.movie.ext.formatWithCommasAndDecimals
import com.tmdb.movie.ui.theme.TMDBMovieTheme
import eu.wewox.textflow.TextFlow
import eu.wewox.textflow.TextFlowObstacleAlignment

@Composable
fun MovieBackdropLayout(
    @MediaType mediaType: Int,
    movieDetails: MovieDetails?,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
) {
    val context = LocalContext.current
    val placeholderBitmap = AppCompatResources.getDrawable(context, R.drawable.image_placeholder_horizontal)?.toBitmap()?.apply {
        eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = ImageRequest.Builder(LocalContext.current)
                .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                .error(BitmapDrawable(context.resources, placeholderBitmap))
                .data(onBuildImage(movieDetails?.backdropPath, ImageType.BACKDROP)).crossfade(true).build(),
            contentScale = ContentScale.FillWidth,
            contentDescription = ""
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background.copy(0.0f),
                            MaterialTheme.colorScheme.background.copy(1.0f),
                        )
                    )
                )
                .padding(top = 28.dp)
                .align(Alignment.BottomCenter)
        ) {
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                AutoResizeText(
                    text = movieDetails?.getMovieName(mediaType) ?: "",
                    modifier = Modifier
                        .weight(1.0f, fill = false)
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSizeRange = FontSizeRange(16.sp, 22.sp),
                    maxLines = 2,
                )
                if (movieDetails?.getReleaseYear(mediaType)?.isNotEmpty() == true) {
                    Text(
                        text = "(${movieDetails.getReleaseYear(mediaType)})",
                        modifier = Modifier
                            .padding(start = 4.dp, bottom = 2.dp)
                            .align(Alignment.Bottom),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Composable
fun MovieMiddleLayout(
    @MediaType mediaType: Int, modifier: Modifier = Modifier, movieDetails: MovieDetails?
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (movieDetails?.tagline?.isNotEmpty() == true) {
            Text(
                text = movieDetails.tagline,
                modifier = Modifier.padding(start = 16.dp, top = 6.dp),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    fontStyle = FontStyle.Italic,
                ),
            )
        }

        movieDetails?.genres?.let { genres ->
            LazyRow(modifier = Modifier.padding(top = 6.dp)) {
                items(genres.size) { index ->
                    AssistChip(
                        modifier = Modifier.padding(
                            start = if (index == 0) 16.dp else 4.dp,
                            end = if (index == genres.size - 1) 16.dp else 4.dp,
                        ),
                        onClick = { },
                        label = {
                            Text(
                                modifier = Modifier.padding(horizontal = 6.dp),
                                text = genres[index].name ?: "",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 1.0f)
                            )
                        },
                        shape = MaterialTheme.shapes.large,
                        colors = AssistChipDefaults.assistChipColors(),
                        border = AssistChipDefaults.assistChipBorder(borderColor = MaterialTheme.colorScheme.primary)
                    )
                    /*AssistChip(
                        modifier = Modifier.padding(
                            start = if (index == 0) 16.dp else 4.dp,
                            end = if (index == genres.size - 1) 16.dp else 4.dp,
                        ),
                        onClick = { },
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        colors = ChipDefaults.chipColors(backgroundColor = Color.Transparent)
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 6.dp),
                            text = genres[index].name ?: "",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 1.0f)
                        )
                    }*/
                }
            }
        }

        Divider(
            modifier = Modifier.padding(
                start = 16.dp, end = 16.dp, top = 16.dp
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1.0f), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AutoResizeText(
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp), text = if (mediaType == MediaType.MOVIE) {
                        movieDetails?.getFormatRevenue() ?: "0.00"
                    } else {
                        movieDetails?.popularity?.formatWithCommasAndDecimals(2) ?: "0.00"
                    }, style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
                    ), fontSizeRange = FontSizeRange(12.sp, 16.sp), maxLines = 1
                )

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(if (mediaType == MediaType.MOVIE) R.string.key_revenue else R.string.key_popularity),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }

            Column(
                modifier = Modifier
                    .weight(1.0f)
                    .align(Alignment.Bottom), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RatingBar(modifier = Modifier,
                        value = (movieDetails?.voteAverage ?: 0f) / 2f,
                        style = RatingBarStyle.Fill(
                            activeColor = MaterialTheme.colorScheme.primary,
                            inActiveColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                        ),
                        size = 10.dp,
                        spaceBetween = 1.dp,
                        numOfStars = 5,
                        onValueChange = {},
                        onRatingChanged = {})
                    Text(
                        modifier = Modifier.padding(start = 3.dp),
                        text = "(${String.format("%.1f", movieDetails?.voteAverage)})",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    text = stringResource(R.string.key_vote_point),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }

            Column(
                modifier = Modifier.weight(1.0f), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AutoResizeText(
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    text = movieDetails?.getNiceDate(mediaType, false) ?: stringResource(id = R.string.key_unknown),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
                    ),
                    fontSizeRange = FontSizeRange(12.sp, 16.sp),
                    maxLines = 1
                )

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(if (mediaType == MediaType.MOVIE) R.string.key_release_date else R.string.key_first_air_date),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        }

        Divider(
            modifier = Modifier.padding(
                start = 16.dp, end = 16.dp, top = 24.dp
            )
        )
    }
}

@Composable
fun MovieOverviewLayout(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetails?,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
) {
    val context = LocalContext.current
    val placeholderBitmap = AppCompatResources.getDrawable(context, R.drawable.image_placeholder)?.toBitmap()?.apply {
        eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.key_overview),
            modifier = Modifier,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
        )
        TextFlow(modifier = Modifier.padding(top = 12.dp),
            text = movieDetails?.getMovieOverview(context) ?: "",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
            ),
            obstacleAlignment = TextFlowObstacleAlignment.TopStart,
            obstacleContent = {
                AsyncImage(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .width(120.dp)
                        .clip(MaterialTheme.shapes.medium),
                    model = ImageRequest.Builder(LocalContext.current).placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                        .error(BitmapDrawable(context.resources, placeholderBitmap)).data(onBuildImage(movieDetails?.posterPath, ImageType.POSTER))
                        .crossfade(true).build(),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = ""
                )
            })
    }
}

@Composable
fun MovieCastLayout(
    modifier: Modifier = Modifier,
    isTv: Boolean,
    castList: List<Cast>,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
    onMoreCasts: (List<Cast>) -> Unit,
    onPeopleDetail: (Int) -> Unit,
) {
    val castSize = castList.size.coerceAtMost(10)
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        ProfileTitleComponent(
            modifier = Modifier,
            title = stringResource(if (isTv) R.string.key_series_cast else R.string.key_top_billed_cast),
            showMoreText = castList.size > 10,
            moreText = stringResource(id = R.string.key_view_all),
            onMoreTextClick = { onMoreCasts(castList) }
        )

        LazyRow(modifier = Modifier.padding(top = 10.dp)) {
            items(castSize) { index ->
                MainCastComponent(
                    modifier = Modifier.padding(
                        start = if (index == 0) 16.dp else 8.dp,
                        end = if (index == castList.size - 1) 16.dp else 8.dp,
                    ),
                    cast = castList[index],
                    onBuildImage = onBuildImage,
                    onPeopleDetail = onPeopleDetail
                )
            }
        }

    }
}

@Composable
fun MainCastComponent(
    modifier: Modifier = Modifier,
    cast: Cast,
    onPeopleDetail: (Int) -> Unit,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
) {
    val context = LocalContext.current
    var isImageError by rememberSaveable { mutableStateOf(false) }
    val placeholderBitmap = AppCompatResources.getDrawable(context, R.drawable.image_placeholder)?.toBitmap()?.apply {
        eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
    }
    Column(modifier = modifier.width(60.dp)) {
        if (isImageError) {
            Image(
                modifier = Modifier
                    .size(60.dp)
                    .border(
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary), shape = CircleShape
                    )
                    .clip(CircleShape)
                    .clickable { onPeopleDetail(cast.id) },
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.baseline_account_circle_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
            )
        } else {
            AsyncImage(
                modifier = Modifier
                    .size(60.dp)
                    .border(
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary), shape = CircleShape
                    )
                    .clip(CircleShape)
                    .clickable { onPeopleDetail(cast.id) },
                model = ImageRequest.Builder(LocalContext.current).placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                    .error(BitmapDrawable(context.resources, placeholderBitmap)).data(onBuildImage(cast.profilePath, ImageType.PROFILE))
                    .crossfade(true).listener(onError = { _, _ ->
                        isImageError = true
                    }).build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        }

        Text(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth(),
            text = cast.name ?: "",
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f), lineHeight = 16.sp
            ),
            maxLines = 2,
            minLines = 2,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieVideoComponent(
    modifier: Modifier = Modifier,
    onMoreVideos: (List<Video>) -> Unit,
    videos: List<Video>,
    onVideoClick: (String?, Boolean) -> Unit
) {
    val maxSize = videos.size.coerceAtMost(5)
    var currentPage by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { maxSize }

    LaunchedEffect(pagerState.currentPage) {
        currentPage = pagerState.currentPage
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        ProfileTitleComponent(
            modifier = Modifier,
            title = stringResource(R.string.key_videos),
            showMoreText = videos.size > 5,
            moreText = stringResource(id = R.string.key_view_all),
            onMoreTextClick = { onMoreVideos(videos) }
        )
        HorizontalPager(
            modifier = Modifier.padding(top = 10.dp),
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 8.dp,
        ) { index ->
            VideoPagerComponent(
                video = videos[index],
                onVideoClick = onVideoClick,
            )
        }
    }
}

@Composable
fun VideoPagerComponent(
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
            modifier = Modifier
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
                .clip(RoundedCornerShape(8.dp))
                .background(
                    color = MaterialTheme.colorScheme.background.copy(alpha = 0.8f),
                )
                .padding(horizontal = 16.dp, vertical = if (video.isYoutube()) 6.dp else 10.dp),
            painter = painterResource(id = if (video.isYoutube()) R.drawable.logo_youtube else R.drawable.logo_vimeo),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.Red)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovieVideoComponentPreview() {
    val movieDetails = MovieDetails(
        title = "阿凡达：水之道",
        releaseDate = "2022-12-14",
        tagline = "传奇导演詹姆斯·卡梅隆全新巨作",
        genres = listOf(Genre(id = 1, name = "动作"), Genre(id = 2, name = "冒险"), Genre(id = 3, name = "科幻")),
        voteAverage = 7.655f,
        revenue = 232025.00,
        overview = "影片设定在《阿凡达》的剧情落幕十余年后，讲述了萨利一家（杰克、奈蒂莉和孩子们）的故事：危机未曾消散，一家人拼尽全力彼此守护、奋力求生，并历经艰险磨难。影片设定在《阿凡达》的剧情落幕十余年后，讲述了萨利一家（杰克、奈蒂莉和孩子们）的故事：危机未曾消散，一家人拼尽全力彼此守护、奋力求生，并历经艰险磨难。影片设定在《阿凡达》的剧情落幕十余年后，讲述了萨利一家（杰克、奈蒂莉和孩子们）的故事：危机未曾消散，一家人拼尽全力彼此守护、奋力求生，并历经艰险磨难。",
        credits = Credits(
            cast = listOf(
                Cast(name = "Sam Worthington", character = "Jake Sully", profilePath = "/mflBcox36s9ZPbsZPVOuhf6axaJ.jpg"),
                Cast(name = "Sam Worthington", character = "Jake Sully", profilePath = "/mflBcox36s9ZPbsZPVOuhf6axaJ.jpg"),
                Cast(name = "Sam Worthington", character = "Jake Sully", profilePath = "/mflBcox36s9ZPbsZPVOuhf6axaJ.jpg"),
            ),
        ),
        videos = com.tmdb.movie.data.Videos(
            results = listOf(
                Video(
                    key = "https://www.youtube.com/watch?v=5PSNL1qE6VY",
                    name = "阿凡达：水之道",
                    site = "YouTube",
                    type = "Trailer",
                ),
                Video(
                    key = "https://www.youtube.com/watch?v=5PSNL1qE6VY",
                    name = "阿凡达：水之道",
                    site = "YouTube",
                    type = "Trailer",
                ),
                Video(
                    key = "https://www.youtube.com/watch?v=5PSNL1qE6VY",
                    name = "阿凡达：水之道",
                    site = "YouTube",
                    type = "Trailer",
                ),
            )
        )
    )

    TMDBMovieTheme {
//        MovieVideoComponent(modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 12.dp), videos = listOf(
//            Video(
//                id = "1",
//                iso31661 = "US",
//                iso6391 = "en",
//                key = "vsBwcxu8bAQ",
//                name = "name",
//                official = true,
//                publishedAt = "2021-09-01",
//                site = "YouTube",
//                size = 1080,
//                type = "Trailer"
//            ), Video(
//                id = "1",
//                iso31661 = "US",
//                iso6391 = "en",
//                key = "vsBwcxu8bAQ",
//                name = "name",
//                official = true,
//                publishedAt = "2021-09-01",
//                site = "YouTube",
//                size = 1080,
//                type = "Trailer"
//            ), Video(
//                id = "1",
//                iso31661 = "US",
//                iso6391 = "en",
//                key = "vsBwcxu8bAQ",
//                name = "name",
//                official = true,
//                publishedAt = "2021-09-01",
//                site = "YouTube",
//                size = 1080,
//                type = "Trailer"
//            ), Video(
//                id = "1",
//                iso31661 = "US",
//                iso6391 = "en",
//                key = "vsBwcxu8bAQ",
//                name = "name",
//                official = true,
//                publishedAt = "2021-09-01",
//                site = "YouTube",
//                size = 1080,
//                type = "Trailer"
//            )
//        ),
//            onVideoClick = {},
//            onMoreVideos = {})
        Column(modifier = Modifier.fillMaxSize()) {
            MovieBackdropLayout(mediaType = MediaType.MOVIE, movieDetails = movieDetails)
            MovieMiddleLayout(
                modifier = Modifier.padding(bottom = 24.dp),
                mediaType = MediaType.MOVIE,
                movieDetails = movieDetails
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieVideoComponentPreview2() {
    TMDBMovieTheme {
//        MovieDetailImageComponent(
//            imageType = ImageType.BACKDROP, imageList = listOf(
//                MovieImage(),
//                MovieImage(),
//            )
//        )
        MovieDetailLoadingComponent()
    }
}

@Composable
fun MovieDetailLoadingComponent(isTv: Boolean = false) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
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
                painter = painterResource(id = R.drawable.image_placeholder_horizontal),
                contentScale = ContentScale.FillWidth,
                contentDescription = ""
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.background.copy(0.0f),
                                MaterialTheme.colorScheme.background.copy(1.0f),
                            )
                        )
                    )
                    .padding(top = 28.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = "xxxxxxxxxxxxxxxxxx",
                        modifier = Modifier
                            .weight(1.0f, fill = false)
                            .align(Alignment.CenterVertically)
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
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onBackground,
                        maxLines = 2,
                    )
                }
            }
        }

        // MovieMiddleLayout
        Text(
            text = "xxxxxxxxxxxxxxxx",
            modifier = Modifier
                .padding(start = 16.dp, top = 10.dp)
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
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                fontStyle = FontStyle.Italic,
            ),
        )

        LazyRow(modifier = Modifier.padding(top = 10.dp)) {
            items(3) { index ->
                Text(
                    modifier = Modifier
                        .height(32.dp)
                        .padding(
                            start = if (index == 0) 16.dp else 4.dp,
                            end = if (index == 2) 16.dp else 4.dp,
                        )
                        .placeholder(
                            visible = true,
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(30.dp),
                            highlight = PlaceholderHighlight.shimmer(
                                highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                                    alpha = 0.25f
                                )
                            )
                        ),
                    text = "xxxxxxxxxxxx",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 1.0f)
                )
            }
        }

        Divider(
            modifier = Modifier
                .padding(
                    start = 16.dp, end = 16.dp, top = 24.dp
                )
                .placeholder(
                    visible = true,
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(4.dp),
                    highlight = PlaceholderHighlight.shimmer(
                        highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                            alpha = 0.25f
                        )
                    )
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1.0f), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .placeholder(
                            visible = true,
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderHighlight.shimmer(
                                highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                                    alpha = 0.25f
                                )
                            )
                        ), text = "xxxxxxx", style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
                    ), maxLines = 1
                )

                Text(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .placeholder(
                            visible = true,
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderHighlight.shimmer(
                                highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                                    alpha = 0.25f
                                )
                            )
                        ), text = stringResource(id = R.string.key_revenue), style = MaterialTheme.typography.bodyMedium
                )
            }

            Column(
                modifier = Modifier
                    .weight(1.0f)
                    .align(Alignment.Bottom), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RatingBar(modifier = Modifier.placeholder(
                    visible = true,
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(4.dp),
                    highlight = PlaceholderHighlight.shimmer(
                        highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                            alpha = 0.25f
                        )
                    )
                ), value = 0f, style = RatingBarStyle.Fill(
                    activeColor = MaterialTheme.colorScheme.primary,
                    inActiveColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                ), size = 12.dp, spaceBetween = 2.dp, numOfStars = 5, onValueChange = {}, onRatingChanged = {})

                Text(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .placeholder(
                            visible = true,
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderHighlight.shimmer(
                                highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                                    alpha = 0.25f
                                )
                            )
                        ), text = stringResource(R.string.key_vote_point), style = MaterialTheme.typography.bodyMedium
                )
            }

            Column(
                modifier = Modifier.weight(1.0f), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .placeholder(
                            visible = true,
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderHighlight.shimmer(
                                highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                                    alpha = 0.25f
                                )
                            )
                        ), text = "xxxxxxx", style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
                    ), maxLines = 1
                )

                Text(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .placeholder(
                            visible = true,
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderHighlight.shimmer(
                                highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                                    alpha = 0.25f
                                )
                            )
                        ), text = stringResource(R.string.key_release_date), style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Divider(
            modifier = Modifier
                .padding(
                    start = 16.dp, end = 16.dp, top = 24.dp
                )
                .placeholder(
                    visible = true,
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(4.dp),
                    highlight = PlaceholderHighlight.shimmer(
                        highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                            alpha = 0.25f
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp, end = 16.dp, top = 24.dp
                )
        ) {
            Text(
                text = stringResource(R.string.key_overview),
                modifier = Modifier.placeholder(
                    visible = true,
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(4.dp),
                    highlight = PlaceholderHighlight.shimmer(
                        highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                            alpha = 0.25f
                        )
                    )
                ),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
            )
            Row(modifier = Modifier.padding(top = 6.dp)) {
                Image(
                    modifier = Modifier
                        .padding(end = 16.dp, top = 12.dp)
                        .width(120.dp)
                        .clip(MaterialTheme.shapes.medium)
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
                    painter = painterResource(id = R.drawable.image_placeholder),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = ""
                )

                Column(modifier = Modifier.padding(top = 6.dp)) {
                    repeat(6) {
                        Text(
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .placeholder(
                                    visible = true,
                                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(4.dp),
                                    highlight = PlaceholderHighlight.shimmer(
                                        highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                                            alpha = 0.25f
                                        )
                                    )
                                ), text = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                            ), maxLines = 1
                        )
                    }
                }
            }
        }

        // MainCastLayout
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text(
                text = stringResource(R.string.key_top_billed_cast),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
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
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
            )

            LazyRow(modifier = Modifier.padding(top = 16.dp)) {
                items(6) { index ->
                    Image(
                        modifier = Modifier
                            .padding(
                                start = if (index == 0) 16.dp else 8.dp, end = if (index == 5) 16.dp else 8.dp
                            )
                            .size(60.dp)
                            .placeholder(
                                visible = true,
                                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                                shape = CircleShape,
                                highlight = PlaceholderHighlight.shimmer(
                                    highlightColor = PlaceholderDefaults.shimmerHighlightColor(
                                        alpha = 0.25f
                                    )
                                )
                            )
                            .clip(CircleShape)

                            .clickable { },
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.baseline_account_circle_24),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
                    )
                }
            }
        }

        if (isTv) {
            LatestSeasonComponentPlaceholder(
                modifier = Modifier.padding(
                    top = 24.dp,
                    bottom = 16.dp
                ),
            )
        }
    }
}