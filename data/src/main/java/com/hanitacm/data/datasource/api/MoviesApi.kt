package com.hanitacm.data.datasource.api

import com.hanitacm.data.datasource.api.model.mappers.MoviesDataModelMapper
import com.hanitacm.data.repository.DataSource
import com.hanitacm.data.repository.model.MovieDataModel
import io.reactivex.Single
import javax.inject.Inject

class MoviesApi @Inject constructor(
    private val moviesService: MoviesService, private val mapper: MoviesDataModelMapper
) : DataSource {

    override fun getAllMovies(): Single<List<MovieDataModel>> {
        return moviesService.getPopularMovies("4b2dda035db530ab9de5426133354f16")
            .map { mapper.mapToDataModel(it) }
    }

    override fun getMovieDetail(id: Int): Single<MovieDataModel> {
        return Single.error(UnsupportedOperationException())
    }
}