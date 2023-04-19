package com.example.mad_ld.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_ld.db.entity.Movie
import com.example.mad_ld.db.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import okhttp3.internal.notifyAll

class FavoritesViewModel(private val movieRepository: MovieRepository): ViewModel() {
    private val _favoritesList = MutableStateFlow(listOf<Movie>())
    val favoritesList: StateFlow<List<Movie>> = _favoritesList.asStateFlow()

    init {
        viewModelScope.launch {
            movieRepository.getFavoriteMovies().collect { movieList ->
                if (movieList.isNotEmpty()) {
                    _favoritesList.value = movieList
                } else {
                    _favoritesList.value = emptyList()
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