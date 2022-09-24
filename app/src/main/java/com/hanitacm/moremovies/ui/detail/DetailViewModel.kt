package com.hanitacm.moremovies.ui.detail


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
class DetailViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    private val subscription = CompositeDisposable()

    private val _viewState = MutableLiveData<DetailViewModelState>()
    val viewState: LiveData<DetailViewModelState>
        get() {
            if (_viewState.value == null) loading()
            return _viewState
        }

    fun getMovieDetail(id: Int) {
        subscription.add(
            moviesRepository.getMovieDetail(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { result ->

                    when {
                        result.isSuccess -> _viewState.postValue(
                            DetailViewModelState.DetailLoaded(result.getOrThrow())
                        )
                        result.isFailure -> _viewState.postValue(
                            DetailViewModelState.DetailLoadFailure(result.exceptionOrNull())
                        )


                    }
                })
    }

    private fun loading() {
        _viewState.value = DetailViewModelState.Loading
    }

    override fun onCleared() {
        subscription.dispose()
        super.onCleared()
    }
}
