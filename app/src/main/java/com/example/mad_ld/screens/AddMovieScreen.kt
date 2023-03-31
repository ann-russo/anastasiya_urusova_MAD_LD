package com.example.mad_ld.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.mad_ld.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mad_ld.models.Genre
import com.example.mad_ld.models.ListItemSelectable
import com.example.mad_ld.viewmodels.MoviesViewModel
import com.example.mad_ld.viewmodels.OutlineTextFieldWithErrorView
import com.example.mad_ld.widgets.AppBar


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
        var title by remember { mutableStateOf("") }
        var titleError by remember { mutableStateOf(false) }
        val titleUpdate = { data : String ->
            title = data
            if (data.isNotEmpty()){
                titleError = false
            }
        }

        var year by remember { mutableStateOf("") }
        var yearError by remember { mutableStateOf(false) }
        val yearUpdate = { data : String ->
            year = data
            if (data.isNotEmpty()){
                yearError = false
            }
        }

        val genres = Genre.values().toList()
        var genreItems by remember {
            mutableStateOf(genres.map { genre ->
                ListItemSelectable(
                    title = genre.toString(),
                    isSelected = false
                ) }
            ) }
        var genreError by remember { mutableStateOf(false) }
        val genreUpdate = { genreItem : ListItemSelectable ->
            genreItems = genreItems.map {
                if (it.title == genreItem.title) {
                    genreItem.copy(isSelected = !genreItem.isSelected)
                } else {
                    it
                }
            }
        }

        var director by remember { mutableStateOf("") }
        var directorError by remember { mutableStateOf(false) }
        val directorUpdate = { data : String ->
            director = data
            if (data.isNotEmpty()){
                directorError = false
            }
        }

        var actors by remember { mutableStateOf("") }
        var actorsError by remember { mutableStateOf(false) }
        val actorsUpdate = { data : String ->
            actors = data
            if (data.isNotEmpty()){
                actorsError = false
            }
        }

        var plot by remember { mutableStateOf("") }
        var plotError by remember { mutableStateOf(false) }
        val plotUpdate = { data : String ->
            plot = data
            if (data.isNotEmpty()){
                plotError = false
            }
        }

        var rating by remember { mutableStateOf("") }
        var ratingError by remember { mutableStateOf(false) }
        val ratingUpdate = { data : String ->
            rating = data
            if (data.isNotEmpty()){
                ratingError = false
            }
        }

        ValidationsUI(
            title = title,
            titleError = titleError,
            titleUpdate = titleUpdate,
            year = year,
            yearError = yearError,
            yearUpdate = yearUpdate,
            genreItems = genreItems,
            genreError = genreError,
            genreUpdate = genreUpdate,
            director = director,
            directorError = directorError,
            directorUpdate = directorUpdate,
            actors = actors,
            actorsError = actorsError,
            actorsUpdate = actorsUpdate,
            plot = plot,
            plotError = plotError,
            plotUpdate = plotUpdate,
            rating = rating,
            ratingError = ratingError,
            ratingUpdate = ratingUpdate,
            navController = navController
        ) {

            titleError = !title.isNotEmpty()
            yearError = !year.isNotEmpty()
            genreError = !genreItems.isNotEmpty()
            directorError = !director.isNotEmpty()
            actorsError = !actors.isNotEmpty()
            plotError = !plot.isNotEmpty()
            ratingError = !rating.isNotEmpty()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ValidationsUI(
    title: String,
    titleError: Boolean,
    titleUpdate: (String) -> Unit,
    year: String,
    yearError: Boolean,
    yearUpdate: (String) -> Unit,
    genreItems: List<ListItemSelectable>,
    genreError: Boolean,
    genreUpdate: (ListItemSelectable) -> Unit,
    director: String,
    directorError: Boolean,
    directorUpdate: (String) -> Unit,
    actors: String,
    actorsError: Boolean,
    actorsUpdate: (String) -> Unit,
    plot: String,
    plotError: Boolean,
    plotUpdate: (String) -> Unit,
    rating: String,
    ratingError: Boolean,
    ratingUpdate: (String) -> Unit,
    navController: NavController,
    validate: () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        AppBar(navController, "Add new movie")

        var isEnabledSaveButton by remember {
            mutableStateOf(true)
        }

        OutlineTextFieldWithErrorView(
            value = title,
            onValueChange = titleUpdate,
            label = { Text(text = stringResource(R.string.enter_movie_title)) },
            singleLine = true,
            isError = titleError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            errorMsg = "Enter valid title"
        )

        OutlineTextFieldWithErrorView(
            value = year,
            onValueChange = yearUpdate,
            label = { Text(stringResource(R.string.enter_movie_year)) },
            singleLine = true,
            isError = yearError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            errorMsg = "Enter valid year"
        )

        Text(
            modifier = Modifier
                .padding(top = 4.dp)
                .padding(horizontal = 10.dp),
            text = stringResource(R.string.select_genres),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.h6)

        LazyHorizontalGrid(
            modifier = Modifier
                .height(180.dp)
                .padding(horizontal = 10.dp),
            rows = GridCells.Fixed(5)){
            items(genreItems) { genreItem ->
                Chip(
                    modifier = Modifier.padding(2.dp),
                    colors = ChipDefaults.chipColors(
                        backgroundColor = if (genreItem.isSelected)
                            colorResource(id = R.color.purple_200)
                        else
                            colorResource(id = R.color.white)
                    ),
                    onClick = {
                        genreUpdate.invoke(genreItem)
                    }
                ) {
                    Text(text = genreItem.title)
                }
            }
        }

        OutlineTextFieldWithErrorView(
            value = director,
            onValueChange = directorUpdate,
            label = { Text(stringResource(R.string.enter_director)) },
            singleLine = true,
            isError = directorError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            errorMsg = "Enter valid director"
        )

        OutlineTextFieldWithErrorView(
            value = actors,
            onValueChange = actorsUpdate,
            label = { Text(stringResource(R.string.enter_actors)) },
            singleLine = true,
            isError = actorsError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            errorMsg = "Enter valid actors"
        )

        OutlineTextFieldWithErrorView(
            value = plot,
            onValueChange = plotUpdate,
            label = {
                Text(
                    textAlign = TextAlign.Start,
                    text = stringResource(R.string.enter_plot))
                    },
            singleLine = false,
            isError = plotError,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(horizontal = 10.dp),
            errorMsg = "Enter valid plot"
        )

        OutlineTextFieldWithErrorView(
            value = rating,
            onValueChange = ratingUpdate,
            label = { Text(stringResource(R.string.enter_rating)) },
            singleLine = true,
            isError = ratingError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            errorMsg = "Enter valid rating"
        )

        Button(
            modifier = Modifier.padding(10.dp),
            enabled = isEnabledSaveButton,
            onClick = {
                validate()

            }) {
            Text(text = stringResource(R.string.add))
        }
    }
}