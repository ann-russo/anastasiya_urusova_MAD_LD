package com.example.mad_ld.utils

import android.content.Context
import com.example.mad_ld.db.MovieDatabase
import com.example.mad_ld.db.repository.MovieRepository
import com.example.mad_ld.viewmodelfactory.FavoritesViewModelFactory
import com.example.mad_ld.viewmodelfactory.HomeViewModelFactory

object InjectorUtils {
    private fun getMovieRepository(context: Context): MovieRepository{
        return MovieRepository(MovieDatabase.getDatabase(context).movieDao())
    }

    fun provideHomeViewModelFactory(context: Context): HomeViewModelFactory {
        val repository = getMovieRepository(context)
        return HomeViewModelFactory(repository)
    }

    fun provideFavoritesViewModelFactory(context: Context): FavoritesViewModelFactory {
        val repository = getMovieRepository(context)
        return FavoritesViewModelFactory(repository)
    }

}