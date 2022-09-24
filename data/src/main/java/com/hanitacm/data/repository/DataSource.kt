package com.hanitacm.data.repository

import com.hanitacm.data.repository.model.MovieDataModel
import io.reactivex.Single

interface DataSource {
    fun getAllMovies(): Single<List<MovieDataModel>>
    fun getMovieDetail(id: Int): Single<MovieDataModel>
}