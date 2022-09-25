package com.hanitacm.data

import com.hanitacm.data.datasource.api.MoviesApi
import com.hanitacm.data.datasource.cache.MoviesCache
import com.hanitacm.data.repository.MoviesRepository
import com.hanitacm.data.repository.model.MovieDataModel
import com.hanitacm.data.repository.model.MovieDomainModel
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviesRepositoryTest {

    @Mock
    lateinit var moviesApi: MoviesApi

    @Mock
    lateinit var moviesCache: MoviesCache

    @InjectMocks
    lateinit var moviesRepository: MoviesRepository


    @Test
    fun `get popular movies from cache`() = runTest {
        whenever(moviesCache.getAllMovies()).thenReturn(moviesDataModel)

        moviesRepository.getPopularMovies()

        verify(moviesCache, only()).getAllMovies()
        verifyZeroInteractions(moviesApi)
        verify(moviesCache, never()).insertMovies(moviesDataModel)

    }

    @Test
    fun `get popular movies from api when cache is empty`() = runTest {
        whenever(moviesCache.getAllMovies()).thenReturn(emptyList())
        whenever(moviesApi.getAllMovies()).thenReturn(moviesDataModel)

        moviesRepository.getPopularMovies()

        verify(moviesCache).getAllMovies()
        verify(moviesApi, only()).getAllMovies()

    }

    @Test
    fun `insert movies in cache after get them`() = runTest {
        whenever(moviesCache.getAllMovies()).thenReturn(emptyList())
        whenever(moviesApi.getAllMovies()).thenReturn(moviesDataModel)

        moviesRepository.getPopularMovies()

        verify(moviesCache).insertMovies(moviesDataModel)
    }

    @Test
    fun `map movies result to moviesDataModel`() = runTest {
        whenever(moviesCache.getAllMovies()).thenReturn(emptyList())
        whenever(moviesApi.getAllMovies()).thenReturn(moviesDataModel)

        val moviesResponse = moviesRepository.getPopularMovies()

        Assert.assertEquals(moviesDomainModel, moviesResponse)
    }

    @Test
    fun `get detail movie from cache`() = runTest {
        val id = 694919

        whenever(moviesCache.getMovieDetail(id)).thenReturn(movieDataModel)

        val movieResponse = moviesRepository.getMovieDetail(id)

        verify(moviesCache, only()).getMovieDetail(id)
        verifyZeroInteractions(moviesApi)

        assert(movieResponse == movieDomainModel)

    }

    private val movieDataModel = MovieDataModel(
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
    private val moviesDataModel = listOf(movieDataModel)


    private val movieDomainModel = MovieDomainModel(
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
    private val moviesDomainModel = listOf(movieDomainModel)


}