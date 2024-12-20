package com.al4apps.movies.domain

import com.al4apps.movies.data.repository.MoviesRepositoryImpl
import com.al4apps.movies.domain.models.MovieItem

class GetMoviesUseCase(
    private val moviesRepository: MoviesRepositoryImpl
) {

    suspend fun get(): List<MovieItem> {
        return moviesRepository.getMovieItems()
    }
}