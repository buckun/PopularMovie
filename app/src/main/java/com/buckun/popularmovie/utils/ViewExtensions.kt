package com.buckun.popularmovie.utils
import android.widget.ImageView
import com.buckun.popularmovie.R
import com.bumptech.glide.Glide

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this.context)
        .load(imageUrl)
        .error(R.drawable.ic_launcher_background)
        .into(this)
}