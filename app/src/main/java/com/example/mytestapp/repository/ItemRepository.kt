package com.example.mytestapp.repository

import android.content.ClipData
import com.example.mytestapp.data.ItemDatabaseDao
import com.example.mytestapp.model.MItem
import com.google.rpc.context.AttributeContext
import com.squareup.okhttp.Dispatcher
import io.grpc.internal.SharedResourceHolder
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

    fun getItem(itemId: String): Flow<MItem> = itemDatabaseDao.getItemsById(itemId).flowOn(Dispatchers.IO)
        .conflate()

//    fun getItem(itemId: String): MItem = itemDatabaseDao.getItemsById(itemId)


}


//suspend fun getBookInfo(bookId: String): Resource<Item> {
//    val response = try {
//        Resource.Loading(data = true)
//        api.getBookInfo(bookId)
//
//    }catch (exception: Exception){
//        return Resource.Error(message = "An error occurred ${exception.message.toString()}")
//    }
//    Resource.Loading(data = false)
//    return Resource.Success(data = response)
//}