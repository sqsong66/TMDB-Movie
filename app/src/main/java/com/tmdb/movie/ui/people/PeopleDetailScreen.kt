package com.tmdb.movie.ui.people

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tmdb.movie.R
import com.tmdb.movie.component.BlurHeaderBgComponent
import com.tmdb.movie.component.ErrorPage
import com.tmdb.movie.component.FullScreenPhoto
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.MediaType
import com.tmdb.movie.data.PeopleCast
import com.tmdb.movie.data.PeopleDetails
import com.tmdb.movie.data.SortedPeopleCredits
import com.tmdb.movie.ext.pxToDp
import com.tmdb.movie.ui.people.component.AlsoKnownForComponent
import com.tmdb.movie.ui.people.component.PeopleActingBottomSheet
import com.tmdb.movie.ui.people.component.PeopleBiographyComponent
import com.tmdb.movie.ui.people.component.PeopleDetailLoadingPager
import com.tmdb.movie.ui.people.component.PeopleDetailTopComponent
import com.tmdb.movie.ui.people.component.PeopleKnownForComponent
import com.tmdb.movie.ui.people.vm.PeopleDetailUiState
import com.tmdb.movie.ui.people.vm.PeopleDetailViewModel
import com.tmdb.movie.ui.theme.TMDBMovieTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PeopleDetailRoute(
    onBackClick: (Boolean) -> Unit, detailType: Int, // 0-首页进入 1-详情进入； 首页进入需要隐藏BottomBar，关闭时显示BottomBar
    toMovieDetail: (Int, @MediaType Int) -> Unit, viewModel: PeopleDetailViewModel = hiltViewModel()
) {

    BackHandler { onBackClick(detailType == 0) }
    var previewImageUrl by rememberSaveable { mutableStateOf<String?>(null) }
    val config by viewModel.configStream.collectAsStateWithLifecycle()
    val peopleCredits by viewModel.peopleCredits.collectAsStateWithLifecycle()
    val peopleDetailUiState by viewModel.peopleDetailUiState.collectAsStateWithLifecycle()

    PeopleDetailScreen(peopleDetailUiState = peopleDetailUiState,
        peopleCredits = peopleCredits,
        onBackClick = { onBackClick(detailType == 0) },
        onBuildImage = { url, type, size -> config.buildImageUrl(url, type, size) },
        toMovieDetail = toMovieDetail,
        onPreviewImage = { url ->
            previewImageUrl = url
        })

    if (previewImageUrl != null) {
        FullScreenPhoto(
            photoUrl = previewImageUrl!!,
            onDismiss = {
                previewImageUrl = null
            },
        )
    }
}

@Composable
fun PeopleDetailScreen(
    peopleDetailUiState: PeopleDetailUiState,
    peopleCredits: SortedPeopleCredits? = null,
    onBackClick: (Boolean) -> Unit,
    onPreviewImage: (String?) -> Unit,
    toMovieDetail: (Int, @MediaType Int) -> Unit,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
) {
    val scrollState = rememberScrollState()
    var imageUrl by remember { mutableStateOf("") }
    var peopleName by remember { mutableStateOf("") }
    var topBarHeight by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        BlurHeaderBgComponent(
            imageUrl = imageUrl,
            useBlur = true,
            scaleFactor = 2,
            blurRadius = 12
        )
        when (peopleDetailUiState) {
            is PeopleDetailUiState.Error -> ErrorPage()
            PeopleDetailUiState.Loading -> PeopleDetailLoadingPager(topBarHeight = topBarHeight)
            is PeopleDetailUiState.Success -> {
                peopleName = peopleDetailUiState.data.name ?: ""
                imageUrl = onBuildImage(peopleDetailUiState.data.profilePath, ImageType.PROFILE, ImageSize.SMALL) ?: ""
                PeopleDetailComponent(
                    topBarHeight = topBarHeight,
                    scrollState = scrollState,
                    peopleDetails = peopleDetailUiState.data,
                    peopleCredits = peopleCredits,
                    onBuildImage = onBuildImage,
                    toMovieDetail = toMovieDetail,
                    onPreviewImage = onPreviewImage
                )
            }
        }
        PeopleDetailTopBar(
            modifier = Modifier.onGloballyPositioned {
                topBarHeight = it.size.height
            }, scrollState = scrollState, peopleName = peopleName, onBackClick = onBackClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleDetailTopBar(
    modifier: Modifier,
    scrollState: ScrollState,
    peopleName: String,
    onBackClick: (Boolean) -> Unit,
) {
    var topBarHeight by rememberSaveable { mutableIntStateOf(0) }
    var topBarAlpha by rememberSaveable { mutableFloatStateOf(0f) }

    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.value }.collectLatest { scrollValue ->
            val deltaY = scrollValue.toFloat().coerceAtMost(topBarHeight.toFloat())
            topBarAlpha = if (topBarHeight == 0) 0f else deltaY / topBarHeight.toFloat()
        }
    }

    TopAppBar(
        modifier = modifier.onGloballyPositioned {
            topBarHeight = it.size.height
        },
        title = {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = peopleName, style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = topBarAlpha)
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onBackClick(true)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = ""
                )
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = topBarAlpha),
        )
    )
}

