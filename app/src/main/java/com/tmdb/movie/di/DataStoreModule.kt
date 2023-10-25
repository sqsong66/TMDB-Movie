package com.tmdb.movie.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.room.Room
import com.tmdb.movie.data.TMDBConfig
import com.tmdb.movie.datastore.ThemeConfigSerializer
import com.tmdb.movie.db.TMDBDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    /*@Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return context.preferencesDataStore
    }*/

    @Provides
    @Singleton
    fun provideThemeDataStore(
        @ApplicationContext context: Context,
        serializer: ThemeConfigSerializer
    ): DataStore<TMDBConfig> {
        return DataStoreFactory.create(
            serializer = serializer,
            produceFile = { context.dataStoreFile("config_data.json") })
    }

    @Provides
    @Singleton
    fun provideTMDBDatabase(@ApplicationContext context: Context): TMDBDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = TMDBDatabase::class.java,
            name = TMDBDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideSearchHistoryDao(database: TMDBDatabase) =
        database.searchHistoryDao()

    @Provides
    fun providePopularMovieDao(database: TMDBDatabase) =
        database.popularMovieDao()
}