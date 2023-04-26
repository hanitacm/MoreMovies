package com.hanitacm.moremovies.ui.main

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hanitacm.moremovies.ui.common.ProgressBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainViewModel) {

    viewModel.getPopularMovies()

    val uiState: MainViewModelState by viewModel.viewState.collectAsStateWithLifecycle()
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) {
        when (uiState) {
            MainViewModelState.Loading -> ProgressBar()
            is MainViewModelState.MoviesLoadFailure -> {
                (uiState as MainViewModelState.MoviesLoadFailure).error.message?.let {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = it
                        )
                    }
                }
            }
            is MainViewModelState.MoviesLoaded -> MovieList((uiState as MainViewModelState.MoviesLoaded).movies)
        }
    }
}