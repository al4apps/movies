package com.al4apps.movies.presentation.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.al4apps.movies.domain.GetMoviesUseCase
import com.al4apps.movies.domain.models.MovieItem
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private var moviesList = emptyList<MovieItem>()

    private val _moviesLiveData = MutableLiveData<List<MovieItem>>()
    val moviesLiveData: LiveData<List<MovieItem>>
        get() = _moviesLiveData

    private val _genresLiveData = MutableLiveData<List<String>>()
    val genresLiveData: LiveData<List<String>>
        get() = _genresLiveData

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean>
        get() = _isLoadingLiveData

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {
            try {
                _isLoadingLiveData.value = true
                moviesList = getMoviesUseCase.get().sortedBy { it.localizedName }
                Log.d("MoviesViewModel", moviesList.joinToString("\n"))
                updateGenres(moviesList)
                _moviesLiveData.value = moviesList
            } catch (t: Throwable) {
                Log.d("MoviesViewModel", t.message.toString())
            } finally {
                _isLoadingLiveData.value = false
            }
        }
    }

    private fun updateGenres(movies: List<MovieItem>) {
        val genresSet = mutableSetOf<String>()
        movies.map { genresSet.addAll(it.genres) }
        Log.d("MoviesViewModel", genresSet.joinToString("\n"))
        _genresLiveData.value = genresSet.toList()
    }

    fun showMoviesByGenre(genre: String) {
        _moviesLiveData.value = moviesList.filter { it.genres.contains(genre) }
    }
}