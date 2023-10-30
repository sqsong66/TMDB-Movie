package com.tmdb.movie.ui.people.component

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.tmdb.movie.R
import com.tmdb.movie.component.AutoResizeText
import com.tmdb.movie.component.FontSizeRange
import com.tmdb.movie.component.ProfileTitleComponent
import com.tmdb.movie.data.ImageSize
import com.tmdb.movie.data.ImageType
import com.tmdb.movie.data.MediaType
import com.tmdb.movie.data.PeopleCast
import com.tmdb.movie.data.PeopleDetails
import com.tmdb.movie.ui.theme.TMDBMovieTheme
import com.webtoonscorp.android.readmore.foundation.ToggleArea
import com.webtoonscorp.android.readmore.material3.ReadMoreText

@Composable
fun PeopleDetailTopComponent(
    modifier: Modifier = Modifier,
    peopleDetails: PeopleDetails,
    onBuildImage: (String?, @ImageType Int, @ImageSize Int) -> String? = { url, _, _ -> url },
    onPreviewImage: (String?) -> Unit = {},
) {
    val context = LocalContext.current
    val placeholderBitmap = AppCompatResources.getDrawable(context, R.drawable.image_placeholder)?.toBitmap()?.apply {
        eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(start = 16.dp)
                .width(120.dp)
                .clip(MaterialTheme.shapes.small)
                .clickable {
                    onPreviewImage(onBuildImage(peopleDetails.profilePath, ImageType.PROFILE, ImageSize.LARGE))
                },
            model = ImageRequest.Builder(LocalContext.current)
                .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                .error(BitmapDrawable(context.resources, placeholderBitmap))
                .data(onBuildImage(peopleDetails.profilePath, ImageType.PROFILE, ImageSize.MEDIUM))
                .crossfade(true).build(),
            contentDescription = "",
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 8.dp
            )
        ) {
            AutoResizeText(
                text = peopleDetails.name ?: "",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                fontSizeRange = FontSizeRange(16.sp, 22.sp)
            )
            PeopleDetailProfile(
                modifier = Modifier.padding(top = 12.dp),
                keyStr = stringResource(id = R.string.key_gender_colon),
                valueStr = peopleDetails.getGenderText(context)
            )
            PeopleDetailProfile(
                modifier = Modifier.padding(top = 6.dp),
                keyStr = stringResource(id = R.string.key_birthday_colon),
                valueStr = peopleDetails.getPrettyBirthday()
            )
            PeopleDetailProfile(
                modifier = Modifier.padding(top = 6.dp),
                keyStr = stringResource(id = R.string.key_place_of_birth_colon),
                valueStr = peopleDetails.placeOfBirth
            )
            PeopleDetailProfile(
                modifier = Modifier.padding(top = 6.dp),
                keyStr = stringResource(id = R.string.key_know_for_colon),
                valueStr = peopleDetails.knownForDepartment
            )
            PeopleDetailProfile(
                modifier = Modifier.padding(top = 6.dp),
                keyStr = stringResource(id = R.string.key_popularity_colon),
                valueStr = peopleDetails.getPopularText()
            )
        }
    }
}

@Composable
fun PeopleDetailProfile(
    modifier: Modifier = Modifier,
    keyStr: String,
    valueStr: String?,
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                MaterialTheme.typography.bodyLarge.copy(
                    // fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                )
                    .toSpanStyle()
            ) {
                append(keyStr)
            }
            withStyle(
                MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f)
                )
                    .toSpanStyle()
            ) {
                append(valueStr ?: stringResource(id = R.string.key_unknown))
            }
        },
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge.copy(
            lineHeight = 18.sp
        )
    )
}

@Composable
fun PeopleBiographyComponent(
    modifier: Modifier = Modifier,
    biography: String?,
) {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        ProfileTitleComponent(
            modifier = Modifier,
            title = stringResource(R.string.key_biography),
            showMoreText = false,
            moreText = "",
            onMoreTextClick = {}
        )
        ReadMoreText(
            modifier = Modifier.padding(top = 10.dp, start = 16.dp, end = 16.dp),
            text = biography ?: "",
            expanded = expanded,
            readMoreMaxLines = 8,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
            ),
            readMoreText = stringResource(R.string.key_read_more),
            readLessText = stringResource(R.string.key_read_less),
            onExpandedChange = onExpandedChange,
            readMoreStyle = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.primary
            ).toSpanStyle(),
            toggleArea = ToggleArea.More
        )
    }
}

fun LazyListScope.peopleActingComponent(
    modifier: Modifier = Modifier,
    sortedCasts: List<List<PeopleCast>>,
    isTest: Boolean = false,
    toMovieDetail: (Int, @MediaType Int) -> Unit,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
) {
    items(sortedCasts.size) { index ->
        val castList = sortedCasts[index]
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            castList.forEachIndexed { index, peopleCast ->
                PeopleCastComponent(
                    modifier = Modifier,
                    peopleCast = peopleCast,
                    isFirst = index == 0,
                    isTest = isTest,
                    onBuildImage = onBuildImage,
                    toMovieDetail = toMovieDetail
                )
            }
        }
    }
}

