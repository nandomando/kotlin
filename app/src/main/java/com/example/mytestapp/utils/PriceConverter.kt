package com.example.mytestapp.utils

import androidx.room.TypeConverter

class PriceConverter {

    @TypeConverter
    fun toString(price: Int): String? {
        return Integer.toString(price)
    }

    @TypeConverter
    fun toInt(str: String?): Int? {
        return str?.toInt()
    }
}
