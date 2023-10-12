package com.tmdb.movie.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
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
import com.tmdb.movie.component.myiconpack.buildErrorVector
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun ErrorPage(
    modifier: Modifier = Modifier,
    errorMsg: String? = null,
    showRetryBtn: Boolean = true,
    onRetry: () -> Unit = {}
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.width(260.dp),
                imageVector = buildErrorVector(
                    primaryColor = MaterialTheme.colorScheme.primary,
                    backgroundColor = MaterialTheme.colorScheme.background
                ), contentDescription = "",
                contentScale = ContentScale.FillWidth
            )

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = errorMsg ?: stringResource(R.string.key_something_wrong),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
            )

            if (showRetryBtn) {
                Button(
                    modifier = Modifier.padding(top = 16.dp),
                    onClick = onRetry
                ) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.key_retry),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorPagePreview() {
    TMDBMovieTheme {
        ErrorPage()
    }
}