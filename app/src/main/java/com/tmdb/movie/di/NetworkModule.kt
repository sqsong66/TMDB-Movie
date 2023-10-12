package com.tmdb.movie.di

import android.content.Context
import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.gson.Gson
import com.tmdb.movie.network.ApiService
import com.tmdb.movie.network.TMDBNetworkInterceptor
import com.tmdb.movie.utils.BASE_TMDB_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideCookieJar(@ApplicationContext context: Context): ClearableCookieJar {
        return PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))
    }

    @Singleton
    @Provides
    fun provideOkhttpClient(@ApplicationContext context: Context, cookieJar: ClearableCookieJar): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .addNetworkInterceptor(TMDBNetworkInterceptor(context, Gson()))
            .addNetworkInterceptor(logInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_TMDB_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}