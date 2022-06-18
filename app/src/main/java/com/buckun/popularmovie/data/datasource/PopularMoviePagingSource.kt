package com.buckun.popularmovie.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.buckun.popularmovie.data.api.ApiServices
import com.buckun.popularmovie.data.response.PopularMovieResponseItem
import com.buckun.popularmovie.utils.Constant

class PopularMoviePagingSource constructor(
    private val apiServices: ApiServices
) : PagingSource<Int, PopularMovieResponseItem>() {

    override fun getRefreshKey(state: PagingState<Int, PopularMovieResponseItem>): Int {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovieResponseItem> {
        var pageIndex = 1
        if (params.key != null) {
            pageIndex = params.key!!
        }
        val response = apiServices.getPopularMovies(Constant.API_KEY, pageIndex)

        return if (response.results.isNotEmpty()) {
            pageIndex++
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = pageIndex
            )
        } else {
            LoadResult.Page(
                data = arrayListOf(),
                prevKey = null,
                nextKey = null
            )
        }
    }
}