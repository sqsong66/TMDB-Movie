package com.tmdb.movie.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tmdb.movie.R
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun LoadingFooter(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(16.dp)
                .clip(RoundedCornerShape(10.dp)),
            strokeWidth = 2.dp,
            strokeCap = StrokeCap.Round,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = stringResource(id = R.string.key_loading),
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}

@Composable
fun LoadingError(modifier: Modifier = Modifier, onRetry: () -> Unit = {}) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.key_load_error),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.error
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.width(6.dp))
        Button(
            content = {
                Text(
                    text = stringResource(id = R.string.key_retry),
                    style = MaterialTheme.typography.bodySmall
                        .copy(color = MaterialTheme.colorScheme.onTertiary),
                    textAlign = TextAlign.End
                )
            },
            onClick = { onRetry() },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
            shape = RoundedCornerShape(4.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
            modifier = Modifier.defaultMinSize(minWidth = 1.dp, minHeight = 18.dp),
            elevation = ButtonDefaults.buttonElevation()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingFooterPreview() {
    LoadingFooter()
}

@Preview(showBackground = true)
@Composable
fun LoadingErrorPreview() {
    TMDBMovieTheme {
        LoadingError()
    }
}