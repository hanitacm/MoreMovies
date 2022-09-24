package com.hanitacm.data.repository.model.mappers

import com.hanitacm.data.repository.model.MovieDataModel
import com.hanitacm.data.repository.model.MovieDomainModel
import javax.inject.Inject

class MovieDataModelMapper @Inject constructor() {

    fun mapToDomainModel(movies: List<MovieDataModel>): List<MovieDomainModel> {
        return movies.map {
            mapToDomainModel(it)
        }
    }

    fun mapToDomainModel(movie: MovieDataModel): MovieDomainModel {
        return with(movie) {
            MovieDomainModel(
                id = id,
                title = title,
                overview = overview,
                releaseDate = releaseDate,
                posterPath = posterPath,
                backdropPath = backdropPath,
                originalLanguage = originalLanguage,
                originalTitle = originalTitle,
                popularity = popularity,
                voteAverage = voteAverage
            )
        }
    }
}



