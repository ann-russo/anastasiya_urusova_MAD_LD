package com.example.mad_ld.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mad_ld.utils.InjectorUtils
import com.example.mad_ld.viewmodel.FavoritesViewModel
import com.example.mad_ld.widgets.AppBar
import com.example.mad_ld.widgets.MovieList
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(navController: NavController) {
    val viewModel: FavoritesViewModel = viewModel(
        factory = InjectorUtils.provideFavoritesViewModelFactory(
            LocalContext.current
        )
    )
    val moviesState by viewModel.favoritesList.collectAsState()
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { AppBar(navController, "Favorites") },
            content = { padding ->
                Column(modifier = Modifier.padding(padding)) {
                    MovieList(
                        navController,
                        list = moviesState,
                        onFavoriteMovie = { movie ->
                            coroutineScope.launch {
                                viewModel.changeFavState(movie)
                            }
                        },
                        onDelete = { movie ->
                            coroutineScope.launch {
                                viewModel.deleteMovie(movie)
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "${movie.title} successfully deleted"
                                )
                            }
                        }
                    )
                }
            }
        )
    }
}