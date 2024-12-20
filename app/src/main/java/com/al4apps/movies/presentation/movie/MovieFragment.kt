package com.al4apps.movies.presentation.movie

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import coil.load
import com.al4apps.movies.R
import com.al4apps.movies.databinding.FragmentMovieBinding
import com.al4apps.movies.domain.models.MovieModel
import com.al4apps.movies.utils.AbstractFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : AbstractFragment<FragmentMovieBinding>(FragmentMovieBinding::inflate) {

    private val viewModel by viewModel<MovieViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMovie()
        initToolbar()
        bindViewModel()
    }

    private fun initMovie() {
        val movieId = arguments?.getInt(MOVIE_ID_KEY, NO_ID) ?: NO_ID
        if (movieId != NO_ID) {
            viewModel.fetchMovieInfo(movieId)
        }
    }

    private fun initToolbar() {
        binding.toolbarWithBack.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun updateMovieInfo(movie: MovieModel) {
        if (movie.imageUrl == null) {
            binding.posterImageView.load(R.drawable.placeholder)
        } else {
            binding.posterImageView.load(movie.imageUrl) {
                placeholder(R.drawable.placeholder)
            }
        }
        movie.rating?.let {
            binding.ratingContainer.isVisible = true
            binding.ratingTextView.text = it.toString().take(3)
        }
        val genreYearText = if (movie.genres.isNotEmpty()) {
            val genres = movie.genres.joinToString(", ")
            getString(R.string.movie_genres_year_text, genres, movie.year)
        } else {
            getString(R.string.movie_year_text, movie.year)
        }
        binding.movieGenreYearTextView.text = genreYearText
        binding.toolbarWithBack.title = movie.localizedName
        binding.movieNameTextView.text = movie.localizedName
        binding.descriptionTextView.text = movie.description
    }

    private fun bindViewModel() {
        viewModel.movieLiveData.observe(viewLifecycleOwner, ::updateMovieInfo)
    }

    companion object {
        const val MOVIE_ID_KEY = "movie_id"
        const val NO_ID = -1
    }
}