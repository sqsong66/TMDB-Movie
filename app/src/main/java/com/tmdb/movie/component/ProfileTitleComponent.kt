package com.tmdb.movie.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun ProfileTitleComponent(
    modifier: Modifier = Modifier,
    title: String = "",
    showMoreText: Boolean = true,
    moreText: String = "",
    onMoreTextClick: () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leadingIcon != null) leadingIcon()
        Text(
            text = title,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 6.dp)
                .weight(1.0f),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
        )
        if (showMoreText) {
            Box(modifier = Modifier
                .padding(horizontal = 8.dp)
                .clip(MaterialTheme.shapes.extraSmall)
                .clickable { onMoreTextClick() }) {
                Text(
                    text = moreText,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileTitleComponentPreview() {
    TMDBMovieTheme {
        ProfileTitleComponent(title = "Videos", moreText = "View all", onMoreTextClick = {})
    }
}