@Composable
fun PeopleDetailComponent(
    topBarHeight: Int,
    scrollState: ScrollState,
    peopleDetails: PeopleDetails,
    peopleCredits: SortedPeopleCredits?,
    onPreviewImage: (String?) -> Unit = {},
    toMovieDetail: (Int, @MediaType Int) -> Unit,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
) {

    var showAllKnownForBottomSheet by remember { mutableStateOf(false) }
    val sortedCasts = peopleCredits?.sortedCasts
    if (showAllKnownForBottomSheet && sortedCasts != null) {
        PeopleActingBottomSheet(name = peopleDetails.name ?: "", castLists = sortedCasts, onBottomSheetDismiss = {
            showAllKnownForBottomSheet = false
        }, onBuildImage = { url, type ->
            onBuildImage(url, type, ImageSize.MEDIUM)
        }, toMovieDetail = { id, type ->
            toMovieDetail(id, type)
        })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(topBarHeight.pxToDp()))
        PeopleDetailTopComponent(
            modifier = Modifier.padding(top = 8.dp),
            peopleDetails = peopleDetails,
            onBuildImage = onBuildImage,
            onPreviewImage = onPreviewImage,
        )
        PeopleBiographyComponent(
            modifier = Modifier.padding(top = 24.dp), biography = peopleDetails.getPeopleBiography(LocalContext.current)
        )
        if (peopleCredits?.knownForCredits?.isNotEmpty() == true) {
            PeopleKnownForComponent(modifier = Modifier.padding(top = 24.dp),
                peopleCredits = peopleCredits.knownForCredits,
                onBuildImage = { url, type ->
                    onBuildImage(url, type, ImageSize.MEDIUM)
                },
                toMovieDetail = toMovieDetail,
                onViewAllKnownFor = {
                    showAllKnownForBottomSheet = true
                })
        }
        if (peopleDetails.alsoKnownAs?.isNotEmpty() == true) {
            AlsoKnownForComponent(
                modifier = Modifier.padding(top = 18.dp), knownList = peopleDetails.alsoKnownAs
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PeopleDetailScreenPreview() {
    TMDBMovieTheme {
        PeopleDetailScreen(peopleDetailUiState = PeopleDetailUiState.Loading,
//            peopleDetailUiState = PeopleDetailUiState.Success(
//                PeopleDetails(
//                    name = "Julianne Moore",
//                    gender = 1,
//                    birthday = "1960-12-03",
//                    placeOfBirth = "Fayetteville, North Carolina, USA",
//                    knownForDepartment = "Acting",
//                    popularity = 238.24f,
//                    biography = "Julie Anne Smith (born December 3, 1960), known professionally as Julianne Moore, is an American actress and author. Prolific in film since the early 1990s, she is particularly known for her portrayals of emotionally troubled women in independent films, as well as for her roles in blockbusters. She is the recipient of numerous accolades, including an Academy Award, a British Academy Film Award, two Golden Globe Awards, and two Emmy Awards.\\n\\nAfter studying theater at Boston University, Moore began her career with a series of television roles. From 1985 to 1988, she was a regular in the soap opera As the World Turns, earning a Daytime Emmy Award for her performance. Her film debut was in Tales from the Darkside: The Movie (1990), and she continued to play small roles for the next four years, including in the thriller The Hand That Rocks the Cradle (1992). Moore first received critical attention with Robert Altman's Short Cuts (1993), and successive performances in Vanya on 42nd Street (1994) and Safe (1995) continued this acclaim. Starring roles in the blockbusters Nine Months (1995) and The Lost World: Jurassic Park (1997) established her as a Hollywood leading lady.\\n\\nMoore received considerable recognition in the late 1990s and early 2000s, earning Academy Award nominations for Boogie Nights (1997), The End of the Affair (1999), Far from Heaven (2002) and The Hours (2002). In the first of these, she played a 1970s pornographic actress, while in the other three, she starred as a mid-20th century unhappy housewife. She also had success with the films The Big Lebowski (1998), Magnolia (1999), Hannibal (2001), Children of Men (2006), A Single Man (2009), The Kids Are All Right (2010), and Crazy, Stupid, Love (2011). She won a Primetime Emmy Award for her portrayal of Sarah Palin in the television film Game Change (2012). She won the Academy Award for Best Actress for her portrayal of an Alzheimer's patient in Still Alice (2014) and was named Best Actress at the Cannes Film Festival for her role in Maps to the Stars (2014). Among her highest-grossing releases are the final two films in the series The Hunger Games and the spy film Kingsman: The Golden Circle (2017).\\n\\nIn addition to her acting work, Moore has written a series of children's books about a character named \\\"Freckleface Strawberry\\\". In 2015, Time magazine named her one of the 100 most influential people in the world, and in 2020, The New York Times named her one of the greatest actors of the 21st century. She is married to director Bart Freundlich, with whom she has two children.",
//                    alsoKnownAs = listOf(
//                        "Julianne Moore",
//                        "Джулианна Мур",
//                        "جوليان مور",
//                        "줄리안 무어",
//                        "ジュリアン・ムーア",
//                        "茱莉安·摩尔",
//                        "茱莉�"
//                    )
//                ),
//            ),
            peopleCredits = SortedPeopleCredits(
                knownForCredits = listOf(
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
                    ),
                    PeopleCast(
                        backdropPath = "",
                        character = "Mother Malkin",
                        title = "Maggie's Plan",
                        name = "Maggie's Plan",
                        releaseDate = "",
                        firstAirDate = "2016-04-27",
                        mediaType = "movie",
                    ),
                )
            ), onBackClick = {}, toMovieDetail = { _, _ -> }, onPreviewImage = {})
    }
}

