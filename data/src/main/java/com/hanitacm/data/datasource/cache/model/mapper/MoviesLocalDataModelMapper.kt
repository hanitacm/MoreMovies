package com.hanitacm.data.datasource.cache.model.mapper

import com.hanitacm.data.datasource.db.Movie
import com.hanitacm.data.repository.model.MovieDataModel

fun List<Movie>.asDataModel() = map { it.asDataModel() }

fun Movie.asDataModel() =
    MovieDataModel(
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        posterPath = posterPath,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        popularity = popularity,
        voteAverage = voteAverage,
    )

fun List<MovieDataModel>.asLocalDataModel(): List<Movie> =
    map {
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
            voteAverage = it.voteAverage,
        )
    }
