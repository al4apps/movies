package com.al4apps.movies.data.datasource

import com.al4apps.movies.data.models.MovieDto
import com.al4apps.movies.data.network.SequeniaApi

class MoviesDataSourceImpl(
    private val sequeniaApi: SequeniaApi
) : MoviesDataSource {
    override suspend fun getMovies(): List<MovieDto> {
        return sequeniaApi.getMovies().films
    }
}