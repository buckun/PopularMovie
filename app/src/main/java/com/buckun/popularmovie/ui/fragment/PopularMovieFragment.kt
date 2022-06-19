package com.buckun.popularmovie.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.buckun.popularmovie.R
import com.buckun.popularmovie.base.BaseFragment
import com.buckun.popularmovie.databinding.FragmentPopularMovieBinding
import com.buckun.popularmovie.ui.viewmodel.NetworkStateViewModel
import com.buckun.popularmovie.ui.adapter.PopularMoviePagingAdapter
import com.buckun.popularmovie.utils.NetworkListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PopularMovieFragment : BaseFragment<FragmentPopularMovieBinding>()  {
    override fun getLayoutId(): Int = R.layout.fragment_popular_movie

    @Inject
    lateinit var popularMoviePagingAdapter: PopularMoviePagingAdapter
    private lateinit var networkListener: NetworkListener
    private val networkStateViewModel: NetworkStateViewModel by viewModels()
    private val viewModel: PopularMovieViewModel by viewModels()

    override fun prepareView(savedInstanceState: Bundle?) {
        initAdapter()
        pagingObserve()
        initPagingAdapterLoadStateListener()
    }

    private fun initAdapter(){
        binding.popularMoviesRecyclerView.adapter = popularMoviePagingAdapter
    }

    private fun initPagingAdapterLoadStateListener(){
        popularMoviePagingAdapter.addLoadStateListener { combinedLoadStates->
            if (combinedLoadStates.refresh is LoadState.Loading ){
                Log.i("buckun","loading")
            } else {
                Log.i("buckun","notLoading")
            }
        }
    }

    private fun pagingObserve() {
        lifecycleScope.launch {
            viewModel.getPopularMovies().collectLatest { response ->
                popularMoviePagingAdapter.submitData(response)
            }
        }
        lifecycleScope.launch {
            networkListener = NetworkListener()
            context?.let {
                networkListener.checkNetworkAvailability(it).collect { status ->
                    networkStateViewModel.networkStatus = status
                    networkStateViewModel.showNetworkStatus()
                }
            }
        }

        /*lifecycleScope.launch {
            viewModel.getFirebase().collectLatest { response ->
                Log.i("buckun","responese "+ response)
            }
        }*/
    }
}