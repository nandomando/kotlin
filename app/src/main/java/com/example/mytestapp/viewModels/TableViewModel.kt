package com.example.mytestapp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestapp.model.MItem
import com.example.mytestapp.model.MTable
import com.example.mytestapp.repository.TableRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TableViewModel @Inject constructor(private val repository: TableRepository): ViewModel(){
    private val _tableList = MutableStateFlow<List<MTable>>(emptyList())
    val tableList = _tableList.asStateFlow()

//    private val _tableItemList = MutableStateFlow<List<MItem>>(emptyList())
//    val tableItemList = _tableItemList.asStateFlow()

    init {

        viewModelScope.launch(Dispatchers.IO){
            repository.getAllTables().distinctUntilChanged()
                .collect { listOfTables ->
                    if (listOfTables.isNullOrEmpty()) {
                        Log.d("empty", "empty list ")
                    } else {
                        _tableList.value = listOfTables
                    }
                }
        }

        //////
    }

    fun addTable(table: MTable) = viewModelScope.launch {
        repository.addTable(table)
    }


    fun updateTable(table: MTable) = viewModelScope.launch {
        repository.updateTable(table)
    }
    ///////////////////////////////
//    fun addItem(item: MItem) = viewModelScope.launch {
//        repository.addItem(item)
//    }

    /////////////////

    fun removeTable(table: MTable) = viewModelScope.launch {
        repository.deleteTable(table)
    }

    fun getTableById(string: String) = viewModelScope.launch {
        repository.getTableById(string)
    }

}