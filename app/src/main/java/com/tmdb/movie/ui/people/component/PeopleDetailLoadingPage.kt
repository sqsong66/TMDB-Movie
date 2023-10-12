package com.tmdb.movie.ui.people.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.placeholder.PlaceholderDefaults
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmerHighlightColor
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.tmdb.movie.R
import com.tmdb.movie.component.AutoResizeText
import com.tmdb.movie.component.FontSizeRange
import com.tmdb.movie.ext.pxToDp
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun PeopleDetailLoadingPager(
    topBarHeight: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 6.dp)
    ) {
        Spacer(modifier = Modifier.height(topBarHeight.pxToDp()))
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .width(120.dp)
                    .clip(MaterialTheme.shapes.small)
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
                painter = painterResource(id = R.drawable.image_placeholder),
                contentScale = ContentScale.FillWidth,
                contentDescription = ""
            )
            Column(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 8.dp
                )
            ) {
                AutoResizeText(
                    modifier = Modifier
                        .width(100.dp)
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
                    text = "xxxxxxxx",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    fontSizeRange = FontSizeRange(16.sp, 22.sp)
                )
                PeopleProfileItem(modifier = Modifier.padding(top = 12.dp))
                PeopleProfileItem(modifier = Modifier.padding(top = 8.dp))
                PeopleProfileItem(modifier = Modifier.padding(top = 8.dp))
                PeopleProfileItem(modifier = Modifier.padding(top = 8.dp))
                PeopleProfileItem(modifier = Modifier.padding(top = 8.dp))
            }
        }

        Text(
            text = "",
            modifier = Modifier
                .width(120.dp)
                .padding(start = 16.dp, end = 16.dp, top = 30.dp)
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

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 10.dp)
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
            text = "xxxx",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
            ),
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 4.dp)
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
            text = "xxxx",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
            ),
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 2.dp)
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
            text = "xxxx",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
            ),
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 4.dp)
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
            text = "xxxx",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
            ),
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 4.dp)
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
            text = "xxxx",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
            ),
        )

        Text(
            modifier = Modifier
                .width(280.dp)
                .padding(start = 16.dp, end = 16.dp, top = 4.dp)
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
            text = "xxxx",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
            ),
        )

        Text(
            text = "",
            modifier = Modifier
                .width(120.dp)
                .padding(start = 16.dp, end = 16.dp, top = 30.dp)
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

        LazyRow(modifier = Modifier.padding(top = 20.dp)) {
            items(5) {
                Image(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .width(120.dp)
                        .clip(MaterialTheme.shapes.small)
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
                    painter = painterResource(id = R.drawable.image_placeholder),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = ""
                )
            }
        }

        Text(
            text = "",
            modifier = Modifier
                .width(120.dp)
                .padding(start = 16.dp, end = 16.dp, top = 30.dp)
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

        Text(
            modifier = Modifier
                .width(180.dp)
                .padding(start = 16.dp, end = 16.dp, top = 10.dp)
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
            text = "xxxx",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
            ),
        )
        Text(
            modifier = Modifier
                .width(180.dp)
                .padding(start = 16.dp, end = 16.dp, top = 6.dp)
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
            text = "xxxx",
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            modifier = Modifier
                .width(180.dp)
                .padding(start = 16.dp, end = 16.dp, top = 6.dp)
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
            text = "xxxx",
            style = MaterialTheme.typography.bodyMedium,
        )

    }
}

@Composable
fun PeopleProfileItem(modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
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
            text = "xxxx",
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            modifier = Modifier
                .width(160.dp)
                .padding(start = 6.dp)
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
            text = "xxxxxxxx",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PeopleDetailLoadingPagerPreview() {
    TMDBMovieTheme {
        PeopleDetailLoadingPager(
            topBarHeight = 0,
        )
    }
}