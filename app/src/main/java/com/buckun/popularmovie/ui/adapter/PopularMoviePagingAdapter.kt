package com.buckun.popularmovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.buckun.popularmovie.data.response.PopularMovieResponseItem
import com.buckun.popularmovie.databinding.ItemPopularMovieLayoutBinding
import javax.inject.Inject

class PopularMoviePagingAdapter
@Inject
constructor() :
    PagingDataAdapter<PopularMovieResponseItem, PopularMovieViewHolder>(
        DiffUtils
    ) {

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        val popularMovieItem = getItem(position)
        if (popularMovieItem != null) {
            holder.bind(popularMovieItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        return PopularMovieViewHolder(
            ItemPopularMovieLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    object DiffUtils : DiffUtil.ItemCallback<PopularMovieResponseItem>() {
        override fun areItemsTheSame(
            oldItem: PopularMovieResponseItem,
            newItem: PopularMovieResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PopularMovieResponseItem,
            newItem: PopularMovieResponseItem
        ): Boolean {
            return oldItem == newItem
        }

    }
}