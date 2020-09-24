package com.example.movielist.core.data.remote

import android.util.Log
import com.example.movielist.core.data.remote.network.ApiResponse
import com.example.movielist.core.data.remote.network.ApiService
import com.example.movielist.core.data.remote.response.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception


class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllMovies():Flow<ApiResponse<List<MoviesResponse>>>{
        return flow {
            try {
                val response = apiService.getMovie()
                val dataArray = response.movie
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.movie))
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e:Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}