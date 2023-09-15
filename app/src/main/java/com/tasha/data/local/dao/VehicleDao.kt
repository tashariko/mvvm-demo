package com.tasha.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tasha.data.local.entity.Vehicle

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllItems(list: List<Vehicle>)

    @Insert
    fun addItem(vehicle: Vehicle)

    @Query("SELECT * from vehicle")
    fun getItems(): List<Vehicle>

}