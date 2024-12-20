package com.al4apps.movies.data.network

import com.al4apps.movies.data.models.MoviesResponseModel
import retrofit2.http.GET

interface SequeniaApi {

    @GET("films.json")
    suspend fun getMovies(): MoviesResponseModel
}