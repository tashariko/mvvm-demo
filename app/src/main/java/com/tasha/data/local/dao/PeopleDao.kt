package com.tasha.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tasha.data.local.entity.People

@Dao
interface PeopleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllItems(list: List<People>)

    @Insert
    fun addItem(vehicle: People)

    @Query("SELECT * from people")
    fun getItems(): List<People>

}