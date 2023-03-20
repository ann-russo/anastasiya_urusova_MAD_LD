package com.example.mad_ld.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoritesModel : ViewModel() {

    private val _favMovies = MutableLiveData<List<Movie>>()
    val favMovies: LiveData<List<Movie>>
        get() = _favMovies

    fun addToFavorites(movie: Movie) {
        val favMovies = _favMovies.value?.toMutableList() ?: mutableListOf()
        favMovies.add(movie)
        _favMovies.value = favMovies
    }

    fun removeFromFavorites(movie: Movie) {
        val favMovies = _favMovies.value?.toMutableList() ?: mutableListOf()
        favMovies.remove(movie)
        _favMovies.value = favMovies
    }

    fun isFavorite(movie: Movie): Boolean {
        val favMovies = _favMovies.value?.toMutableList() ?: mutableListOf()
        return favMovies.contains(movie)
    }
}