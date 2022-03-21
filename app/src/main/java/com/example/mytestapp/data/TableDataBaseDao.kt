package com.example.mytestapp.data

import androidx.room.*
import com.example.mytestapp.model.MItem
import com.example.mytestapp.model.MTable
import kotlinx.coroutines.flow.Flow


@Dao
interface TableDataBaseDao {
    @Query(value = "SELECT * from tables_tbl")
    fun getTables(): Flow<List<MTable>>

    @Query(value = "SELECT * from tables_tbl where tableId =:id")
    suspend fun getTableById(id: String): MTable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(table: MTable)
 /////////////////////////
//    @Query(value = "SELECT * from tables_tbl where plats_")
//    fun getPlats(): Flow<List<MItem>>


///////////////
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertItem(item: MItem)

    /////////////////////

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(table: MTable)

    @Query(value = "DELETE from tables_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteTable(table: MTable)
}