package com.example.mad_ld.screens

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mad_ld.R
import com.example.mad_ld.models.Genre
import com.example.mad_ld.models.ListItemSelectable
import com.example.mad_ld.navigation.Screen
import com.example.mad_ld.viewmodels.MoviesViewModel
import com.example.mad_ld.viewmodels.OutlineTextFieldWithErrorView
import com.example.mad_ld.widgets.AppBar
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddMovieScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    moviesViewModel: MoviesViewModel
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            AppBar(navController, "Add new movie")

            val scope = rememberCoroutineScope()
            val snackBarHostState = remember { SnackbarHostState() }

            var title by remember { mutableStateOf("") }
            var titleError by remember { mutableStateOf(false) }

            var year by remember { mutableStateOf("") }
            var yearError by remember { mutableStateOf(false) }

            var genres by remember { mutableStateOf(getMappedGenres()) }
            var genresError by remember { mutableStateOf(false) }

            var director by remember { mutableStateOf("") }
            var directorError by remember { mutableStateOf(false) }

            var actors by remember { mutableStateOf("") }
            var actorsError by remember { mutableStateOf(false) }

            var plot by remember { mutableStateOf("") }
            var plotError by remember { mutableStateOf(false) }

            var rating by remember { mutableStateOf("") }
            var ratingError by remember { mutableStateOf(false) }

            OutlineTextFieldWithErrorView(
                value = title,
                onValueChange = { input: String ->
                    title = input
                    titleError = !moviesViewModel.isStringValid(input)
                },
                label = { Text(text = stringResource(R.string.enter_movie_title)) },
                singleLine = true,
                isError = titleError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                errorMsg = stringResource(R.string.error_movie_title),
                trailingIcon = {
                    if (titleError) {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = "Error"
                        )
                    }
                }
            )

            OutlineTextFieldWithErrorView(
                value = year,
                onValueChange = { input: String ->
                    year = input
                    yearError = !moviesViewModel.isYearValid(input)
                },
                label = { Text(stringResource(R.string.enter_movie_year)) },
                singleLine = true,
                isError = yearError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                errorMsg = stringResource(R.string.error_movie_year),
                trailingIcon = {
                    if (yearError) {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = "Error"
                        )
                    }
                }
            )

            Text(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .padding(horizontal = 10.dp),
                text = stringResource(R.string.select_genres),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6
            )

            LazyHorizontalGrid(
                modifier = Modifier
                    .height(180.dp)
                    .padding(horizontal = 10.dp),
                rows = GridCells.Fixed(5)
            ) {
                items(genres) { genreItem ->
                    Chip(
                        modifier = Modifier.padding(2.dp),
                        colors = ChipDefaults.chipColors(
                            backgroundColor = if (genreItem.isSelected)
                                colorResource(id = R.color.purple_200)
                            else
                                colorResource(id = R.color.white)
                        ),
                        onClick = {
                            genres = genres.map {
                                if (it.title == genreItem.title) {
                                    genreItem.copy(isSelected = !genreItem.isSelected)
                                } else {
                                    it
                                }
                            }
                        }
                    ) {
                        Text(text = genreItem.title)
                    }
                }
            }

            if (!moviesViewModel.isGenreValid(genres)) {
                genresError = true
                Text(
                    text = stringResource(R.string.error_genres),
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp)
                )
            } else {
                genresError = false
                Text(text = "")
            }

            OutlineTextFieldWithErrorView(
                value = director,
                onValueChange = { input: String ->
                    director = input
                    directorError = !moviesViewModel.isStringValid(input)
                },
                label = { Text(stringResource(R.string.enter_director)) },
                singleLine = true,
                isError = directorError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                errorMsg = stringResource(R.string.error_director),
                trailingIcon = {
                    if (directorError) {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = "Error"
                        )
                    }
                }
            )

            OutlineTextFieldWithErrorView(
                value = actors,
                onValueChange = { input: String ->
                    actors = input
                    actorsError = !moviesViewModel.isStringValid(input)
                },
                label = { Text(stringResource(R.string.enter_actors)) },
                singleLine = true,
                isError = actorsError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                errorMsg = stringResource(R.string.error_actors),
                trailingIcon = {
                    if (actorsError) {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = "Error"
                        )
                    }
                }
            )

            OutlineTextFieldWithErrorView(
                value = plot,
                onValueChange = { input: String ->
                    plot = input
                    plotError = !moviesViewModel.isStringValid(input)
                },
                label = {
                    Text(
                        textAlign = TextAlign.Start,
                        text = stringResource(R.string.enter_plot)
                    )
                },
                singleLine = false,
                isError = plotError,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(horizontal = 10.dp),
                errorMsg = stringResource(R.string.error_plot),
                trailingIcon = {
                    if (plotError) {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = "Error"
                        )
                    }
                }
            )

            OutlineTextFieldWithErrorView(
                value = rating,
                onValueChange = { input: String ->
                    rating = input
                    ratingError = !moviesViewModel.isRatingValid(input)
                },
                label = { Text(stringResource(R.string.enter_rating)) },
                singleLine = true,
                isError = ratingError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                errorMsg = stringResource(R.string.error_rating),
                trailingIcon = {
                    if (ratingError) {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = "Error"
                        )
                    }
                }
            )

            SnackbarHost(hostState = snackBarHostState)

            Button(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                enabled = validateInputs(
                    moviesViewModel = moviesViewModel,
                    title = title,
                    year = year,
                    genres = genres,
                    director = director,
                    actors = actors,
                    plot = plot,
                    rating = rating
                ),
                onClick = {
                    moviesViewModel.createNewMovie(
                        title,
                        year,
                        genres.filter { genre: ListItemSelectable -> genre.isSelected },
                        director,
                        actors,
                        plot,
                        rating
                    )
                    scope.launch {
                        snackBarHostState.showSnackbar("Added movie to catalogue!")
                    }
                    Handler(Looper.getMainLooper()).postDelayed({
                        navController.navigate(Screen.Home.route)
                    }, 3000)
                }) {
                Text(text = stringResource(R.string.add))
            }

        }
    }
}

fun getMappedGenres(): List<ListItemSelectable> {
    return Genre.values().toList().map { genre ->
        ListItemSelectable(
            title = genre.toString(),
            isSelected = false
        )
    }
}

fun validateInputs(
    moviesViewModel: MoviesViewModel,
    title: String,
    year: String,
    genres: List<ListItemSelectable>,
    director: String,
    actors: String,
    plot: String,
    rating: String
): Boolean {
    return moviesViewModel.isStringValid(title) &&
            moviesViewModel.isYearValid(year) &&
            moviesViewModel.isGenreValid(genres) &&
            moviesViewModel.isStringValid(director) &&
            moviesViewModel.isStringValid(actors) &&
            moviesViewModel.isStringValid(plot) &&
            moviesViewModel.isRatingValid(rating)
}