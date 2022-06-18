package com.buckun.popularmovie.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.buckun.popularmovie.data.response.PopularMovieResponseItem
import com.buckun.popularmovie.databinding.ItemPopularMovieLayoutBinding
import com.buckun.popularmovie.utils.executeWithAction

class PopularMovieViewHolder(private val binding: ItemPopularMovieLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(popularMovieResponseItem: PopularMovieResponseItem) {
        binding.executeWithAction {
            this.item = popularMovieResponseItem
        }
    }
}