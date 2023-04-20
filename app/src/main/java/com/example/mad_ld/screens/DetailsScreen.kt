package com.example.mad_ld.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mad_ld.db.MovieDatabase
import com.example.mad_ld.db.repository.MovieRepository
import com.example.mad_ld.viewmodel.DetailsViewModel
import com.example.mad_ld.viewmodelfactory.DetailsViewModelFactory
import com.example.mad_ld.widgets.AppBar
import com.example.mad_ld.widgets.ImagesList
import com.example.mad_ld.widgets.MovieCard
import com.example.mad_ld.widgets.MovieList
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(navController: NavController, movieId: Int) {
    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao())
    val factory = DetailsViewModelFactory(repository = repository, movieId = movieId)
    val viewModel: DetailsViewModel = viewModel(factory = factory)
    val movie = viewModel.movie.value
    var title = ""
    if (movie != null) {
        title = movie.title
    }

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { AppBar(navController, title)},
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .verticalScroll(rememberScrollState())) {
                    if (movie == null) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.h4,
                            text = "Movie not found!"
                        )
                    } else {
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
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "${movie.title} successfully deleted"
                                    )
                                }
                            }
                        )
                        ImagesList(images = movie.images)
                    }
                }
            }
        )
    }
}