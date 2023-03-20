package com.example.mad_ld.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mad_ld.models.FavoritesModel
import com.example.mad_ld.models.Movie
import com.example.mad_ld.widgets.AppBar
import com.example.mad_ld.widgets.MovieCard

@Composable
fun FavoriteScreen(navController: NavController, favMovies: List<Movie>, favoritesModel: FavoritesModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            AppBar(navController, "Favorites")
            FavoritesList(favMovies, favoritesModel)
        }
    }
}

@Composable
fun FavoritesList(movies: List<Movie>, favoritesModel: FavoritesModel) {
    if (movies.isEmpty()) {
        Text(
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.h4,
            text = "Your list is empty!")
    }
    LazyColumn{
        items(movies) {movie ->
            MovieCard(movie = movie, favoritesModel = favoritesModel)
        }
    }
}