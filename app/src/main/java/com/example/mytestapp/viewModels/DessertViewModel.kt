package com.example.mytestapp.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestapp.model.MItem
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

    private  val _currentDessert = MutableStateFlow(MItemDessert())
    val currentDessert = _currentDessert.asStateFlow()


//    private val _dessertListMutable = mutableStateListOf<MItemDessert>()
//    val dessertListMutable = _dessertListMutable

    init {

        viewModelScope.launch(Dispatchers.IO){
            repository.getAllDessert().distinctUntilChanged()
                .collect { listOfDessert ->
                    if (listOfDessert.isNullOrEmpty()) {
                        Log.d("empty", "EMPTY DESSERT list ")
                    } else {
                        _dessertList.value = listOfDessert
//                        listOfDessert.forEach { item ->
//                            dessertListMutable.add(item)
//                        }
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

    fun getDessert(itemId: String) = viewModelScope.launch(Dispatchers.IO){
        repository.getDessert(itemId).distinctUntilChanged().collect {
            item ->
            _currentDessert.value = item
        }
    }


}