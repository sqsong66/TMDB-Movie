package com.tmdb.movie.ext

import android.app.Activity
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory

@Composable
inline fun <reified VM : ViewModel> hiltViewModelExt(
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
    noinline extrasProducer: (Bundle?) -> Bundle?
): VM {
    val factory = createHiltViewModelFactory(viewModelStoreOwner, extrasProducer)
    return viewModel(viewModelStoreOwner, factory = factory)
}

@Composable
fun createHiltViewModelFactory(
    viewModelStoreOwner: ViewModelStoreOwner,
    extrasProducer: (Bundle?) -> Bundle?
): ViewModelProvider.Factory? = when (viewModelStoreOwner) {
    is NavBackStackEntry -> {
        val navBackStackEntry = viewModelStoreOwner as NavBackStackEntry
        val activity = LocalContext.current.let {
            var ctx = it
            while (ctx is ContextWrapper) {
                if (ctx is Activity) {
                    return@let ctx
                }
                ctx = ctx.baseContext
            }
            throw IllegalStateException(
                "Expected an activity context for creating a HiltViewModelFactory for a " +
                        "NavBackStackEntry but instead found: $ctx"
            )
        }
        HiltViewModelFactory.createInternal(
            activity,
            navBackStackEntry,
            extrasProducer(navBackStackEntry.arguments),
            navBackStackEntry.defaultViewModelProviderFactory,
        )
    }
    is ComponentActivity -> {
        HiltViewModelFactory.createInternal(
            viewModelStoreOwner,
            viewModelStoreOwner,
            extrasProducer(viewModelStoreOwner.intent?.extras),
            SavedStateViewModelFactory(
                viewModelStoreOwner.application,
                viewModelStoreOwner,
                extrasProducer(viewModelStoreOwner.intent?.extras)
            )
        )
    }
    else -> {
        // Use the default factory provided by the ViewModelStoreOwner
        // and assume it is an @AndroidEntryPoint annotated fragment
        null
    }
}
