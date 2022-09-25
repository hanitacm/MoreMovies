package com.hanitacm.data.datasource.api

import com.hanitacm.data.datasource.api.model.mappers.MoviesDataModelMapper
import com.hanitacm.data.repository.DataSource
import com.hanitacm.data.repository.model.MovieDataModel
import javax.inject.Inject

class MoviesApi @Inject constructor(
    private val moviesService: MoviesService, private val mapper: MoviesDataModelMapper
) : DataSource {

    override suspend fun getAllMovies(): List<MovieDataModel> {
        return mapper.mapToDataModel(moviesService.getPopularMovies("4b2dda035db530ab9de5426133354f16"))
    }

    override suspend fun getMovieDetail(id: Int): MovieDataModel {
        throw  UnsupportedOperationException()
    }
}