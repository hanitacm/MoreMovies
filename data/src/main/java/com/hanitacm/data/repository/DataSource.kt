package com.hanitacm.data.repository

import com.hanitacm.data.repository.model.MovieDataModel

interface DataSource {
    suspend fun getAllMovies(): List<MovieDataModel>

    suspend fun getMovieDetail(id: Int): MovieDataModel
}
