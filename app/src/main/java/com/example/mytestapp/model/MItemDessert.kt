package com.example.mytestapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "items_dessert_tbl")
data class MItemDessert(
    @PrimaryKey
    var dessertId: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "item_dessert_name")
    var name: String? = null,

    @ColumnInfo(name = "item_dessert_description")
    var description: String? = null,

    @ColumnInfo(name = "item_dessert_price")
    var price: String? = null,
){}
