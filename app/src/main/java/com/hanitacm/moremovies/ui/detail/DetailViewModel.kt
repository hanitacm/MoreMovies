package com.hanitacm.moremovies.ui.detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanitacm.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    private val _viewState = MutableLiveData<DetailViewModelState>()
    val viewState: LiveData<DetailViewModelState>
        get() {
            if (_viewState.value == null) loading()
            return _viewState
        }

    fun getMovieDetail(id: Int) {

        viewModelScope.launch {
            runCatching { moviesRepository.getMovieDetail(id) }
                .onSuccess { movie ->
                    _viewState.value = DetailViewModelState.DetailLoaded(movie)
                }
                .onFailure {
                    _viewState.value =
                        DetailViewModelState.DetailLoadFailure(it)
                }
        }
    }

    private fun loading() {
        _viewState.value = DetailViewModelState.Loading
    }
}
