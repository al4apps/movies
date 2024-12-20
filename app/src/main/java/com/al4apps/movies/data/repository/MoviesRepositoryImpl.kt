package com.al4apps.movies.data.repository

import com.al4apps.movies.data.datasource.MoviesDataSourceImpl
import com.al4apps.movies.data.models.MovieDto
import com.al4apps.movies.domain.MoviesRepository
import com.al4apps.movies.domain.models.MovieItem
import com.al4apps.movies.domain.models.MovieModel

class MoviesRepositoryImpl(
    private val moviesDataSource: MoviesDataSourceImpl
) : MoviesRepository {
    override suspend fun getMovieItems(): List<MovieItem> {
        return moviesDataSource.getMovies().map { it.toMovieItem() }
    }

    override suspend fun getMovieModels(): List<MovieModel> {
        return moviesDataSource.getMovies().map { it.toMovieModel() }
    }

    private fun MovieDto.toMovieItem(): MovieItem {
        return MovieItem(id, localizedName, imageUrl, genres)
    }

    private fun MovieDto.toMovieModel(): MovieModel {
        return MovieModel(id, localizedName, name, year, rating, imageUrl, description, genres)
    }
}