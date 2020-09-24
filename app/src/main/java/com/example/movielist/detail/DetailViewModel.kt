package com.example.movielist.detail

import androidx.lifecycle.ViewModel
import com.example.movielist.core.domain.model.Movies
import com.example.movielist.core.domain.usecase.MoviesUseCase

class DetailViewModel(private val moviesUseCase: MoviesUseCase):ViewModel() {

    fun setFavoriteMovies(movies:Movies,newStatus:Boolean) =
        moviesUseCase.setFavourite(movies,newStatus)
}