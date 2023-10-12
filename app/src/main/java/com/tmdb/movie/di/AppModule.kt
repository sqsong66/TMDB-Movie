package com.tmdb.movie.di

import com.tmdb.movie.repository.IMovieRepository
import com.tmdb.movie.repository.ISearchRepository
import com.tmdb.movie.repository.RecentSearchRepository
import com.tmdb.movie.repository.TMDBMovieRepository
import com.tmdb.movie.utils.monitor.ConnectivityManagerNetworkMonitor
import com.tmdb.movie.utils.monitor.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    fun bindsMovieRepository(
        repository: TMDBMovieRepository
    ): IMovieRepository

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor

    @Binds
    fun bindsSearchRepository(
        repository: RecentSearchRepository
    ): ISearchRepository
}