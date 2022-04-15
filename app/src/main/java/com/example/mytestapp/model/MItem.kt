package com.example.mytestapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.auth.FirebaseAuth
import java.util.*


@Entity(tableName = "items_tbl")
data class MItem(
    @PrimaryKey
    var plateId: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "userId")
    var userId: String? = null,

    @ColumnInfo(name = "item_name")
    var name: String? = null,

    @ColumnInfo(name = "item_description")
    var description: String? = null,

    @ColumnInfo(name = "item_price")
    var price: String? = null,

    @ColumnInfo(name = "item_boolean")
    var send: Boolean = false
) {}





