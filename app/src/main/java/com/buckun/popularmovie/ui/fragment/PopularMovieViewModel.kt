package com.buckun.popularmovie.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.buckun.popularmovie.data.api.ApiServices
import com.buckun.popularmovie.data.datasource.PopularMoviePagingSource
import com.buckun.popularmovie.data.response.PopularMovieResponseItem
import com.buckun.popularmovie.utils.Constant.NETWORK_PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(
    private val apiServices: ApiServices,
) : ViewModel() {
    fun getPopularMovies(): Flow<PagingData<PopularMovieResponseItem>> {
        return Pager(config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { PopularMoviePagingSource(apiServices) }
        ).flow
    }
}