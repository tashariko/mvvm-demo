package com.gopay.screens.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.gopay.data.local.dao.VehicleDao
import com.gopay.data.local.entity.Vehicle
import com.gopay.persistance.AppDatabase
import junit.framework.TestCase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
class VehicleLocalDataSourceTest: TestCase() {
    private lateinit var vehicleDao: VehicleDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).allowMainThreadQueries().build()
        vehicleDao = db.getVehicleDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val vehicle = Vehicle(111, "2 Years")
        vehicleDao.addItem(vehicle)
        val vehicles = vehicleDao.getItems()
        Assert.assertTrue(vehicles.contains(vehicle))
    }
}