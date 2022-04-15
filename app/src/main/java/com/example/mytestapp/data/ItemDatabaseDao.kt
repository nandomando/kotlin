package com.example.mytestapp.data

import androidx.room.*
import com.example.mytestapp.model.MItem
import kotlinx.coroutines.flow.Flow


@Dao
interface ItemDatabaseDao {
    @Query(value = "SELECT * from items_tbl")
    fun getItems(): Flow<List<MItem>>

    @Query(value = "SELECT * from items_tbl where plateId =:id")
    fun getItemsById(id: String): Flow<MItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: MItem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(item: MItem)

    @Query(value = "DELETE from items_tbl")
    suspend fun deleteAll()

    @Delete()
    suspend fun deleteItem(item: MItem)
}
