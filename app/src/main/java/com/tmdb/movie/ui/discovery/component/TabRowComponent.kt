package com.tmdb.movie.ui.discovery.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tmdb.movie.ext.customTabIndicatorOffset
import com.tmdb.movie.ui.theme.NoRippleTheme
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun WrapIndicatorTabRow() {
    val density = LocalDensity.current
    val tabItems = listOf("Movies", "TV Shows")
    var tabIndex by rememberSaveable { mutableIntStateOf(0) }
    var tabRowWidth by remember { mutableStateOf(64.dp) }
    val tabsWidth = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabItems.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }
    TabRow(
        modifier = Modifier
            .statusBarsPadding(),
        selectedTabIndex = tabIndex,
        containerColor = Color.Transparent,
        indicator = { tabPositions ->
            if (tabIndex < tabPositions.size) {
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .customTabIndicatorOffset(tabPositions[tabIndex], tabsWidth[tabIndex])
                        .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp, bottomStart = 4.dp, bottomEnd = 4.dp)),
                    height = 4.dp
                )
            }
        },
        divider = {}
    ) {
        tabItems.forEachIndexed { index, title ->
            Tab(modifier = Modifier,
                selected = index == tabIndex, onClick = {
                    tabIndex = index
                }) {
                Text(
                    modifier = Modifier
                        .padding(vertical = 16.dp),
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = if (index == tabIndex) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        fontWeight = if (index == tabIndex) FontWeight.Bold else FontWeight.Medium,
                        fontSize = if (index == tabIndex) 32.sp else 24.sp
                    ),
                    onTextLayout = { textLayoutResult ->
                        tabsWidth[index] = with(density) { textLayoutResult.size.width.toDp() }
                        tabRowWidth += with(density) { textLayoutResult.size.width.toDp() }
                    },
                )
            }
        }
    }
}

@Composable
fun DiscoveryTabRow(
    modifier: Modifier = Modifier,
    tabLists: List<String>,
    selectedTabIndex: Int = 0,
    onTabSelected: (Int) -> Unit = {},
) {

    ScrollableTabRow(
        modifier = modifier
            .statusBarsPadding(),
        edgePadding = 0.dp,
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        indicator = { tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .customTabIndicatorOffset(tabPositions[selectedTabIndex], 50.dp)
                        .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp/*, bottomStart = 4.dp, bottomEnd = 4.dp*/)),
                    height = 4.dp
                )
            }
        },
        divider = {}
    ) {
        tabLists.forEachIndexed { index, title ->
            CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
                Tab(modifier = Modifier,
                    selected = index == selectedTabIndex,
                    onClick = { onTabSelected(index) }
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 12.dp, start = 16.dp, end = 16.dp),
                        text = title,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = if (index == selectedTabIndex) 1f else 0.8f)
                        ),
                        fontWeight = if (index == selectedTabIndex) FontWeight.Bold else FontWeight.Medium,
                        fontSize = if (index == selectedTabIndex) 24.sp else 18.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WrapIndicatorTabRowPreview() {
    TMDBMovieTheme {
        DiscoveryTabRow(tabLists = listOf("Movies", "TV Shows"))
    }
}