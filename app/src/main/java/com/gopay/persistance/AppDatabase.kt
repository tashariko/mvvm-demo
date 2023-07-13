package com.gopay.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.gopay.data.local.dao.VehicleDao
import com.gopay.data.local.entity.Vehicle

@Database(entities = [Vehicle::class], version = 1, exportSchema = false)
@TypeConverters(CustomConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getVehicleDao(): VehicleDao
}