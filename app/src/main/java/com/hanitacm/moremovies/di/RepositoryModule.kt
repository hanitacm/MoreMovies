package com.hanitacm.moremovies.di

import android.content.Context
import com.hanitacm.data.datasource.api.MoviesApi
import com.hanitacm.data.datasource.cache.MoviesCache
import com.hanitacm.data.datasource.db.MoviesDatabase
import com.hanitacm.data.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(
        moviesApi: MoviesApi,
        moviesCache: MoviesCache
    ): MoviesRepository =
        MoviesRepository(moviesApi, moviesCache)

    @Singleton
    @Provides
    fun provideMoviesDb(@ApplicationContext appContext: Context): MoviesDatabase =
        MoviesDatabase.getDb(appContext)

}

