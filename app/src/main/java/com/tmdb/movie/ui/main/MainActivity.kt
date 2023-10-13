package com.tmdb.movie.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.tmdb.movie.data.DarkThemeType
import com.tmdb.movie.ui.main.vm.MainActivityUiState
import com.tmdb.movie.ui.main.vm.MainActivityUiState.Loading
import com.tmdb.movie.ui.main.vm.MainActivityUiState.Success
import com.tmdb.movie.ui.main.vm.MainViewModel
import com.tmdb.movie.ui.app.TMDBApp
import com.tmdb.movie.ui.theme.TMDBMovieTheme
import com.tmdb.movie.utils.monitor.NetworkMonitor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var uiState: MainActivityUiState by mutableStateOf(Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    .onEach {
                        uiState = it
                    }
                    .collect()
            }
        }

        splashScreen.setKeepOnScreenCondition {
            when (uiState) {
                Loading -> true
                is Success -> false
            }
        }

        enableEdgeToEdge()
        setContent {
            TMDBMovieTheme(
                darkTheme = shouldUseDarkTheme(uiState = uiState),
                dynamicColor = shouldUseDynamicTheming(uiState = uiState)
            ) {
                TMDBApp(networkMonitor)
            }
        }
    }
}

@Composable
private fun shouldUseDarkTheme(
    uiState: MainActivityUiState,
): Boolean = when (uiState) {
    Loading -> isSystemInDarkTheme()
    is Success -> when (uiState.config.darkTheme) {
        DarkThemeType.LIGHT -> false
        DarkThemeType.DARK -> true
        else -> isSystemInDarkTheme()
    }
}

@Composable
private fun shouldUseDynamicTheming(
    uiState: MainActivityUiState,
): Boolean = when (uiState) {
    Loading -> true
    is Success -> uiState.config.useDynamicTheme
}
