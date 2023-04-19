package com.example.mad_ld.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mad_ld.db.repository.MovieRepository
import com.example.mad_ld.viewmodel.DetailsViewModel

class DetailsViewModelFactory(private val repository: MovieRepository, private val movieId: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(repository, movieId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}