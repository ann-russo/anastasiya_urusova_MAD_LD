package com.example.mad_ld.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mad_ld.screens.AddMovieScreen
import com.example.mad_ld.screens.DetailScreen
import com.example.mad_ld.screens.FavoriteScreen
import com.example.mad_ld.screens.HomeScreen
import com.example.mad_ld.viewmodels.MoviesViewModel

@Composable
fun Navigation(moviesViewModel: MoviesViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController, moviesViewModel)
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.StringType
                })
        ) {backStackEntry ->
            DetailScreen(navController, moviesViewModel, movieId = backStackEntry.arguments?.getString("movieId"))
        }

        composable(Screen.Favorites.route) {
            FavoriteScreen(navController, moviesViewModel)
        }

        composable(Screen.Add.route) {
            AddMovieScreen(navController = navController, moviesViewModel = moviesViewModel)
        }
    }
}

sealed class Screen(val route: String) {
    object Home: Screen("homescreen")
    object Details: Screen("detail/{movieId}")
    object Favorites: Screen("favorites")
    object Add: Screen("add")
}