package com.example.movielist.core.data.local.room

import androidx.room.*
import com.example.movielist.core.data.local.entity.MoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao{
    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MoviesEntity>>

    @Query("SELECT * FROM movies where isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MoviesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(tourism: List<MoviesEntity>)

    @Update
    fun updateFavoriteMovies(tourism: MoviesEntity)
}