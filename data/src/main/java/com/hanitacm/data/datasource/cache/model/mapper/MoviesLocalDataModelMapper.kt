package com.hanitacm.data.datasource.cache.model.mapper

import com.hanitacm.data.datasource.db.Movie
import com.hanitacm.data.repository.model.MovieDataModel
import javax.inject.Inject

class MoviesLocalDataModelMapper @Inject constructor() {
    fun mapToDataModel(movies: List<Movie>): List<MovieDataModel> {
        return movies.map {
            mapToDataModel(it)
        }
    }

    fun mapToDataModel(it: Movie): MovieDataModel {
        return MovieDataModel(
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


    fun mapToLocalDataModel(movies: List<MovieDataModel>): List<Movie> {
        return movies.map {
            Movie(
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