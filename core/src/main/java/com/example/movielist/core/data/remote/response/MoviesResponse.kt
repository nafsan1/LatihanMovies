package com.example.movielist.core.data.remote.response

import com.google.gson.annotations.SerializedName

class MoviesResponse (

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("release_date")
    val release_date: String,

    @field:SerializedName("poster_path")
    val poster_path: String,

    @field:SerializedName("overview")
    val overview: String
)