package com.example.mytestapp.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestapp.data.DataOrException
import com.example.mytestapp.model.FireBaseItem
import com.example.mytestapp.model.MItem
import com.example.mytestapp.repository.FireRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirebaseViewModel @Inject constructor(
    private val repository: FireRepository): ViewModel() {

    val data: MutableState<DataOrException<List<FireBaseItem>, Boolean, Exception>>
    = mutableStateOf(DataOrException(listOf(), true, Exception("")))

    init {
        getAllItemsFromDatabase()
    }

    private fun getAllItemsFromDatabase() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getAllItemsFromDatabase()
            if (!data.value.data.isNullOrEmpty()) data.value.loading = false
        }
        Log.d("GET", "getAllItemsFromDatabase: ${data.value.data?.toList().toString()}")
    }


}