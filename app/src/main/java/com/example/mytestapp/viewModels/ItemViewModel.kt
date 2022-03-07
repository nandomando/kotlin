package com.example.mytestapp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestapp.model.MItem
import com.example.mytestapp.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor( private val repository: ItemRepository): ViewModel() {
  private val _itemList = MutableStateFlow<List<MItem>>(emptyList())
    val itemList = _itemList.asStateFlow()

    init {

        viewModelScope.launch(Dispatchers.IO){
            repository.getAllItem().distinctUntilChanged()
                .collect { listOfItems ->
                    if (listOfItems.isNullOrEmpty()) {
                        Log.d("empty", "empty list ")
                    } else {
                        _itemList.value = listOfItems
                    }
                }
        }
    }

     fun addItem(item: MItem ) = viewModelScope.launch {
        repository.addItem(item)
    }

     fun updateItem(item: MItem) = viewModelScope.launch {
        repository.updateItem(item)
    }

     fun removeItem(item: MItem) = viewModelScope.launch {
        repository.deleteItem(item)
    }

}
