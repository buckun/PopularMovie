package com.buckun.popularmovie.data.datasource

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class FirebaseStoreSource constructor(
    private val firebaseDatabase: FirebaseDatabase
)  {

    fun getValue() : String? {
        var result: String? = null
        val myRef = firebaseDatabase.getReference("etstur")
        myRef.setValue("ETSTUR")

        // Read from the database
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<String>()
                Log.d("TAG", "Value is: " + value)
                result = value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }

        })
        return result
    }
}