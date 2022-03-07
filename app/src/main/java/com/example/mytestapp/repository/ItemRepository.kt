package com.example.mytestapp.repository

import com.example.mytestapp.data.ItemDatabaseDao
import com.example.mytestapp.model.MItem
import com.squareup.okhttp.Dispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ItemRepository @Inject constructor(private val itemDatabaseDao: ItemDatabaseDao) {
    suspend fun addItem(item: MItem) = itemDatabaseDao.insert(item)
    suspend fun updateItem(item: MItem) = itemDatabaseDao.update(item)
    suspend fun deleteItem(item: MItem) = itemDatabaseDao.deleteItem(item)
    suspend fun deleteAllItems() = itemDatabaseDao.deleteAll()
    fun getAllItem(): Flow<List<MItem>> = itemDatabaseDao.getItems().flowOn(Dispatchers.IO)
        .conflate()

}