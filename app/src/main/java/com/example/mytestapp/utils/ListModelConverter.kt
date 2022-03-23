package com.example.mytestapp.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.room.TypeConverter
import com.example.mytestapp.model.MItem
import com.example.mytestapp.model.MItemDessert
import com.example.mytestapp.model.MItemDrinks
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow


class ListModelConverter {

    @TypeConverter
fun stringToItems(json: String?): SnapshotStateList<MItem>? {
    val gson = Gson()
    val type = object :
        TypeToken< SnapshotStateList<MItem?>?>() {}.type
    return gson.fromJson<SnapshotStateList<MItem>>(json, type)
}

@TypeConverter
fun itemsToString(list: SnapshotStateList<MItem?>?): String? {
    val gson = Gson()
    val type = object :
        TypeToken<SnapshotStateList<MItem?>?>() {}.type
    return gson.toJson(list, type)
}
/////////////////////////DESSERT////////////////////////
    @TypeConverter
    fun stringToDessert(json: String?): SnapshotStateList<MItemDessert>? {
        val gson = Gson()
        val type = object :
            TypeToken< SnapshotStateList<MItemDessert?>?>() {}.type
        return gson.fromJson<SnapshotStateList<MItemDessert>>(json, type)
    }

    @TypeConverter
    fun dessertToString(list: SnapshotStateList<MItemDessert?>?): String? {
        val gson = Gson()
        val type = object :
            TypeToken<SnapshotStateList<MItemDessert?>?>() {}.type
        return gson.toJson(list, type)
    }
/////////////////////////////DRINKS////////////////////////////////
    @TypeConverter
    fun stringToDrinks(json: String?): SnapshotStateList<MItemDrinks>? {
        val gson = Gson()
        val type = object :
            TypeToken< SnapshotStateList<MItemDrinks?>?>() {}.type
        return gson.fromJson<SnapshotStateList<MItemDrinks>>(json, type)
    }

    @TypeConverter
    fun drinksToString(list: SnapshotStateList<MItemDrinks?>?): String? {
        val gson = Gson()
        val type = object :
            TypeToken<SnapshotStateList<MItemDrinks?>?>() {}.type
        return gson.toJson(list, type)
    }


/////////////////////////////////////////////////////////////////////

//    @TypeConverter
//    fun listToJson(value: List<MItem>?) = Gson().toJson(value)
//
//    @TypeConverter
//    fun jsonToList(value: String) = Gson().fromJson(value, Array<MItem>::class.java).toList()

//
//    @TypeConverter
//    fun fromString(value: String?): List<MItem> {
//        val listType = object :
//            TypeToken<ArrayList<MItem?>?>() {}.type
//        return Gson().fromJson(value, listType)
//    }
//
//    @TypeConverter
//    fun fromList(list: List<MItem?>?): String {
//        val gson = Gson()
//        return gson.toJson(list)
//    }
}


