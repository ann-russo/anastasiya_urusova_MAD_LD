package com.example.mad_ld.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mad_ld.models.Movie
import com.example.mad_ld.models.getMovies
import com.example.mad_ld.widgets.AppBar
import com.example.mad_ld.widgets.MovieCard

@Composable
fun HomeScreen() {
    AppBar()
    MovieList()
}

@Preview
@Composable
fun MovieList(movies: List<Movie> = getMovies()) {
    LazyColumn {
        items(movies) {movie ->
            MovieCard(movie = movie)
        }
    }
}