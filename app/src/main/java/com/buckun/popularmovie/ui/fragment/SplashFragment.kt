package com.buckun.popularmovie.ui.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.buckun.popularmovie.R
import com.buckun.popularmovie.base.BaseFragment
import com.buckun.popularmovie.databinding.FragmentSplashBinding
import com.buckun.popularmovie.ui.viewmodel.NetworkStateViewModel
import com.buckun.popularmovie.utils.NetworkListener
import com.google.firebase.database.DatabaseReference
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    private lateinit var networkListener: NetworkListener
    private val networkStateViewModel: NetworkStateViewModel by viewModels()
    private val splashViewModel: SplashViewModel by viewModels()
    private lateinit var database: DatabaseReference


    override fun getLayoutId(): Int = R.layout.fragment_splash

    override fun prepareView(savedInstanceState: Bundle?) {
        networkObserve()
    }

    private fun networkObserve() {

        lifecycleScope.launch {
            networkListener = NetworkListener()
            context?.let {
                networkListener.checkNetworkAvailability(it).collect { status ->
                    networkStateViewModel.networkStatus = status
                    networkStateViewModel.showNetworkStatus()
                    if (status) {
                        splashViewModel.getDateBaseValue().collectLatest { response ->
                            binding.textView.text = response
                            lifecycleScope.launch(Dispatchers.Main) {
                                delay(3000)
                                findNavController().navigate(R.id.popularMovieFragment)
                            }
                        }
                    } else {
                        binding.textView.text = "No Internet Connection"
                    }

                }
            }
        }
    }
}