package com.example.movielist.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.movielist.core.domain.usecase.MoviesUseCase

class HomeViewModel(moviesUseCase: MoviesUseCase):ViewModel() {
    val movies = moviesUseCase.getAllMovies().asLiveData()
}