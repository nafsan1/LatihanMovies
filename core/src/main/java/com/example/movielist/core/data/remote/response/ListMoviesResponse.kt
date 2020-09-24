package com.example.movielist.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListMoviesResponse (
    @field:SerializedName("results")
    val movie: List<MoviesResponse>
)