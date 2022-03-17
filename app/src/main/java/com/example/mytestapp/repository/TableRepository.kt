package com.example.mytestapp.repository

import com.example.mytestapp.data.TableDataBaseDao
import com.example.mytestapp.model.MItem
import com.example.mytestapp.model.MTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TableRepository @Inject constructor(private val tableDataBaseDao: TableDataBaseDao) {
    suspend fun addTable(table: MTable) = tableDataBaseDao.insert(table)
    suspend fun updateTable(table: MTable) = tableDataBaseDao.update(table)
    suspend fun deleteTable(table: MTable) = tableDataBaseDao.deleteTable(table)

//    suspend fun addItem(item: MItem) = tableDataBaseDao.insertItem(MItem())
    suspend fun getTableById(table: String) = tableDataBaseDao.getTableById(table)
    suspend fun deleteAllTables() = tableDataBaseDao.deleteAll()
    fun getAllTables(): Flow<List<MTable>> = tableDataBaseDao.getTables().flowOn(
        Dispatchers.IO)
        .conflate()
}