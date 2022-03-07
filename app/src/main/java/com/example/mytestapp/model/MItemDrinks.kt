package com.example.mytestapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "items_drinks_tbl")
data class MItemDrinks(
    @PrimaryKey
    var drinksId: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "item_drinks_name")
    var name: String? = null,

    @ColumnInfo(name = "item_drinks_description")
    var description: String? = null,

    @ColumnInfo(name = "item_drinks_price")
    var price: String? = null,
){}
