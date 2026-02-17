package com.hanitacm.data.datasource.cache

import com.hanitacm.data.datasource.cache.model.mapper.asDataModel
import com.hanitacm.data.datasource.cache.model.mapper.asLocalDataModel
import com.hanitacm.data.datasource.db.MoviesDatabase
import com.hanitacm.data.repository.DataSource
import com.hanitacm.data.repository.model.MovieDataModel
import javax.inject.Inject

class MoviesCache
    @Inject
    constructor(
        private val moviesDatabase: MoviesDatabase,
    ) : DataSource {
        override suspend fun getAllMovies() = moviesDatabase.movieDao.getAll().map { it.asDataModel() }

        override suspend fun getMovieDetail(id: Int) = moviesDatabase.movieDao.getById(id).asDataModel()

        suspend fun insertMovies(movies: List<MovieDataModel>) =
            moviesDatabase.movieDao.insertAll(movies.asLocalDataModel())
    }
