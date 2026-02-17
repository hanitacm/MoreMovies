package com.hanitacm.data.datasource.api.model.mappers

import com.hanitacm.data.datasource.api.model.MoviesApiModel
import com.hanitacm.data.repository.model.MovieDataModel

fun MoviesApiModel.asDataModel(): List<MovieDataModel> =
    results.map {
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
            voteAverage = it.voteAverage,
        )
    }
