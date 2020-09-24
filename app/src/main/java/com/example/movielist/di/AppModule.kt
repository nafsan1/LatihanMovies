package com.example.movielist.di

import com.example.movielist.core.domain.usecase.MoviesInteractor
import com.example.movielist.core.domain.usecase.MoviesUseCase
import com.example.movielist.detail.DetailViewModel
import com.example.movielist.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory <MoviesUseCase>{MoviesInteractor(get())  }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}