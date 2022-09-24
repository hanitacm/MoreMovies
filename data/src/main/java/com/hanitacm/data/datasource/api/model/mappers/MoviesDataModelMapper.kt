package com.hanitacm.data.datasource.api.model.mappers


import com.hanitacm.data.datasource.api.model.MoviesApiModel
import com.hanitacm.data.repository.model.MovieDataModel
import javax.inject.Inject

class MoviesDataModelMapper @Inject constructor() {
    fun mapToDataModel(moviesApiModel: MoviesApiModel): List<MovieDataModel> {
        return moviesApiModel.results.map {
            MovieDataModel(
                id = it.id,
                title = it.title,
                overview = it.overview,
                releaseDate = it.releaseDate,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                popularity = it.popularity,
                voteAverage = it.voteAverage
            )
        }
    }
}