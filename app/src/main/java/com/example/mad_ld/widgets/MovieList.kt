package com.example.mad_ld.widgets

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mad_ld.models.Movie

@Composable
fun MovieList(
    navController: NavController = rememberNavController(),
    list: List<Movie>,
    onFavoriteMovie: (Movie, Boolean) -> Unit
) {
    LazyColumn{
        items(list) {movie ->
            MovieCard(
                movie = movie,
                favorite = movie.isFavorite,
                onFavoriteChange = { favorite -> onFavoriteMovie(movie, favorite) }
            ) { movieId ->
                navController.navigate("detail/$movieId")
            }
        }
    }
}