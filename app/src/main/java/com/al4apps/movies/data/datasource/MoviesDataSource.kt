package com.al4apps.movies.data.datasource

import com.al4apps.movies.data.models.MovieDto

interface MoviesDataSource {

    suspend fun getMovies(): List<MovieDto>
}