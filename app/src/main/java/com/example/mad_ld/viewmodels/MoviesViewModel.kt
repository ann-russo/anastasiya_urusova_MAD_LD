package com.example.mad_ld.viewmodels

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.mad_ld.models.ListItemSelectable
import com.example.mad_ld.models.Movie
import com.example.mad_ld.models.getMovies

class MoviesViewModel: ViewModel() {
    private val _movieList = getMovies().toMutableStateList()
    val movieList: List<Movie>
        get() = _movieList

    fun changeFavState(movie: Movie, favorite: Boolean) {
        movieList.find { it == movie }?.let { foundMovie ->
            foundMovie.isFavorite = favorite
        }
    }

    fun getFavoriteMovies(): List<Movie> {
        return movieList.filter{ movie: Movie -> movie.isFavorite }
    }

    fun findMovieById(movieId: String): Movie? {
        return movieList.find { it.id == movieId }
    }

    fun validateString(input: String): Boolean {
        return input.isEmpty()
    }

    fun createNewMovie(
        title: String,
        year: String,
        genres: List<ListItemSelectable>,
        director: String,
        actors: String,
        plot: String,
        rating: String
    ) {
        var selectedGenres = ""
        for (genre in genres) {
            selectedGenres += genre.title + " "
        }

        val newMovie = Movie(
            id = createMovieId(),
            title = title,
            year = year,
            genre = selectedGenres,
            director = director,
            actors = actors,
            plot = plot,
            images = listOf("https://developers.elementor.com/docs/assets/img/elementor-placeholder-image.png"),
            rating = rating
        )
        _movieList.add(newMovie)
    }

    private fun createMovieId(): String {
        val idsAsNumbers = mutableListOf<Int>()
        for (movie in movieList) {
            val filteredId = movie.id.replace("tt", "")
            val idAsInt: Int = filteredId.toInt()
            idsAsNumbers.add(idAsInt)
        }
        val randomNum = retrieveRandomNum(idsAsNumbers)
        return "tt$randomNum"
    }

    private fun retrieveRandomNum(except: List<Int>): Int{
        return ((1000000..9999999).filter { !except.contains(it) }).random()
    }
}