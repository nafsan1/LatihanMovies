package com.example.movielist.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.movielist.core.domain.usecase.MoviesUseCase

class FavoriteViewModel(private val moviesUseCase: MoviesUseCase):ViewModel() {
    val favoriteMovies = moviesUseCase.getFavoriteMovies().asLiveData()
}