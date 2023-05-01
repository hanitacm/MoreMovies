package com.hanitacm.moremovies.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screens(val baseRoute: String, val args: List<NamedNavArgument> = emptyList()) {

    val route = baseRoute.plus(args.joinToString("/", prefix = "/") { "{${it.name}}" })

    object Main : Screens("main")
    object MovieDetail : Screens(
        baseRoute = "detail",
        args = listOf(navArgument(Arguments.MOVIE_ID) { type = NavType.IntType })
    ) {
        const val movieIdArg = Arguments.MOVIE_ID
    }
}

private object Arguments {
    const val MOVIE_ID: String = "movieId"
}