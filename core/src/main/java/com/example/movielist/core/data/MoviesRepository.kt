package com.example.movielist.core.data

import com.example.movielist.core.data.local.LocalDataSource
import com.example.movielist.core.data.remote.RemoteDataSource
import com.example.movielist.core.data.remote.network.ApiResponse
import com.example.movielist.core.data.remote.response.MoviesResponse
import com.example.movielist.core.domain.model.Movies
import com.example.movielist.core.domain.repository.IMoviesRepository
import com.example.movielist.core.utils.AppExecutors
import com.example.movielist.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMoviesRepository {
    override fun getAllMovies(): Flow<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<MoviesResponse>>() {
            override fun loadFromDB(): Flow<List<Movies>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean =
               data == null || data.isEmpty()
              //  true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesResponse>>> =
                remoteDataSource.getAllMovies()


            override suspend fun saveCallResult(data: List<MoviesResponse>) {
                val moviesList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(moviesList)
            }

        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movies>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavourite(movies: Movies, state: Boolean) {
        val moviesEntitiy = DataMapper.mapDomainToEntity(movies)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovies(moviesEntitiy, state)
        }
    }
}