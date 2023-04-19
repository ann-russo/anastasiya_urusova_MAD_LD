package com.example.mad_ld.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mad_ld.db.entity.Movie

@Composable
fun MovieList(
    navController: NavController = rememberNavController(),
    list: List<Movie>,
    onFavoriteMovie: (Movie) -> Unit = {},
    onDelete: (Movie) -> Unit = {}
) {
    if (list.isEmpty()) {
        Text(
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.h4,
            text = "No movies to display!"
        )
    } else {
        LazyColumn {
            items(list) { movie: Movie ->
                MovieCard(
                    movie = movie,
                    favorite = movie.isFavorite,
                    onFavoriteChange = { onFavoriteMovie(movie) },
                    onDelete = { onDelete(movie) }
                ) { movieId ->
                    navController.navigate("detail/$movieId")
                }
            }
        }
    }
}