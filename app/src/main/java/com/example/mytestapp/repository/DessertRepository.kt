package com.example.mytestapp.repository

import com.example.mytestapp.data.DessertDataBaseDao
import com.example.mytestapp.model.MItem
import com.example.mytestapp.model.MItemDessert
import com.example.mytestapp.model.MItemDrinks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DessertRepository @Inject constructor(private val dessertDataBaseDao: DessertDataBaseDao) {
    suspend fun addDessert(item: MItemDessert) = dessertDataBaseDao.insert(item)
    suspend fun updateDessert(item: MItemDessert) = dessertDataBaseDao.update(item)
    suspend fun deleteDessert(item: MItemDessert) = dessertDataBaseDao.deleteDessert(item)
    suspend fun deleteAllDesserts() = dessertDataBaseDao.deleteAll()

    fun getAllDessert(): Flow<List<MItemDessert>> = dessertDataBaseDao.getDesserts().flowOn(Dispatchers.IO)
        .conflate()
    fun getDessert(itemId: String): Flow<MItemDessert> = dessertDataBaseDao.getDessertById(itemId).flowOn(Dispatchers.IO)
        .conflate()

}