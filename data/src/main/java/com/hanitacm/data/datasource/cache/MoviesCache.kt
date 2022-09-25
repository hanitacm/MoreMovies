package com.hanitacm.data.datasource.cache

import com.hanitacm.data.datasource.cache.model.mapper.MoviesLocalDataModelMapper
import com.hanitacm.data.datasource.db.MoviesDatabase
import com.hanitacm.data.repository.DataSource
import com.hanitacm.data.repository.model.MovieDataModel
import javax.inject.Inject

class MoviesCache @Inject constructor(
    private val moviesDatabase: MoviesDatabase,
    private val mapperLocal: MoviesLocalDataModelMapper
) : DataSource {

    override suspend fun getAllMovies(): List<MovieDataModel> {
        return moviesDatabase.movieDao.getAll().map {
            mapperLocal.mapToDataModel(it)
        }
    }

    override suspend fun getMovieDetail(id: Int): MovieDataModel {
        return mapperLocal.mapToDataModel(moviesDatabase.movieDao.getById(id))
    }

    suspend fun insertMovies(movies: List<MovieDataModel>) {
        return moviesDatabase.movieDao.insertAll(mapperLocal.mapToLocalDataModel(movies))

    }

}
