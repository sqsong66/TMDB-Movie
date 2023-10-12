package com.tmdb.movie.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tmdb.movie.R
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun LoadingPage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                modifier = Modifier,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(id = R.string.key_in_loading),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPagePreview() {
    TMDBMovieTheme {
        LoadingPage()
    }
}