package com.al4apps.movies.domain

import com.al4apps.movies.data.repository.MoviesRepositoryImpl
import com.al4apps.movies.domain.models.MovieModel

class GetMovieUseCase(
    private val moviesRepository: MoviesRepositoryImpl
) {

    suspend fun get(id: Int): MovieModel? {
        return moviesRepository.getMovieModels().find { movie -> movie.id == id }
    }
}