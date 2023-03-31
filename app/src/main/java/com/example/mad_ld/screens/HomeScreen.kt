package com.example.mad_ld.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mad_ld.viewmodels.MoviesViewModel
import com.example.mad_ld.widgets.AppBar
import com.example.mad_ld.widgets.MovieList

@Composable
fun HomeScreen(navController: NavController = rememberNavController(), moviesViewModel: MoviesViewModel = viewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            AppBar(navController, "Movies")
            MovieList(
                navController,
                list = moviesViewModel.movieList,
                onFavoriteMovie = { movie, favorite ->
                    moviesViewModel.changeFavState(movie, favorite)
                }
            )
        }
    }
}