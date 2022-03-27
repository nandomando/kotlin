package com.example.mytestapp.data

import androidx.room.*
import com.example.mytestapp.model.MItemDessert
import kotlinx.coroutines.flow.Flow

@Dao
interface DessertDataBaseDao {
    @Query(value = "SELECT * from items_dessert_tbl")
    fun getDesserts(): Flow<List<MItemDessert>>

    @Query(value = "SELECT * from items_dessert_tbl where dessertId =:id")
    fun getDessertById(id: String): Flow<MItemDessert>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: MItemDessert)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(item: MItemDessert)

    @Query(value = "DELETE from items_dessert_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteDessert(item: MItemDessert)
}