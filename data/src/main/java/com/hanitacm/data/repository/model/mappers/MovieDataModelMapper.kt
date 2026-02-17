package com.hanitacm.data.repository.model.mappers

import com.hanitacm.data.repository.model.MovieDataModel
import com.hanitacm.data.repository.model.MovieDomainModel

fun List<MovieDataModel>.asDomainModel() = this.map { it.asDomainModel() }

fun MovieDataModel.asDomainModel() =
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
        voteAverage = voteAverage,
    )
