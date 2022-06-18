package com.buckun.popularmovie.data.api

import com.buckun.popularmovie.data.response.PopularMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): PopularMovieResponse
}