package com.example.mad_ld.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mad_ld.viewmodels.MoviesViewModel
import com.example.mad_ld.widgets.AppBar
import com.example.mad_ld.widgets.ImagesList
import com.example.mad_ld.widgets.MovieCard


@Composable
fun DetailScreen(navController: NavController, moviesViewModel: MoviesViewModel, movieId: String?) {
    val movie = movieId?.let { moviesViewModel.findMovieById(movieId) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            if (movie != null) {
                AppBar(navController, movie.title)
                MovieCard(
                    movie = movie,
                    favorite = movie.isFavorite,
                    onFavoriteChange = { favorite -> moviesViewModel.changeFavState(movie, favorite) }
                )
                ImagesList(images = movie.images)
            }
        }
    }
}