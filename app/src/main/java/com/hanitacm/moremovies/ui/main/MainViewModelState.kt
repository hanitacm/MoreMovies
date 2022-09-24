package com.hanitacm.moremovies.ui.main

import com.hanitacm.data.repository.model.MovieDomainModel

sealed class MainViewModelState {
    object Loading : MainViewModelState()
    data class MoviesLoaded(val movies: List<MovieDomainModel>) : MainViewModelState()
    data class MoviesLoadFailure(val error: Throwable?) : MainViewModelState()

}