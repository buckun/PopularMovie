package com.buckun.popularmovie.ui.activity

import android.os.Bundle
import com.buckun.popularmovie.R
import com.buckun.popularmovie.base.BaseActivity
import com.buckun.popularmovie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun prepareView(savedInstanceState: Bundle?) {}

}