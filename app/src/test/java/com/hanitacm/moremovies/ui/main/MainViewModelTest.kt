package com.hanitacm.moremovies.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.hanitacm.data.repository.MoviesRepository
import com.hanitacm.data.repository.model.MovieDomainModel
import com.hanitacm.moremovies.ui.MainCoroutineRule
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
class MainViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    @Mock
    lateinit var moviesRepository: MoviesRepository

    @InjectMocks
    lateinit var mainViewModel: MainViewModel

    @Mock
    lateinit var observer: Observer<in MainViewModelState>

    @Before
    fun setUp() {
        mainViewModel.viewState.observeForever(observer)
    }

    @Test
    fun `call getPopularMovies repository`() = runTest {
        whenever(moviesRepository.getPopularMovies()).thenReturn(listPopularMovies)

        mainViewModel.getPopularMovies()

        verify(observer).onChanged(MainViewModelState.Loading)
        verify(observer).onChanged(MainViewModelState.MoviesLoaded(listPopularMovies))

    }

    @Test
    fun `returns error if it is not possible get popular movies list`() = runTest {
        val error = RuntimeException()

        whenever(moviesRepository.getPopularMovies()).thenThrow(error)

        mainViewModel.getPopularMovies()

        verify(observer).onChanged(MainViewModelState.MoviesLoadFailure(error))
    }

    private val listPopularMovies = listOf(
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
            id = 694919
        )
    )
}




