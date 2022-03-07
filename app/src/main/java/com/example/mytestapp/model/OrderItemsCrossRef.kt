//package com.example.mytestapp.model
//
//import androidx.room.Embedded
//import androidx.room.Entity
//import androidx.room.Junction
//import androidx.room.Relation
//
//@Entity(primaryKeys = ["tableId", "platsId", "dessertId", "drinksId"])
//data class OrderItemsCrossRef(
//    val tableId: String?,
//    val platsId: String? = null,
//    val dessertId: String? = null,
//    val drinksId: String? = null
//
//)
//data class TableWithItems(
//    @Embedded val table: MTable,
//    @Relation(
//        parentColumn = "tableId",
//        entityColumn = "plateId", "dessertId",
//        associateBy = Junction(OrderItemsCrossRef::class)
//    )
//    val songs: List<MItem>
//)