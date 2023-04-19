package com.example.mad_ld.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mad_ld.db.entity.Movie
import com.example.mad_ld.db.repository.MovieRepository
import com.example.mad_ld.models.ListItemSelectable
import java.time.Year

class AddMovieViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun isStringValid(input: String): Boolean {
        return input.isNotEmpty()
    }

    fun isYearValid(input: String): Boolean {
        if (!isStringValid(input)) {
            return false
        }
        return try {
            val yearNum = input.toInt()
            yearNum >= 1900 && yearNum <= Year.now().value
        } catch (ne: NumberFormatException) {
            false
        }
    }

    fun isRatingValid(input: String): Boolean {
        if (!isStringValid(input)) {
            return false
        }
        return try {
            val ratingNum = input.toFloat()
            ratingNum > 0 && ratingNum <= 10
        } catch (ne: NumberFormatException) {
            false
        }
    }

    fun isGenreValid(genres: List<ListItemSelectable>): Boolean {
        var atLeastOneSelected = false
        for (genre in genres) {
            if (genre.isSelected) {
                atLeastOneSelected = true
            }
        }
        return atLeastOneSelected
    }

    suspend fun createNewMovie(
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
            title = title,
            year = year,
            genre = selectedGenres,
            director = director,
            actors = actors,
            plot = plot,
            images = listOf("https://developers.elementor.com/docs/assets/img/elementor-placeholder-image.png"),
            rating = rating
        )
        movieRepository.add(newMovie)
    }
}