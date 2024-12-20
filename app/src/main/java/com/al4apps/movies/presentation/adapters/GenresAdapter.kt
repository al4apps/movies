package com.al4apps.movies.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.al4apps.movies.R
import com.al4apps.movies.databinding.ItemGenreBinding
import com.al4apps.movies.presentation.movies.Genre

class GenresAdapter(
    private val onItemClick : (genre: String) -> Unit
) : ListAdapter<Genre, GenresViewHolder>(GenreDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        return GenresViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        val binding = ItemGenreBinding.bind(holder.itemView)
        val item = currentList[position]
        binding.titleTextView.text = item.genre
        binding.helperView.isVisible = item.isSelected
        binding.root.setOnClickListener {
            onItemClick(item.genre)
        }
    }

    class GenreDiffUtil : DiffUtil.ItemCallback<Genre>() {

        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem.genre == newItem.genre
        }

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem == newItem
        }
    }
}

class GenresViewHolder(view: View) : RecyclerView.ViewHolder(view)