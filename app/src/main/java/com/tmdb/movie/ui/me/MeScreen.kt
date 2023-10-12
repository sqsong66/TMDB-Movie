package com.tmdb.movie.ui.me

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.tmdb.movie.R
import com.tmdb.movie.data.TMDBConfig
import com.tmdb.movie.data.UserData
import com.tmdb.movie.ui.me.component.MeAvatarBgComponent
import com.tmdb.movie.ui.me.dialog.AuthenticationDialog
import com.tmdb.movie.ui.me.dialog.SignOutConfirmDialog
import com.tmdb.movie.ui.me.dialog.ThemeDialog
import com.tmdb.movie.ui.me.vm.MeViewModel
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun MeRoute(
    viewModel: MeViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    var avatarUrl by rememberSaveable { mutableStateOf("") }
    val configStream by viewModel.configStream.collectAsStateWithLifecycle()
    val signOutState by viewModel.signOutState.collectAsStateWithLifecycle()
    var isShowThemeDialog by rememberSaveable { mutableStateOf(false) }
    var isShowSignOutDialog by rememberSaveable { mutableStateOf(false) }
    val authorizeState by viewModel.authorizeState.collectAsStateWithLifecycle()
    var isShowAuthenticationDialog by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(authorizeState) {
        if (authorizeState.isNotEmpty()) {
            if (authorizeState.startsWith("success")) {
                Toast.makeText(context, context.getString(R.string.key_auth_success), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, context.getString(R.string.key_auth_error), Toast.LENGTH_SHORT).show()
            }
            viewModel.updateRequestToken("")
        }
    }

    LaunchedEffect(signOutState) {
        if (signOutState.isNotEmpty()) {
            if (signOutState.startsWith("success")) {
                Toast.makeText(context, context.getString(R.string.key_sign_out_success), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, context.getString(R.string.key_sign_out_error), Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.signOut("")
    }

    if (isShowThemeDialog) {
        ThemeDialog(onDismiss = {
            isShowThemeDialog = false
        })
    }

    if (isShowAuthenticationDialog) {
        AuthenticationDialog(onGetUserData = viewModel::updateRequestToken, onDismiss = {
            isShowAuthenticationDialog = false
        })
    }

    if (isShowSignOutDialog) {
        SignOutConfirmDialog(
            onDismiss = { isShowSignOutDialog = false },
            onConfirm = {
                isShowSignOutDialog = false
                configStream.userData?.sessionId?.let { viewModel.signOut(it) }
            },
        )
    }

    MeScreen(config = configStream,
        onAvatarClick = {
            if (configStream.userData != null) return@MeScreen
            isShowAuthenticationDialog = true
        }, onChangeTheme = {
            isShowThemeDialog = true
        }, onSignOut = {
            isShowSignOutDialog = true
        }, onBuildAvatar = {
            val url = configStream.buildAvatarUrl(context)
            avatarUrl = url
            url
        },
        avatarUrl = avatarUrl
    )
}

@Composable
fun MeScreen(
    config: TMDBConfig,
    onAvatarClick: () -> Unit,
    onChangeTheme: () -> Unit,
    onSignOut: () -> Unit,
    avatarUrl: String = "",
    onBuildAvatar: () -> String = { "" },
) {
    val name = config.userData?.username
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .placeholder(R.drawable.gravatar)
            .error(R.drawable.gravatar).data(onBuildAvatar())
            .crossfade(true)
            .build()
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (avatarUrl.isNotEmpty()) MeAvatarBgComponent(imageUrl = avatarUrl)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            Image(
                modifier = Modifier
                    .padding(top = 56.dp)
                    .border(width = 2.dp, color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                    .size(120.dp)
                    .padding(6.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
                    .clickable { onAvatarClick() },
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = if (config.isEmptyAvatar()) ColorFilter.tint(MaterialTheme.colorScheme.primary) else null,
            )

            Text(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 56.dp)
                    .align(Alignment.CenterHorizontally),
                text = if (name.isNullOrEmpty()) stringResource(id = R.string.key_click_authorize) else name,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = if (name.isNullOrEmpty()) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary,
                    fontWeight = if (name.isNullOrEmpty()) FontWeight.Normal else FontWeight.Bold
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(8.dp)
                    )
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onChangeTheme() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                    painter = painterResource(id = R.drawable.baseline_palette_24),
                    contentDescription = "Theme"
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(id = R.string.key_theme),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            AnimatedVisibility(visible = config.userData != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(8.dp)
                        )
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onSignOut() },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                        painter = painterResource(id = R.drawable.baseline_logout_24),
                        contentDescription = "Theme"
                    )
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = stringResource(id = R.string.key_sign_out),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }

            /*Image(
                modifier = Modifier
                    .width(200.dp)
                    .padding(top = 60.dp)
                    .align(Alignment.CenterHorizontally), imageVector = buildErrorVector(
                    primaryColor = MaterialTheme.colorScheme.primary, backgroundColor = MaterialTheme.colorScheme.background
                ), contentDescription = "", contentScale = ContentScale.FillWidth
            )*/
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MeScreenPreview() {
    TMDBMovieTheme {
        MeScreen(
            config = TMDBConfig(
                userData = UserData()
            ),
            onAvatarClick = {},
            onChangeTheme = {},
            onSignOut = {},
        )
    }
}