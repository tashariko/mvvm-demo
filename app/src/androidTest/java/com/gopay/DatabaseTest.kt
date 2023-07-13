package com.gopay

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gopay.data.local.dao.VehicleDao
import com.gopay.data.local.entity.Vehicle
import com.gopay.persistance.AppDatabase
import junit.framework.TestCase
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest: TestCase() {
    private lateinit var vehicleDao: VehicleDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
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
        val language = Vehicle(111, "2 Years")
        vehicleDao.addItem(language)
        val languages = vehicleDao.getItems()
        Assert.assertTrue(languages.contains(language))
    }
}