package com.example.mad_ld.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_ld.db.entity.Movie
import com.example.mad_ld.db.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val movieRepository: MovieRepository): ViewModel() {
    private val _movieList = MutableStateFlow(listOf<Movie>())
    val movieList: StateFlow<List<Movie>> = _movieList.asStateFlow()

    init {
        viewModelScope.launch {
            movieRepository.getAllMovies().collect{ movieList ->
                if (movieList.isNotEmpty()) {
                    _movieList.value = movieList
                } else {
                    _movieList.value = emptyList()
                }
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