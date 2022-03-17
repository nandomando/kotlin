package com.example.mytestapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mytestapp.model.MItem
import com.example.mytestapp.model.MItemDessert
import com.example.mytestapp.model.MItemDrinks
import com.example.mytestapp.model.MTable
import com.example.mytestapp.utils.ListModelConverter
import com.example.mytestapp.utils.PriceConverter
import com.example.mytestapp.utils.UUIDConverter

@Database(entities = [MItem::class, MItemDessert::class, MItemDrinks::class, MTable::class], version = 18, exportSchema =false )
@TypeConverters(UUIDConverter::class, PriceConverter::class, ListModelConverter::class)
abstract class ItemDataBase: RoomDatabase() {

    abstract fun itemDao(): ItemDatabaseDao
    abstract fun dessertDao(): DessertDataBaseDao
    abstract fun drinksDao(): DrinksDataBaseDao
    abstract fun tableDao(): TableDataBaseDao
}