package com.buckun.popularmovie.ui.fragment

import androidx.lifecycle.ViewModel
import com.buckun.popularmovie.data.datasource.FirebaseStoreSource
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
)  : ViewModel(){

    fun getDateBaseValue(): Flow<String?> {
        return FirebaseStoreSource(firebaseDatabase).getDateBaseValue()
    }

}