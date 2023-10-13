package com.tmdb.movie.ui.me.dialog

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tmdb.movie.R
import com.tmdb.movie.ui.me.vm.AuthenticationUiState
import com.tmdb.movie.ui.me.vm.AuthenticationViewModel
import com.tmdb.movie.ui.theme.TMDBMovieTheme
import com.tmdb.movie.utils.copyTextToClipboard
import com.tmdb.movie.utils.launchUrl

@Composable
fun AuthenticationDialog(
    onDismiss: () -> Unit,
    onGetUserData: (String) -> Unit,
    viewModel: AuthenticationViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit, block = {
        Log.e("sqsong", "LaunchedEffect Unit: $uiState")
        if (uiState is AuthenticationUiState.Loading) {
            viewModel.refresh()
        }
    })

    LaunchedEffect(uiState) {
        Log.w("sqsong", "LaunchedEffect uiState: $uiState")
        if (uiState is AuthenticationUiState.Error) {
            Toast.makeText(context, context.getString(R.string.key_authorize_error), Toast.LENGTH_SHORT).show()
            onDismiss()
        }
    }

    AuthenticationDialog(
        uiState = uiState,
        onDismiss = onDismiss,
        onAuthorized = {
            onGetUserData(it)
            viewModel.resetAuthState()
            onDismiss()
        },
        toAuthorize = {
            val link = String.format(context.getString(R.string.key_authentication_url), it)
            launchUrl(context, link)
            viewModel.updateOnAuthorize()
        },
        onCopyAuthUrl = {
            val link = String.format(context.getString(R.string.key_authentication_url), it)
            copyTextToClipboard(context, link)
            Toast.makeText(context, context.getString(R.string.key_copy_success), Toast.LENGTH_SHORT).show()
            viewModel.updateOnAuthorize()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationDialog(
    uiState: AuthenticationUiState,
    onDismiss: () -> Unit,
    onAuthorized: (String) -> Unit = {},
    toAuthorize: (String) -> Unit = {},
    onCopyAuthUrl: (String) -> Unit = {},
) {
    val configuration = LocalConfiguration.current

    AlertDialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = false
        ),
        modifier = Modifier
            .widthIn(max = configuration.screenWidthDp.dp - 80.dp),
        onDismissRequest = { },
    ) {

        Card(shape = MaterialTheme.shapes.extraLarge) {
            Text(
                modifier = Modifier.padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 24.dp
                ),
                text = stringResource(id = R.string.key_tmdb_authenticate),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 24.dp),
                text = stringResource(R.string.key_auth_message),
                style = MaterialTheme.typography.bodyLarge.copy(
                    lineHeight = 28.sp
                ),
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (uiState is AuthenticationUiState.OnAuthorize) {
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp),
                    enabled = uiState != AuthenticationUiState.Loading,
                    onClick = { onAuthorized(uiState.authUrl) }
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 3.dp),
                        text = stringResource(id = R.string.key_authorized),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp),
                    enabled = uiState != AuthenticationUiState.Loading,
                    onClick = { toAuthorize((uiState as AuthenticationUiState.Success).authUrl) }
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 3.dp),
                        text = stringResource(id = R.string.key_to_authorize),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }

                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp),
                    enabled = uiState != AuthenticationUiState.Loading,
                    onClick = { onCopyAuthUrl((uiState as AuthenticationUiState.Success).authUrl) }
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 3.dp),
                        text = stringResource(id = R.string.key_copy_authenticate_url),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp),
                    enabled = uiState != AuthenticationUiState.Loading,
                    onClick = { onDismiss() }
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 3.dp),
                        text = stringResource(id = R.string.key_cancel),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AuthenticationDialogPreview() {
    TMDBMovieTheme {
        AuthenticationDialog(
            uiState = AuthenticationUiState.Loading,
            onDismiss = {}
        )
    }
}