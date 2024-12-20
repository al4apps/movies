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
    private var selectedGenre = ""

    private val _moviesLiveData = MutableLiveData<List<MovieItem>>()
    val moviesLiveData: LiveData<List<MovieItem>>
        get() = _moviesLiveData

    private val _genresLiveData = MutableLiveData<List<Genre>>()
    val genresLiveData: LiveData<List<Genre>>
        get() = _genresLiveData

    private val _loadingStateLiveData = MutableLiveData<Int>()
    val loadingStateLiveData: LiveData<Int> get() = _loadingStateLiveData

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            try {
                _loadingStateLiveData.value = MoviesFragment.STATE_LOADING
                moviesList = getMoviesUseCase.get().sortedBy { it.localizedName }
                updateGenres(moviesList)
                showMoviesBySelectedGenre()
                _loadingStateLiveData.value = MoviesFragment.STATE_LOADING_FINISHED
            } catch (t: Throwable) {
                Log.d("MoviesViewModel", t.message.toString())
                _loadingStateLiveData.value = MoviesFragment.EXCEPTION_LOADING
            }
        }
    }

    fun updateSelectedGenre(genre: String) {
        selectedGenre = if (genre.lowercase() != selectedGenre) {
            genre.lowercase()
        } else ""
        showMoviesBySelectedGenre()
        updateGenres(moviesList)
    }

    private fun updateGenres(movies: List<MovieItem>) {
        val genresSet = mutableSetOf<String>()
        movies.forEach { genresSet.addAll(it.genres) }

        _genresLiveData.value = genresSet.map { name ->
            Genre(
                name.replaceFirstChar { it.uppercase() },
                name.lowercase() == selectedGenre.lowercase()
            )
        }
    }

    private fun showMoviesBySelectedGenre() {
        _moviesLiveData.value = if (selectedGenre == "") moviesList
        else moviesList.filter { it.genres.contains(selectedGenre) }
    }
}