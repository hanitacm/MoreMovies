package com.hanitacm.moremovies.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanitacm.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {
    private val _viewState = MutableLiveData<MainViewModelState>()
    val viewState: LiveData<MainViewModelState>
        get() {
            if (_viewState.value == null) loading()
            return _viewState
        }

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

    private fun loading() {
        _viewState.value = MainViewModelState.Loading
    }
}
