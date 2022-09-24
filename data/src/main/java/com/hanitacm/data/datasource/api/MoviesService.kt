package com.hanitacm.data.datasource.api

import com.hanitacm.data.datasource.api.model.MoviesApiModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesService {

    @GET("discover/movie?sort_by=popularity.desc")
    fun getPopularMovies(@Query("api_key") apiKey: String): Single<MoviesApiModel>

}
