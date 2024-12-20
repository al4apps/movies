package com.al4apps.movies.presentation.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.al4apps.movies.R
import com.al4apps.movies.databinding.ItemMovieBinding
import com.al4apps.movies.domain.models.MovieItem
import com.al4apps.movies.utils.inflate

class MoviesAdapter(
    private val onItemClick: (id: Int) -> Unit
) : ListAdapter<MovieItem, MoviesViewHolder>(MoviesDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(parent.inflate(R.layout.item_movie, false), onItemClick)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class MoviesDiffUtil : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem == newItem
        }
    }
}

class MoviesViewHolder(
    view: View,
    private val onItemClick: (id: Int) -> Unit
) : RecyclerView.ViewHolder(view) {
    private val binding = ItemMovieBinding.bind(view)

    fun bind(movie: MovieItem) {
        binding.root.setOnClickListener { onItemClick(movie.id) }
        if (movie.imageUrl == null) binding.posterImageView.load(R.drawable.placeholder)
        movie.imageUrl?.let {
            binding.posterImageView.load(movie.imageUrl) {
                placeholder(R.drawable.placeholder)
            }
        }
        binding.titleTextView.text = movie.localizedName
        binding.titleTextView.maxLines = 2
    }
}