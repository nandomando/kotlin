package com.example.mytestapp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestapp.model.MItemDessert
import com.example.mytestapp.repository.DessertRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DessertViewModel  @Inject constructor(private val repository: DessertRepository): ViewModel(){
    private val _dessertList = MutableStateFlow<List<MItemDessert>>(emptyList())
    val dessertList = _dessertList.asStateFlow()

    init {

        viewModelScope.launch(Dispatchers.IO){
            repository.getAllDessert().distinctUntilChanged()
                .collect { listOfDessert ->
                    if (listOfDessert.isNullOrEmpty()) {
                        Log.d("empty", "empty list ")
                    } else {
                        _dessertList.value = listOfDessert
                    }
                }
        }
    }

    fun addDessert(item: MItemDessert) = viewModelScope.launch {
        repository.addDessert(item)
    }

    fun updateDessert(item: MItemDessert) = viewModelScope.launch {
        repository.updateDessert(item)
    }

    fun removeDessert(item: MItemDessert) = viewModelScope.launch {
        repository.deleteDessert(item)
    }

}