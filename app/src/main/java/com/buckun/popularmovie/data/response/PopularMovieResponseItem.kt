package com.buckun.popularmovie.data.response

data class PopularMovieResponseItem(
    val id: Int?,
    val title: String?,
    val original_language: String?,
    val poster_path: String?,
    val popularity:String?,
    val original_title:String?,
    val overview:String?,
    val vote_average:String?,
    val first_air_date:String?,
    val release_date:String?
)