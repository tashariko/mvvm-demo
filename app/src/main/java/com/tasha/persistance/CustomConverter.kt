package com.tasha.persistance

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class CustomConverter {

    @TypeConverter
    fun storedLongToList(data: String?): ArrayList<String>? {
        val gson = Gson()
        if (data == null) {
            return ArrayList()
        }
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson<ArrayList<String>>(data, listType)
    }

    @TypeConverter
    fun listToStoredLong(myObjects: ArrayList<String>?): String? {
        val gson = Gson()
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return gson.toJson(myObjects, listType)
    }

}