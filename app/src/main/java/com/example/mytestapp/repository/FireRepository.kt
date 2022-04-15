package com.example.mytestapp.repository

import android.util.Log
import com.example.mytestapp.data.DataOrException
import com.example.mytestapp.model.FireBaseItem
import com.example.mytestapp.model.MItem
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class FireRepository@Inject constructor(
    private val queryItem: Query
) {
    suspend fun getAllItemsFromDatabase(): DataOrException<List<FireBaseItem>, Boolean, Exception>{
        val dataOrException = DataOrException<List<FireBaseItem>, Boolean, Exception>()

        try {
            dataOrException.loading = true
            dataOrException.data = queryItem.get().await().documents.map { documentSnapshot ->
                Log.d("REPOSITORY", "REPOSITORY: $documentSnapshot")
                documentSnapshot.toObject(FireBaseItem::class.java)!!
            }
            if(!dataOrException.data.isNullOrEmpty()) dataOrException.loading = false

        }catch (exception: FirebaseFirestoreException){
            dataOrException.e = exception
        }
        return dataOrException
    }
}