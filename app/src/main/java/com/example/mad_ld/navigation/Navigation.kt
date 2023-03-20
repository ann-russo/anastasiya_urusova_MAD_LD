package com.example.mad_ld.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mad_ld.models.FavoritesModel
import com.example.mad_ld.models.Movie
import com.example.mad_ld.screens.DetailScreen
import com.example.mad_ld.screens.FavoriteScreen
import com.example.mad_ld.screens.HomeScreen

@Composable
fun Navigation(favMovies: List<Movie>, favoritesModel: FavoritesModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController, favMovies, favoritesModel)
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.StringType
                })
        ) {backStackEntry ->
            DetailScreen(navController, favoritesModel, movieId = backStackEntry.arguments?.getString("movieId"))
        }

        composable(Screen.Favorites.route) {
            FavoriteScreen(navController, favMovies, favoritesModel)
        }
    }
}

sealed class Screen(val route: String) {
    object Home: Screen("homescreen")
    object Details: Screen("detail/{movieId}")
    object Favorites: Screen("favorites")
}