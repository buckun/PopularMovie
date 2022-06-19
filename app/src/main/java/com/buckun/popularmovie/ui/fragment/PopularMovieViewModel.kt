package com.buckun.popularmovie.ui.fragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.buckun.popularmovie.data.api.ApiServices
import com.buckun.popularmovie.data.datasource.FirebaseStoreSource
import com.buckun.popularmovie.data.datasource.PopularMoviePagingSource
import com.buckun.popularmovie.data.response.PopularMovieResponseItem
import com.buckun.popularmovie.utils.Constant.NETWORK_PAGE_SIZE
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(
    private val apiServices: ApiServices,
    private val firebaseDatabase: FirebaseDatabase) : ViewModel() {

    fun getPopularMovies(): Flow<PagingData<PopularMovieResponseItem>> {
        return Pager(config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { PopularMoviePagingSource(apiServices) }
        ).flow
    }

    fun getFirebase(): Flow<String>  {
        return flow {FirebaseStoreSource(firebaseDatabase).getValue()}
    }

    fun getValue() {
        val myRef = firebaseDatabase.getReference("etstur")
        myRef.setValue("")

        // Read from the database
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<String>()
                Log.d("TAG", "Value is: " + value)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }

        })
    }
}