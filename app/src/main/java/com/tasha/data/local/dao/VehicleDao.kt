package com.tasha.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.tasha.data.local.entity.PPVTable
import com.tasha.data.local.entity.Vehicle

@Dao
public abstract class VehicleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addAllItems(list: List<Vehicle>)

    @Insert
    abstract fun addItem(vehicle: Vehicle)

    @Query("SELECT * from vehicle")
    abstract fun getItems(): List<Vehicle>

}