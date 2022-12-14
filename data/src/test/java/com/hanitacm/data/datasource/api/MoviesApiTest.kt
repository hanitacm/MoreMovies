package com.hanitacm.data.datasource.api

import com.hanitacm.data.datasource.api.model.MovieData
import com.hanitacm.data.datasource.api.model.MoviesApiModel
import com.hanitacm.data.repository.model.MovieDataModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviesApiTest {
    @Mock
    lateinit var moviesService: MoviesService

    @InjectMocks
    lateinit var moviesApi: MoviesApi

    @Test
    fun `get popular movies from api`() = runTest {
        whenever(moviesService.getPopularMovies(any())).thenReturn(moviesResponse)

        val apiResponse = moviesApi.getAllMovies()

        verify(moviesService, only()).getPopularMovies(any())

        assertEquals(moviesDataModel, apiResponse)
    }

    private val moviesResponse =
        MoviesApiModel(
            page = 1,
            totalPages = 500,
            totalResults = 10000,
            results = listOf(
                MovieData(
                    popularity = 2000.0,
                    voteCount = 0,
                    video = false,
                    posterPath = "/6CoRTJTmijhBLJTUNoVSUNxZMEI.jpg",
                    id = 694919,
                    adult = false,
                    backdropPath = "/gYRzgYE3EOnhUkv7pcbAAsVLe5f.jpg",
                    originalLanguage = "en",
                    originalTitle = "Money Plane",
                    genreIds = listOf(28),
                    title = "Money Plane",
                    voteAverage = 0.0,
                    overview = "A professional thief with \$40 million in debt and his family's life on the line must commit one final heist - rob a futuristic airborne casino filled with the world's most dangerous criminals.",
                    releaseDate = "2020-09-29"

                )
            )
        )

    private val moviesDataModel = listOf(
        MovieDataModel(
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