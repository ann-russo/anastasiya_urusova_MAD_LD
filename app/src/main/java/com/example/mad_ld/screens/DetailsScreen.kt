package com.example.mad_ld.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mad_ld.models.FavoritesModel
import com.example.mad_ld.models.Movie
import com.example.mad_ld.models.getMovies
import com.example.mad_ld.widgets.AppBar
import com.example.mad_ld.widgets.ImageCard
import com.example.mad_ld.widgets.MovieCard


@Composable
fun DetailScreen(navController: NavController, favoritesModel: FavoritesModel, movieId: String?) {
    val movie = movieId?.let { findMovieById(movieId) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            if (movie != null) {
                AppBar(navController, movie.title)
                MovieCard(movie = movie, favoritesModel = favoritesModel)
                ImagesList(images = movie.images)
            }
        }
    }
}

@Composable
fun ImagesList(images: List<String>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Movie Images",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(10.dp))
        LazyRow{
            items(images) {image ->
                ImageCard(image = image)
            }
        }
    }
}

fun findMovieById(movieId: String): Movie? {
    return getMovies().find { movie: Movie -> movie.id == movieId }
}