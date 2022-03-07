package com.example.mytestapp.data

import androidx.room.*
import com.example.mytestapp.model.MItem
import com.example.mytestapp.model.MItemDrinks
import kotlinx.coroutines.flow.Flow

@Dao
interface DrinksDataBaseDao {

    @Query(value = "SELECT * from items_drinks_tbl")
    fun getDrinks(): Flow<List<MItemDrinks>>

    @Query(value = "SELECT * from items_drinks_tbl where drinksId =:id")
    suspend fun getDrinksById(id: String): MItemDrinks

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: MItemDrinks)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(item: MItemDrinks)

    @Query(value = "DELETE from items_drinks_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteDrink(item: MItemDrinks)
}