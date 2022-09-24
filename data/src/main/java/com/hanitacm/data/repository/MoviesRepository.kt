package com.hanitacm.data.repository

import com.hanitacm.data.datasource.api.MoviesApi
import com.hanitacm.data.datasource.cache.MoviesCache
import com.hanitacm.data.repository.model.MovieDomainModel
import com.hanitacm.data.repository.model.mappers.MovieDataModelMapper
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesApi: MoviesApi,
    private val moviesCache: MoviesCache,
    private val mapper: MovieDataModelMapper
) {

    fun getPopularMovies(): Single<Result<List<MovieDomainModel>>> {
        return moviesCache.getAllMovies()
            .flatMap { movies ->
                when {
                    movies.isEmpty() -> moviesApi.getAllMovies()
                        .doOnSuccess { moviesCache.insertMovies(it) }
                    else -> Single.just(movies)
                }
            }.map { Result.success(mapper.mapToDomainModel(it)) }
    }

    fun getMovieDetail(id: Int): Single<Result<MovieDomainModel>> {
        return moviesCache.getMovieDetail(id).map { Result.success(mapper.mapToDomainModel(it)) }
    }
}






