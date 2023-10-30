package com.tmdb.movie.ui.detail.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.PopupProperties
import com.tmdb.movie.R
import com.tmdb.movie.data.AccountState

@Composable
fun MovieDetailMoreAction(
    modifier: Modifier,
    accountState: AccountState?,
    onWatchlist: () -> Unit,
    onAddList: () -> Unit,
    onShare: () -> Unit,
) {
    var showDropdownMenu by remember { mutableStateOf(false) }
    Box(modifier = modifier) {
        IconButton(
            onClick = {
                showDropdownMenu = true
            }) {
            Icon(
                painter = painterResource(id = R.drawable.more_horiz_24),
                contentDescription = "",
            )
        }
        DropdownMenu(
            expanded = showDropdownMenu,
            modifier = Modifier.align(Alignment.TopEnd),
            properties = PopupProperties(dismissOnClickOutside = true),
            onDismissRequest = { showDropdownMenu = false },
        ) {
            DropdownMenuItem(
                text = { Text(text = stringResource(/*if (accountState?.watchlist == true) R.string.key_remove_from_watchlist else*/ R.string.key_add_to_watchlist)) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = if (accountState?.watchlist == true) R.drawable.baseline_bookmark_added_24 else R.drawable.outline_bookmark_add_24),
                        contentDescription = "",
                        tint = if (accountState?.watchlist == true) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                onClick = onWatchlist,
            )
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.key_add_to_list)) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_format_list_24),
                        contentDescription = "",
                    )
                },
                onClick = {
                    onAddList()
                    showDropdownMenu = false
                },
            )
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.key_share)) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_share_24),
                        contentDescription = "",
                    )
                },
                onClick = {
                    onShare()
                    showDropdownMenu = false
                })
        }
    }
}