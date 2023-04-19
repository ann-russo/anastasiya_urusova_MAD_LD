package com.example.mad_ld.db.repository

import com.example.mad_ld.db.dao.MovieDao
import com.example.mad_ld.db.entity.Movie
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieDao: MovieDao) {
    suspend fun add(movie: Movie) = movieDao.add(movie = movie)

    suspend fun update(movie: Movie) = movieDao.update(movie = movie)

    suspend fun delete(movie: Movie) = movieDao.delete(movie = movie)

    fun getAllMovies() : Flow<List<Movie>> = movieDao.getAllMovies()

    fun getFavoriteMovies() : Flow<List<Movie>> = movieDao.getFavoriteMovies()

    fun getMovieById(id: Int) : Flow<Movie> = movieDao.getMovieById(id)
}