package com.buckun.popularmovie.ui.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.buckun.popularmovie.data.response.PopularMovieResponseItem
import com.buckun.popularmovie.databinding.ItemPopularMovieLayoutBinding
import com.buckun.popularmovie.ui.activity.DetailsActivity
import com.buckun.popularmovie.utils.executeWithAction

class PopularMovieViewHolder(private val binding: ItemPopularMovieLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(popularMovieResponseItem: PopularMovieResponseItem) {
        binding.executeWithAction {
            this.item = popularMovieResponseItem
        }
        binding.popularMovieContainer.setOnClickListener {
            val i = Intent(binding.root.context, DetailsActivity::class.java)
            i.putExtra("poster_path",popularMovieResponseItem.poster_path)
            i.putExtra("original_title",popularMovieResponseItem.original_title)
            i.putExtra("original_language",popularMovieResponseItem.original_language)
            i.putExtra("release_date",popularMovieResponseItem.release_date)
            i.putExtra("overview",popularMovieResponseItem.overview)
            binding.root.context.startActivity(i);
        }
    }
}