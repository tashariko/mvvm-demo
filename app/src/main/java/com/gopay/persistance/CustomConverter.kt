package com.gopay.persistance

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class CustomConverter {

    @TypeConverter
    fun storedLongToList(data: String): ArrayList<Long> {
        val gson = Gson()
        if (data == null) {
            return ArrayList()
        }
        val listType = object : TypeToken<ArrayList<Long>>() {}.type
        return gson.fromJson<ArrayList<Long>>(data, listType)
    }

    @TypeConverter
    fun listToStoredLong(myObjects: ArrayList<Long>): String {
        val gson = Gson()
        val listType = object : TypeToken<ArrayList<Long>>() {}.type
        return gson.toJson(myObjects, listType)
    }

}