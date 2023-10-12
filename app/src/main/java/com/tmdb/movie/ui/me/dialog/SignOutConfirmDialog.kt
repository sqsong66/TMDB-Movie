package com.tmdb.movie.ui.me.dialog

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.tmdb.movie.R
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun SignOutConfirmDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false),
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                onClick = { onConfirm() }) {
                Text(
                    text = stringResource(id = R.string.key_confirm),
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
        title = {
            Text(
                text = stringResource(id = R.string.key_sign_out_confirm),
                style = MaterialTheme.typography.titleMedium
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.key_sign_out_confirm_content),
                style = MaterialTheme.typography.bodyMedium
            )
        },
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewSignOutConfirmDialog() {
    TMDBMovieTheme {
        SignOutConfirmDialog(
            onDismiss = {},
            onConfirm = {},
        )
    }
}