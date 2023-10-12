package com.tmdb.movie.ui.me.dialog

import android.content.res.Configuration
import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tmdb.movie.R
import com.tmdb.movie.data.DarkThemeType
import com.tmdb.movie.ui.me.vm.ThemeSettingViewModel
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun ThemeDialog(
    onDismiss: () -> Unit,
    viewModel: ThemeSettingViewModel = hiltViewModel()
) {

    val themeConfig by viewModel.themeConfig.collectAsStateWithLifecycle()

    ThemeDialog(
        darkThemeType = themeConfig?.darkTheme ?: DarkThemeType.FOLLOW_SYSTEM,
        useDynamicTheme = themeConfig?.useDynamicTheme ?: false,
        onDarkThemeTypeChanged = viewModel::updateDarkThemeType,
        onDynamicThemeChanged = viewModel::updateDynamicTheme,
        onDismiss = onDismiss,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeDialog(
    @DarkThemeType darkThemeType: Int,
    useDynamicTheme: Boolean,
    onDarkThemeTypeChanged: (Int) -> Unit,
    onDynamicThemeChanged: (Boolean) -> Unit,
    onDismiss: () -> Unit,
) {
    val configuration = LocalConfiguration.current
    val isDynamicTheme = useDynamicTheme && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    AlertDialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = false
        ),
        modifier = Modifier
            .widthIn(max = configuration.screenWidthDp.dp - 80.dp),
        onDismissRequest = { onDismiss() },
    ) {
        Card(shape = MaterialTheme.shapes.extraLarge) {
            Text(
                modifier = Modifier.padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 24.dp
                ),
                text = stringResource(id = R.string.key_theme_setting),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            )

            Divider(
                Modifier.padding(start = 24.dp, top = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f)
            )

            Text(
                modifier = Modifier.padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 24.dp,
                ),
                text = stringResource(id = R.string.key_use_dark_theme),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            )

            Column(
                Modifier
                    .selectableGroup()
                    .padding(top = 16.dp)
            ) {
                ThemeSettingsChooserRow(
                    text = stringResource(R.string.key_dark_mode_system),
                    selected = darkThemeType == DarkThemeType.FOLLOW_SYSTEM,
                    onClick = { onDarkThemeTypeChanged(DarkThemeType.FOLLOW_SYSTEM) },
                )
                ThemeSettingsChooserRow(
                    text = stringResource(R.string.key_dark_mode_light),
                    selected = darkThemeType == DarkThemeType.LIGHT,
                    onClick = { onDarkThemeTypeChanged(DarkThemeType.LIGHT) },
                )
                ThemeSettingsChooserRow(
                    text = stringResource(R.string.key_dark_mode_dark),
                    selected = darkThemeType == DarkThemeType.DARK,
                    onClick = { onDarkThemeTypeChanged(DarkThemeType.DARK) },
                )
            }

            Row(
                modifier = Modifier.padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 12.dp,
                ), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    text = stringResource(id = R.string.key_use_dynamic_theme),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                )
                Switch(
                    modifier = Modifier,
                    checked = isDynamicTheme,
                    enabled = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
                    onCheckedChange = onDynamicThemeChanged,
                    thumbContent = {
                        if (isDynamicTheme) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(id = R.drawable.baseline_check_24),
                                tint = MaterialTheme.colorScheme.primary,
                                contentDescription = ""
                            )
                        }
                    })
            }

            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                Button(
                    modifier = Modifier.padding(end = 24.dp, start = 16.dp),
                    onClick = { onDismiss() },
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
                ) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.key_confirm),
                        style = MaterialTheme.typography.labelLarge
                            .copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            ),
                    )
                }
            }
        }
    }
}

@Composable
fun ThemeSettingsChooserRow(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            modifier = Modifier.padding(
                start = 12.dp,
                top = 12.dp,
                bottom = 12.dp
            ),
            selected = selected,
            onClick = null,
        )
        Spacer(Modifier.width(8.dp))
        Text(text)
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ThemeDialogPreview() {
    TMDBMovieTheme {
        ThemeDialog(
            darkThemeType = DarkThemeType.FOLLOW_SYSTEM,
            useDynamicTheme = false,
            onDarkThemeTypeChanged = {},
            onDynamicThemeChanged = {},
            onDismiss = {})
    }
}
