package com.mungaicodes.tomesanctuary.data.mapper

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {

    @TypeConverter
    fun fromListToString(list: List<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToList(value: String?): List<String> {
        val listType = object :
            TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }
}