@Composable
fun PeopleCastComponent(
    modifier: Modifier = Modifier,
    peopleCast: PeopleCast,
    isFirst: Boolean = true,
    isTest: Boolean = false,
    toMovieDetail: (Int, @MediaType Int) -> Unit,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
) {
    val context = LocalContext.current
    val date = peopleCast.getDate()
    val monthDay = peopleCast.getNiceMonthDay()
    var isImageError by rememberSaveable { mutableStateOf(false) }
    val placeholderBitmap = AppCompatResources.getDrawable(context, R.drawable.poster)?.toBitmap()?.apply {
        eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
    }

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
            .error(BitmapDrawable(context.resources, placeholderBitmap))
            .data(onBuildImage(peopleCast.posterPath, ImageType.POSTER))
            .crossfade(true)
            .size(Size.ORIGINAL)
            .listener(onError = { _, _ ->
                isImageError = true
            })
            .build()
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(45.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isFirst) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .border(2.dp, color = MaterialTheme.colorScheme.primary, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    if (date.isNotEmpty()) {
                        Text(
                            text = peopleCast.getSplitYear(),
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 12.sp
                            )
                        )
                    } else {
                        HorizontalDivider(
                            modifier = Modifier.width(12.dp),
                            thickness = 2.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                }
            } else {
                Box(
                    modifier = Modifier
                        .size(45.dp),
                    contentAlignment = Alignment.Center
                ) {
                    VerticalDivider(
                        modifier = Modifier
                            .width(1.dp)
                            .fillMaxHeight(),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape),
                    )
                }
            }

            VerticalDivider(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .width(1.dp)
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            if (monthDay.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(top = 14.dp),
                    text = monthDay,
                    style = MaterialTheme.typography.bodySmall
                        .copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                )
            }
            Row {
                if (isTest) {
                    Image(
                        modifier = Modifier
                            .width(100.dp)
                            .padding(top = if (monthDay.isEmpty()) 16.dp else 8.dp)
                            .clip(MaterialTheme.shapes.small),
                        painter = painterResource(id = R.drawable.backdrop),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth
                    )
                } else {
                    Box(modifier = Modifier
                        .width(100.dp)
                        .padding(top = if (monthDay.isEmpty()) 16.dp else 8.dp)
                        .clip(MaterialTheme.shapes.small)
                        .clickable { toMovieDetail(peopleCast.id, peopleCast.getMovieType()) }) {
                        Image(
                            modifier = Modifier,
                            painter = painter,
                            contentDescription = "",
                            contentScale = ContentScale.FillWidth,
                        )
                        if (isImageError) {
                            Image(
                                modifier = Modifier
                                    .size(45.dp)
                                    .align(Alignment.Center),
                                painter = painterResource(id = R.drawable.baseline_broken_image),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1.0f)
                        .padding(
                            top = if (monthDay.isEmpty()) 16.dp else 8.dp,
                            start = 16.dp, end = 16.dp
                        )
                ) {
                    Text(
                        modifier = Modifier,
                        text = peopleCast.getCastName(),
                        style = MaterialTheme.typography.bodyLarge
                            .copy(color = MaterialTheme.colorScheme.onSurface),
                        overflow = TextOverflow.Ellipsis
                    )
                    if (peopleCast.character?.isNotEmpty() == true) {
                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = peopleCast.getActCharacter(context),
                            style = MaterialTheme.typography.bodySmall
                                .copy(
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                                    fontStyle = FontStyle.Italic
                                )
                        )
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun PeopleKnownForComponent(
    modifier: Modifier = Modifier,
    peopleCredits: List<PeopleCast>,
    toMovieDetail: (Int, @MediaType Int) -> Unit,
    onViewAllKnownFor: () -> Unit,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        ProfileTitleComponent(
            modifier = Modifier,
            title = stringResource(R.string.key_know_for),
            showMoreText = true,
            moreText = stringResource(id = R.string.key_view_all),
            onMoreTextClick = onViewAllKnownFor
        )

        LazyRow(modifier = Modifier.padding(top = 10.dp)) {
            items(peopleCredits.size) { index ->
                PeopleCreditItem(
                    modifier = Modifier.padding(
                        start = if (index == 0) 16.dp else 8.dp,
                        end = if (index == peopleCredits.size - 1) 16.dp else 8.dp
                    ),
                    peopleCredit = peopleCredits[index],
                    onBuildImage = onBuildImage,
                    toMovieDetail = toMovieDetail
                )
            }
        }
    }
}

@Composable
fun PeopleCreditItem(
    modifier: Modifier,
    peopleCredit: PeopleCast,
    toMovieDetail: (Int, @MediaType Int) -> Unit,
    onBuildImage: (String?, @ImageType Int) -> String? = { url, _ -> url },
) {
    val context = LocalContext.current
    val placeholderBitmap =
        AppCompatResources.getDrawable(context, R.drawable.image_placeholder)?.toBitmap()?.apply {
            eraseColor(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f).toArgb())
        }

    Column(
        modifier = modifier.width(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .clickable { toMovieDetail(peopleCredit.id, peopleCredit.getMovieType()) },
            model = ImageRequest.Builder(LocalContext.current)
                .placeholder(BitmapDrawable(context.resources, placeholderBitmap))
                .data(onBuildImage(peopleCredit.posterPath, ImageType.POSTER))
                .crossfade(true)
                .build(),
            contentScale = ContentScale.FillWidth,
            contentDescription = ""
        )
        Text(
            modifier = Modifier
                .padding(top = 4.dp),
            text = peopleCredit.getCastName(),
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2,
            minLines = 2,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun AlsoKnownForComponent(
    modifier: Modifier,
    knownList: List<String>
) {
    Column(modifier = modifier) {
        ProfileTitleComponent(
            modifier = Modifier,
            title = stringResource(R.string.key_also_know_as),
            showMoreText = false,
            moreText = "",
            onMoreTextClick = {}
        )

        knownList.forEachIndexed { index, name ->
            Text(
                modifier = Modifier.padding(
                    top = if (index == 0) 10.dp else 6.dp,
                    start = 16.dp
                ),
                text = name,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f),
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlsoKnownForComponentPreview() {
    TMDBMovieTheme {
        AlsoKnownForComponent(
            modifier = Modifier,
            knownList = listOf(
                "Julianne Moore",
                "Джулианна Мур",
                "جوليان مور",
                "줄리안 무어",
                "ジュリアン・ムーア",
                "茱莉安·摩尔",
                "茱莉�"
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PeopleDetailTopComponentPreview() {
    TMDBMovieTheme {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            peopleActingComponent(
                modifier = Modifier.fillMaxSize(),
                isTest = false,
                toMovieDetail = { _, _ -> },
                sortedCasts = listOf(
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
                )
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun PeopleBiographyComponentPreview() {
    TMDBMovieTheme {
        PeopleBiographyComponent(
            biography = "Julie Anne Smith (born December 3, 1960), known professionally as Julianne Moore, is an American actress and author. Prolific in film since the early 1990s, she is particularly known for her portrayals of emotionally troubled women in independent films, as well as for her roles in blockbusters. She is the recipient of numerous accolades, including an Academy Award, a British Academy Film Award, two Golden Globe Awards, and two Emmy Awards.\\n\\nAfter studying theater at Boston University, Moore began her career with a series of television roles. From 1985 to 1988, she was a regular in the soap opera As the World Turns, earning a Daytime Emmy Award for her performance. Her film debut was in Tales from the Darkside: The Movie (1990), and she continued to play small roles for the next four years, including in the thriller The Hand That Rocks the Cradle (1992). Moore first received critical attention with Robert Altman's Short Cuts (1993), and successive performances in Vanya on 42nd Street (1994) and Safe (1995) continued this acclaim. Starring roles in the blockbusters Nine Months (1995) and The Lost World: Jurassic Park (1997) established her as a Hollywood leading lady.\\n\\nMoore received considerable recognition in the late 1990s and early 2000s, earning Academy Award nominations for Boogie Nights (1997), The End of the Affair (1999), Far from Heaven (2002) and The Hours (2002). In the first of these, she played a 1970s pornographic actress, while in the other three, she starred as a mid-20th century unhappy housewife. She also had success with the films The Big Lebowski (1998), Magnolia (1999), Hannibal (2001), Children of Men (2006), A Single Man (2009), The Kids Are All Right (2010), and Crazy, Stupid, Love (2011). She won a Primetime Emmy Award for her portrayal of Sarah Palin in the television film Game Change (2012). She won the Academy Award for Best Actress for her portrayal of an Alzheimer's patient in Still Alice (2014) and was named Best Actress at the Cannes Film Festival for her role in Maps to the Stars (2014). Among her highest-grossing releases are the final two films in the series The Hunger Games and the spy film Kingsman: The Golden Circle (2017).\\n\\nIn addition to her acting work, Moore has written a series of children's books about a character named \\\"Freckleface Strawberry\\\". In 2015, Time magazine named her one of the 100 most influential people in the world, and in 2020, The New York Times named her one of the greatest actors of the 21st century. She is married to director Bart Freundlich, with whom she has two children.",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PeopleDetailTopComponentPreview1() {
    TMDBMovieTheme {
        PeopleKnownForComponent(
            peopleCredits = listOf(
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
            ),
            toMovieDetail = { _, _ -> },
            onViewAllKnownFor = {}
        )
    }
}