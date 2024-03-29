package com.hanitacm.moremovies.ui.main

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.hanitacm.data.repository.MoviesRepository
import com.hanitacm.data.repository.model.MovieDomainModel
import com.hanitacm.moremovies.ui.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    @Mock
    lateinit var moviesRepository: MoviesRepository

    @InjectMocks
    lateinit var mainViewModel: MainViewModel

    @Test
    fun `call getPopularMovies repository`() = runTest {
        whenever(moviesRepository.getPopularMovies()).thenReturn(listPopularMovies)

        mainViewModel.getPopularMovies()

        mainViewModel.viewState.test {
            Assert.assertEquals(MainViewModelState.MoviesLoaded(listPopularMovies), awaitItem())

        }
    }

    @Test
    fun `returns error if it is not possible get popular movies list`() = runTest {
        val error = RuntimeException()

        whenever(moviesRepository.getPopularMovies()).thenThrow(error)

        mainViewModel.getPopularMovies()

        mainViewModel.viewState.test {
            Assert.assertEquals(MainViewModelState.MoviesLoadFailure(error), awaitItem())

        }
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




