package com.al4apps.movies.domain

import com.al4apps.movies.domain.models.MovieItem
import com.al4apps.movies.domain.models.MovieModel

interface MoviesRepository {

    suspend fun getMovieItems(): List<MovieItem>

    suspend fun getMovieModels(): List<MovieModel>
}