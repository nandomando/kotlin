package com.example.mytestapp.utils

import androidx.room.TypeConverter
import com.example.mytestapp.model.MItemDessert
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ListModelConverter {
    @TypeConverter
    fun toModelList(modelListString: String?): List<MItemDessert>? {
        if (modelListString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<MItemDessert?>?>() {}.type
        return gson.fromJson<List<MItemDessert>>(modelListString, type)
    }
}