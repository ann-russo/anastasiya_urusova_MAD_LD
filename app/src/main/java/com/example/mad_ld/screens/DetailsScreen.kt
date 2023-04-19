package com.example.mad_ld.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mad_ld.db.MovieDatabase
import com.example.mad_ld.db.repository.MovieRepository
import com.example.mad_ld.viewmodel.DetailsViewModel
import com.example.mad_ld.viewmodelfactory.DetailsViewModelFactory
import com.example.mad_ld.widgets.AppBar
import com.example.mad_ld.widgets.ImagesList
import com.example.mad_ld.widgets.MovieCard
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(navController: NavController, movieId: Int) {
    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao())
    val factory = DetailsViewModelFactory(repository = repository, movieId = movieId)
    val viewModel: DetailsViewModel = viewModel(factory = factory)
    val movie = viewModel.movie.value
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            movie?.let {
                AppBar(navController, movie.title)
                MovieCard(
                    movie = movie,
                    favorite = movie.isFavorite,
                    onFavoriteChange = {
                        coroutineScope.launch {
                            viewModel.changeFavState(movie)
                        }
                    },
                    onDelete = {
                        coroutineScope.launch {
                            viewModel.deleteMovie(movie)
                        }
                    }
                )
                ImagesList(images = movie.images)
            }
        }
    }
}