package com.example.mytestapp.utils

import androidx.room.TypeConverter
import com.example.mytestapp.model.MItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ListModelConverter {
    @TypeConverter
    fun stringToItems(json: String?): MutableList<MItem>? {
        val gson = Gson()
        val type = object :
            TypeToken<MutableList<MItem?>?>() {}.type
        return gson.fromJson<MutableList<MItem>>(json, type)
    }

    @TypeConverter
    fun itemsToString(list: MutableList<MItem?>?): String? {
        val gson = Gson()
        val type = object :
            TypeToken<MutableList<MItem?>?>() {}.type
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