package com.example.mytestapp.repository

import com.example.mytestapp.data.DrinksDataBaseDao
import com.example.mytestapp.model.MItem
import com.example.mytestapp.model.MItemDrinks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DrinksRepository @Inject constructor(private val drinksDataBaseDao: DrinksDataBaseDao) {
    suspend fun addDrink(item: MItemDrinks) = drinksDataBaseDao.insert(item)
    suspend fun updateDrink(item: MItemDrinks) = drinksDataBaseDao.update(item)
    suspend fun deleteDrink(item: MItemDrinks) = drinksDataBaseDao.deleteDrink(item)
    suspend fun deleteAllDrinks() = drinksDataBaseDao.deleteAll()


    fun getAllDrinks(): Flow<List<MItemDrinks>> = drinksDataBaseDao.getDrinks().flowOn(Dispatchers.IO)
        .conflate()

    fun getDrink(itemId: String): Flow<MItemDrinks> = drinksDataBaseDao.getDrinksById(itemId).flowOn(Dispatchers.IO)
        .conflate()


}