package com.hanitacm.moremovies.ui.detail

import com.hanitacm.data.repository.model.MovieDomainModel

sealed class DetailViewModelState {
    object Loading : DetailViewModelState()

    data class DetailLoaded(val movie: MovieDomainModel) : DetailViewModelState()

    data class DetailLoadFailure(val error: Throwable) : DetailViewModelState()
}
