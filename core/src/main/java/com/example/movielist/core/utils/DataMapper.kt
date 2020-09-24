package com.example.movielist.core.utils

import com.example.movielist.core.data.local.entity.MoviesEntity
import com.example.movielist.core.data.remote.response.MoviesResponse
import com.example.movielist.core.domain.model.Movies

object DataMapper {
    fun mapResponsesToEntities(input:List<MoviesResponse>):List<MoviesEntity>{
        val moviesList = ArrayList<MoviesEntity>()
        input.map {
            val movies = MoviesEntity(
                id = it.id,
                title = it.title,
                release_date = it.release_date,
                poster_path = it.poster_path,
                overview = it.overview,
                isFavorite = false
            )
            moviesList.add(movies)
        }
        return moviesList
    }

    fun mapEntitiesToDomain(input: List<MoviesEntity>): List<Movies> =
        input.map {
            Movies(
                id = it.id,
                title = it.title,
                release_date = it.release_date,
                poster_path = it.poster_path,
                overview = it.overview,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input:Movies) = MoviesEntity(
        id = input.id,
        title = input.title,
        release_date = input.release_date,
        poster_path = input.poster_path,
        overview = input.overview,
        isFavorite = input.isFavorite
    )
}