package com.buckun.popularmovie.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.buckun.popularmovie.R
import com.buckun.popularmovie.databinding.ActivityDetailsBinding
import com.buckun.popularmovie.ui.viewmodel.DetailsViewModel
import com.buckun.popularmovie.utils.Constant
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var poster_path  = intent.getStringExtra("poster_path")
        var original_title  = intent.getStringExtra("original_title")
        var original_language  = intent.getStringExtra("original_language")
        var first_air_date  = intent.getStringExtra("first_air_date")
        var overview  = intent.getStringExtra("overview")

        var new_poster_path = Constant.IMAGE_URL_START + poster_path

        Glide.with(binding.root.context)
            .load(new_poster_path)
            .into(binding.detailsImageView)
        binding.name.text = original_title
        binding.originalLanguage.text = original_language
        binding.firstAirDate.text = first_air_date
        binding.overview.text = overview

    }
}