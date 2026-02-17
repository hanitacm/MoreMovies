package com.hanitacm.moremovies.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanitacm.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val moviesRepository: MoviesRepository,
    ) : ViewModel() {
        private val _viewState = MutableStateFlow<MainViewModelState>(MainViewModelState.Loading)
        val viewState: StateFlow<MainViewModelState>
            get() = _viewState

        fun getPopularMovies() {
            viewModelScope.launch {
                runCatching { moviesRepository.getPopularMovies() }
                    .onSuccess { movies ->
                        _viewState.value = MainViewModelState.MoviesLoaded(movies)
                    }
                    .onFailure {
                        _viewState.value = MainViewModelState.MoviesLoadFailure(it)
                    }
            }
        }
    }
