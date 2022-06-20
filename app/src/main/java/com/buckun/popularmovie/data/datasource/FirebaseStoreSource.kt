package com.buckun.popularmovie.data.datasource

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow

class FirebaseStoreSource constructor(
    private val firebaseDatabase: FirebaseDatabase
)  {

    fun getDateBaseValue() : Flow<String?> = callbackFlow {
        var result: String? = null
        val myRef = firebaseDatabase.getReference("value")
        myRef.setValue("ETSTUR")

        // Read from the database
        val test = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<String>()
                result = value.toString()
                trySend(result)
            }

            override fun onCancelled(error: DatabaseError) {
                trySend("Not read firebase database")
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        }
        myRef.addValueEventListener(test)
        awaitClose{ myRef.removeEventListener(test)}
    }
}