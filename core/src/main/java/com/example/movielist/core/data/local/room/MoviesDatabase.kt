package com.example.movielist.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movielist.core.data.local.entity.MoviesEntity

@Database(entities = [MoviesEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase:RoomDatabase() {
    abstract fun moviesDao():MoviesDao
}