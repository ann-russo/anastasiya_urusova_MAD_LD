package com.example.mad_ld.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_ld.db.entity.Movie
import com.example.mad_ld.db.repository.MovieRepository
import kotlinx.coroutines.launch

class DetailsViewModel(private val movieRepository: MovieRepository, movieId: Int): ViewModel() {
    private val _movie: MutableState<Movie?> = mutableStateOf(null)
    val movie: State<Movie?> = _movie

    init {
        viewModelScope.launch {
            movieRepository.getMovieById(movieId).collect{ movie ->
                _movie.value = movie
            }
        }
    }

    suspend fun changeFavState(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        movieRepository.update(movie)
    }

    suspend fun deleteMovie(movie: Movie) {
        movieRepository.delete(movie)
    }
}