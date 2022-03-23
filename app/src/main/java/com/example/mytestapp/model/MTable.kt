package com.example.mytestapp.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*
import kotlin.collections.ArrayList


@Entity(tableName = "tables_tbl")
data class MTable(
    @PrimaryKey
    var tableId: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "tbl_number")
    var number: String? = null,

    @ColumnInfo(name = "client_id")
    var client_id: String? = null,

    @ColumnInfo(name = "dessert_")
    var desserts: SnapshotStateList<MItemDessert> = mutableStateListOf(),

    @ColumnInfo(name = "drinks_")
    var drinks: SnapshotStateList<MItemDrinks> = mutableStateListOf(),

    @ColumnInfo(name = "plats_")
    var plats: SnapshotStateList<MItem> = mutableStateListOf()

){}

