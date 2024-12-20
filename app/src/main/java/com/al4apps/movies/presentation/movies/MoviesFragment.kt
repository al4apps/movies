package com.al4apps.movies.presentation.movies

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.al4apps.movies.R
import com.al4apps.movies.databinding.FragmentMoviesBinding
import com.al4apps.movies.presentation.adapters.GenresAdapter
import com.al4apps.movies.presentation.adapters.MoviesAdapter
import com.al4apps.movies.presentation.movie.MovieFragment
import com.al4apps.movies.utils.AbstractFragment
import com.al4apps.movies.utils.ItemOffsetDecoration
import com.al4apps.movies.utils.autoCleared
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : AbstractFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    private val viewModel by viewModel<MoviesViewModel>()
    private var genresAdapter: GenresAdapter by autoCleared()
    private var moviesAdapter: MoviesAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        bindViewModel()
    }

    private fun initAdapter() {
        genresAdapter = GenresAdapter { genre ->
            viewModel.updateSelectedGenre(genre)
        }
        binding.genresRecyclerView.adapter = genresAdapter

        moviesAdapter = MoviesAdapter { id ->
            val bundle = bundleOf(MovieFragment.MOVIE_ID_KEY to id)
            val action = R.id.movieFragment
            findNavController().navigate(action, bundle)
        }
        val decor = ItemOffsetDecoration(requireContext())
        binding.moviesRecyclerView.adapter = moviesAdapter
        binding.moviesRecyclerView.addItemDecoration(decor)
    }

    private fun showLoader(isLoading: Boolean) {
        binding.contentContainer.isVisible = !isLoading
        binding.progressBar.isVisible = isLoading
    }

    private fun updateLoadingState(exceptionNumber: Int) {
        when (exceptionNumber) {
            EXCEPTION_LOADING -> onLoadingException()
            STATE_LOADING -> showLoader(true)
            STATE_LOADING_FINISHED -> showLoader(false)
        }
    }

    private fun onLoadingException() {
        Snackbar.make(
            binding.root,
            R.string.movies_error_snackbar_text,
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(R.string.movies_error_snackbar_button_text) {
                viewModel.fetchMovies()
            }
            .show()
        binding.contentContainer.isVisible = false
        binding.progressBar.isVisible = false
    }

    private fun bindViewModel() {
        viewModel.genresLiveData.observe(viewLifecycleOwner) { list ->
            genresAdapter.submitList(list)
        }
        viewModel.moviesLiveData.observe(viewLifecycleOwner) { movies ->
            moviesAdapter.submitList(movies)
        }
        viewModel.loadingStateLiveData.observe(viewLifecycleOwner, ::updateLoadingState)
    }

    companion object {
        const val EXCEPTION_LOADING = 1
        const val STATE_LOADING = 2
        const val STATE_LOADING_FINISHED = 3
    }
}
