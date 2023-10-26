package com.tmdb.movie.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tmdb.movie.R
import com.tmdb.movie.component.myiconpack.emptyDataVector
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun EmptyPage(
    modifier: Modifier = Modifier,
    emptyMsg: String? = null,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.width(160.dp),
                imageVector = emptyDataVector(
                    primaryColor = MaterialTheme.colorScheme.primary,
                    backgroundColor = MaterialTheme.colorScheme.background
                ),
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = emptyMsg ?: stringResource(R.string.key_empty_data),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyPagePreview() {
    TMDBMovieTheme {
        EmptyPage()
    }
}