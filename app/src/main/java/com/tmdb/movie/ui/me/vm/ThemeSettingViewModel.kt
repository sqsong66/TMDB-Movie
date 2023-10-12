package com.tmdb.movie.ui.me.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.movie.repository.IMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeSettingViewModel @Inject constructor(
    private val movieRepository: IMovieRepository
) : ViewModel() {

    val themeConfig = movieRepository.configStream
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)


    fun updateDarkThemeType(themeType: Int) {
        viewModelScope.launch {
            movieRepository.updateDarkThemeType(themeType)
        }
    }

    fun updateDynamicTheme(useDynamicTheme: Boolean) {
        viewModelScope.launch {
            movieRepository.updateDynamicTheme(useDynamicTheme)
        }
    }

}