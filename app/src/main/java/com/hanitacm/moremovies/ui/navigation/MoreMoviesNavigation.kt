package com.hanitacm.moremovies.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hanitacm.moremovies.ui.detail.MovieDetail
import com.hanitacm.moremovies.ui.main.MainScreen

@Composable
fun MoreMoviesNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screens.Main.route) {
        composable(Screens.Main.route) {
            MainScreen(viewModel = hiltViewModel(), onMovieClick = { movieId ->
                navController.navigate("${Screens.MovieDetail.baseRoute}/$movieId")
            })
        }
        composable(
            route = Screens.MovieDetail.route,
            arguments = Screens.MovieDetail.args,
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt(Screens.MovieDetail.MOVIE_ID_ARG)
            requireNotNull(movieId)
            MovieDetail(viewModel = hiltViewModel(), movieId = movieId)
        }
    }
}
