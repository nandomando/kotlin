package com.example.mytestapp.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestapp.model.MItem
import com.example.mytestapp.model.MTable
import com.example.mytestapp.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor( private val repository: ItemRepository): ViewModel() {
  private val _itemList = MutableStateFlow<List<MItem>>(emptyList())
    val itemList = _itemList.asStateFlow()

    private val _currentItem = MutableStateFlow(MItem())
    val currentItem = _currentItem.asStateFlow()


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

    fun getItem(itemId: String) = viewModelScope.launch(Dispatchers.IO){
        repository.getItem(itemId).distinctUntilChanged().collect {
            item ->
            _currentItem.value = item
        }
    }

}

//fun findProduct(itemId: String) {
//    coroutineScope.launch(Dispatchers.Main) {
//        searchResults.value = asyncFind(itemId).await()
//    }
//}
//
//private fun asyncFind(itemId: String): Deferred<MItem?> =
//    coroutineScope.async(Dispatchers.IO) {
//        return@async  productDao.findProduct(itemId)
//    }
//val searchResults = MutableLiveData<MItem>()
//
//private val coroutineScope = CoroutineScope(Dispatchers.Main)
