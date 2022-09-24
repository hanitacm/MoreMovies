package com.hanitacm.moremovies.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hanitacm.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    private val subscription = CompositeDisposable()

    private val _viewState = MutableLiveData<MainViewModelState>()
    val viewState: LiveData<MainViewModelState>
        get() {
            if (_viewState.value == null) loading()
            return _viewState
        }

    fun getPopularMovies() {
        subscription.add(
            moviesRepository.getPopularMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { result ->
                    when {
                        result.isSuccess -> _viewState.postValue(
                            MainViewModelState.MoviesLoaded(
                                result.getOrThrow()
                            )
                        )
                        result.isFailure -> _viewState.postValue(
                            MainViewModelState.MoviesLoadFailure(
                                result.exceptionOrNull()
                            )
                        )
                    }
                })
    }

    private fun loading() {
        _viewState.value = MainViewModelState.Loading
    }

    override fun onCleared() {
        subscription.dispose()
        super.onCleared()
    }
}
