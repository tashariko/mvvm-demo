package com.tasha.data.local.dao

import android.media.Image.Plane
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.tasha.data.local.entity.PPVTable
import com.tasha.data.local.entity.Planet
import com.tasha.data.local.entity.Vehicle

@Dao
interface PlanetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllItems(list: List<Planet>)

    @Insert
    fun addItem(vehicle: Planet)

    @Query("SELECT * from planet")
    fun getItems(): List<Planet>
}