package com.example.mad_ld.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mad_ld.models.FavoritesModel
import com.example.mad_ld.models.Movie
import com.example.mad_ld.models.getMovies
import com.example.mad_ld.widgets.AppBar
import com.example.mad_ld.widgets.MovieCard

@Composable
fun HomeScreen(navController: NavController, favMovies: List<Movie>, favoritesModel: FavoritesModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            AppBar(navController, "Movies")
            MovieList(navController, favoritesModel = favoritesModel)
        }
    }
}

@Composable
fun MovieList(navController: NavController = rememberNavController(), movies: List<Movie> = getMovies(), favoritesModel: FavoritesModel) {
    LazyColumn{
        items(movies) {movie ->
            MovieCard(movie = movie, favoritesModel)  { movieId ->
                navController.navigate("detail/$movieId")
            }
        }
    }
}