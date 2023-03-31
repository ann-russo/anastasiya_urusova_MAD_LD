package com.example.mad_ld.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mad_ld.viewmodels.MoviesViewModel
import com.example.mad_ld.widgets.AppBar
import com.example.mad_ld.widgets.MovieList

@Composable
fun FavoriteScreen(navController: NavController, moviesViewModel: MoviesViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            AppBar(navController, "Favorites")
            if (moviesViewModel.getFavoriteMovies().isEmpty()) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.h4,
                    text = "Your Favorites list is empty!")
            }
            MovieList(
                navController,
                list = moviesViewModel.getFavoriteMovies(),
                onFavoriteMovie = { movie, favorite ->
                    moviesViewModel.changeFavState(movie, favorite)
                }
            )
        }
    }
}