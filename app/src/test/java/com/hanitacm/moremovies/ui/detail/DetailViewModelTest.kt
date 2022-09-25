package com.hanitacm.moremovies.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.hanitacm.data.repository.MoviesRepository
import com.hanitacm.data.repository.model.MovieDomainModel
import com.hanitacm.moremovies.ui.MainCoroutineRule
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var moviesRepository: MoviesRepository

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    @Mock
    lateinit var observer: Observer<DetailViewModelState>


    @InjectMocks
    lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        detailViewModel.viewState.observeForever(observer)
    }

    @Test
    fun `get movie detail from repository`() = runTest {
        val id = 34

        whenever(moviesRepository.getMovieDetail(id)).thenReturn(movie)

        detailViewModel.getMovieDetail(id)

        verify(moviesRepository, only()).getMovieDetail(id)
        verify(observer).onChanged(DetailViewModelState.Loading)
        verify(observer).onChanged(DetailViewModelState.DetailLoaded(movie))
    }

    private val movie =
        MovieDomainModel(
            popularity = 2000.0,
            voteAverage = 0.0,
            overview = "A professional thief with \$40 million in debt and his family's life on the line must commit one final heist - rob a futuristic airborne casino filled with the world's most dangerous criminals.",
            posterPath = "/6CoRTJTmijhBLJTUNoVSUNxZMEI.jpg",
            releaseDate = "2020-09-29",
            title = "Money Plane",
            originalTitle = "Money Plane",
            originalLanguage = "en",
            backdropPath = "/gYRzgYE3EOnhUkv7pcbAAsVLe5f.jpg",
            id = 34
        )

}