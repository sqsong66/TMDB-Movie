package com.tmdb.movie.ui.main.component

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tmdb.movie.R
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun LoginTipsDialog(
    onDismiss: () -> Unit,
    toLogin: () -> Unit,
) {

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(id = R.string.key_login_tips),
                style = MaterialTheme.typography.titleMedium
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.key_login_tips_content),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        confirmButton = {
            TextButton(
                onClick = { toLogin() }) {
                Text(
                    text = stringResource(id = R.string.key_to_login),
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(
                    text = stringResource(id = R.string.key_cancel),
                )
            }
        },
    )

}

@Preview(locale = "zh")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoginTipsDialogPreview() {
    TMDBMovieTheme {
        LoginTipsDialog(
            onDismiss = {},
            toLogin = {}
        )
    }
}