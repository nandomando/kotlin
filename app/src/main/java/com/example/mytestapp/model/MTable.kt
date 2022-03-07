package com.example.mytestapp.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "tables_tbl")
data class MTable(
    @PrimaryKey
    var tableId: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "tbl_number")
    var number: String? = null,

    @ColumnInfo(name = "client_id")
    var client_id: String? = null,

    @Embedded(prefix = "dessert_")
    var desserts: MItemDessert? = null,

    @Embedded(prefix = "drinks_")
    var drinks: MItemDrinks? = null,

    @Embedded(prefix = "plats_")
    var plats: MItem? = null
){}
