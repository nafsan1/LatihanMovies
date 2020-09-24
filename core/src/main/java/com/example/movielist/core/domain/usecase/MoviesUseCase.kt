package com.example.movielist.core.domain.usecase

import com.example.movielist.core.data.Resource
import com.example.movielist.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow


interface MoviesUseCase {

    fun getAllMovies(): Flow<Resource<List<Movies>>>

    fun getFavoriteMovies(): Flow<List<Movies>>

    fun setFavourite(movies: Movies, state:Boolean)
}