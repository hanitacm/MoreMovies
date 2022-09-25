package com.hanitacm.data.repository

import com.hanitacm.data.datasource.api.MoviesApi
import com.hanitacm.data.datasource.cache.MoviesCache
import com.hanitacm.data.repository.model.MovieDomainModel
import com.hanitacm.data.repository.model.mappers.asDomainModel

import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesApi: MoviesApi,
    private val moviesCache: MoviesCache,
) {

    suspend fun getPopularMovies(): List<MovieDomainModel> {
        var movies = moviesCache.getAllMovies()

        if (movies.isEmpty()) {
            movies = moviesApi.getAllMovies()
            moviesCache.insertMovies(movies)
        }
        return movies.asDomainModel()
    }

    suspend fun getMovieDetail(id: Int) = moviesCache.getMovieDetail(id).asDomainModel()



}






