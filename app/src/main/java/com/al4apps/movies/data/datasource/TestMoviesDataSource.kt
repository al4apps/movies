package com.al4apps.movies.data.datasource

import com.al4apps.movies.data.models.MovieDto
import com.al4apps.movies.domain.models.MovieItem
import com.al4apps.movies.domain.models.MovieModel

class TestMoviesDataSource : MoviesDataSource {
    override suspend fun getMovies(): List<MovieDto> {
        return emptyList()
    }
}