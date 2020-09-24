package com.example.movielist.core.data.remote.network

import com.example.movielist.core.data.remote.response.ListMoviesResponse
import com.example.movielist.core.utils.key
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{category}")
   suspend fun getMovie(
        @Path("category") category: String = "popular",
        @Query("api_key") api_key: String = key,
        @Query("language") language: String = "en-US"
    ):  ListMoviesResponse
}