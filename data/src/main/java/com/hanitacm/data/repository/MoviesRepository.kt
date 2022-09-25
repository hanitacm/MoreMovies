package com.hanitacm.data.repository

import com.hanitacm.data.datasource.api.MoviesApi
import com.hanitacm.data.datasource.cache.MoviesCache
import com.hanitacm.data.repository.model.MovieDomainModel
import com.hanitacm.data.repository.model.mappers.MovieDataModelMapper
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesApi: MoviesApi,
    private val moviesCache: MoviesCache,
    private val mapper: MovieDataModelMapper
) {

    suspend fun getPopularMovies(): List<MovieDomainModel> {
        var movies = moviesCache.getAllMovies()

        if (movies.isEmpty()) {
            movies = moviesApi.getAllMovies()
            moviesCache.insertMovies(movies)
        }
        return mapper.mapToDomainModel(movies)
    }

    suspend fun getMovieDetail(id: Int): MovieDomainModel {
        return mapper.mapToDomainModel(moviesCache.getMovieDetail(id))
    }
}






