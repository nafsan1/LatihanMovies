package com.example.movielist.core.data.local

import android.util.Log
import com.example.movielist.core.data.local.entity.MoviesEntity
import com.example.movielist.core.data.local.room.MoviesDao
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class LocalDataSource(private  val moviesDao: MoviesDao) {

    fun getAllMovies(): Flow<List<MoviesEntity>> = moviesDao.getAllMovies()

    fun getFavoriteMovies(): Flow<List<MoviesEntity>> = moviesDao.getFavoriteMovies()

    suspend fun insertMovies(moviesList: List<MoviesEntity>) = moviesDao.insertMovies(moviesList)

    fun setFavoriteMovies(movies: MoviesEntity, newState: Boolean) {
        movies.isFavorite = newState
        Timber.d("isFavourite $newState")

        moviesDao.updateFavoriteMovies(movies)
    }
}