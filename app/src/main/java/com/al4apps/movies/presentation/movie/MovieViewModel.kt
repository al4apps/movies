package com.al4apps.movies.presentation.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.al4apps.movies.domain.GetMovieUseCase
import com.al4apps.movies.domain.models.MovieModel
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    private val _movieLiveData = MutableLiveData<MovieModel>()
    val movieLiveData: LiveData<MovieModel> get() = _movieLiveData

    fun fetchMovieInfo(movieId: Int) {
        viewModelScope.launch {
            try {
                getMovieUseCase.get(movieId)?.let {
                    _movieLiveData.value = it
                }
            } catch (t: Throwable) {
                Log.d("MovieViewModel", t.message.toString())
            }
        }
    }
}