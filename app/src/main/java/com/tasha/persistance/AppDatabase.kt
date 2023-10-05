package com.tasha.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tasha.data.local.dao.PPVDao
import com.tasha.data.local.dao.PeopleDao
import com.tasha.data.local.dao.PlanetDao
import com.tasha.data.local.dao.VehicleDao
import com.tasha.data.local.entity.PPVTable
import com.tasha.data.local.entity.People
import com.tasha.data.local.entity.Planet
import com.tasha.data.local.entity.Vehicle

@Database(entities = [Vehicle::class, Planet::class, People::class,PPVTable::class], version = 1, exportSchema = false)
@TypeConverters(CustomConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getVehicleDao(): VehicleDao
    abstract fun getPlanetDao(): PlanetDao
    abstract fun getPeopleDao(): PeopleDao
    abstract fun getPPVDao(): PPVDao
}