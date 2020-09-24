package com.example.movielist.core.domain.usecase

import com.example.movielist.core.data.MoviesRepository
import com.example.movielist.core.data.Resource
import com.example.movielist.core.domain.model.Movies
import com.example.movielist.core.domain.repository.IMoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesInteractor(private val moviesRepository: IMoviesRepository):MoviesUseCase {

    override fun getAllMovies(): Flow<Resource<List<Movies>>> = moviesRepository.getAllMovies()

    override fun getFavoriteMovies(): Flow<List<Movies>> = moviesRepository.getFavoriteMovies()

    override fun setFavourite(movies: Movies, state: Boolean)  =moviesRepository.setFavourite(movies,state)
}