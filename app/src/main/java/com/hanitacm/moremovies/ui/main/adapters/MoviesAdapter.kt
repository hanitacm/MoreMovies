package com.hanitacm.moremovies.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hanitacm.data.repository.model.MovieDomainModel
import com.hanitacm.moremovies.databinding.MovieCardItemBinding


class MoviesAdapter(private val onClickItem: (Int) -> Unit) :
    ListAdapter<MovieDomainModel, MoviesAdapter.MoviesViewHolder>(MoviesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder.create(parent, onClickItem)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MoviesViewHolder(
        private val binding: MovieCardItemBinding,
        private val onClickItem: (Int) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mediaItem: MovieDomainModel) {
            binding.photo.load("https://image.tmdb.org/t/p/w185${mediaItem.posterPath}")
            binding.title.text = mediaItem.title
            binding.rate.text = mediaItem.voteAverage.toString()
            binding.root.setOnClickListener { onClickItem(mediaItem.id) }

        }


        companion object {
            fun create(
                parent: ViewGroup,
                onClickItem: (Int) -> Unit
            ): MoviesViewHolder {
                val binding =
                    MovieCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MoviesViewHolder(binding, onClickItem)
            }
        }
    }

}

class MoviesDiffCallback : DiffUtil.ItemCallback<MovieDomainModel>() {
    override fun areItemsTheSame(oldItem: MovieDomainModel, newItem: MovieDomainModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDomainModel, newItem: MovieDomainModel): Boolean {
        return oldItem == newItem
    }

}
