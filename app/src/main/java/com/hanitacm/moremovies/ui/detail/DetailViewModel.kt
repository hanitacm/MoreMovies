package com.hanitacm.moremovies.ui.detail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanitacm.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    private val _viewState = MutableStateFlow<DetailViewModelState>(DetailViewModelState.Loading)
    val viewState: StateFlow<DetailViewModelState>
        get() = _viewState


    fun getMovieDetail(id: Int) {

        viewModelScope.launch {
            delay(1000)
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
}
