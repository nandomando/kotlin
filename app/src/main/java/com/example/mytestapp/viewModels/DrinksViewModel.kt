package com.example.mytestapp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestapp.model.MItemDrinks
import com.example.mytestapp.repository.DrinksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DrinksViewModel @Inject constructor(private val repository: DrinksRepository): ViewModel() {
    private val _drinksList = MutableStateFlow<List<MItemDrinks>>(emptyList())
    val drinksList = _drinksList.asStateFlow()

    init {

        viewModelScope.launch(Dispatchers.IO){
            repository.getAllDrinks().distinctUntilChanged()
                .collect { listOfItems ->
                    if (listOfItems.isNullOrEmpty()) {
                        Log.d("empty", "empty list ")
                    } else {
                        _drinksList.value = listOfItems
                    }
                }
        }
    }

    fun addDrink(item: MItemDrinks) = viewModelScope.launch {
        repository.addDrink(item)
    }

    fun updateDrink(item: MItemDrinks) = viewModelScope.launch {
        repository.updateDrink(item)
    }

    fun removeDrink(item: MItemDrinks) = viewModelScope.launch {
        repository.deleteDrink(item)
    }

}