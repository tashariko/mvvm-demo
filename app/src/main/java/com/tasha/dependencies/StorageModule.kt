package com.tasha.dependencies

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.tasha.data.local.dao.PeopleDao
import com.tasha.data.local.dao.PlanetDao
import com.tasha.data.local.dao.VehicleDao
import com.tasha.persistance.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class StorageModule {
    @Provides
    @Singleton
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun providesRoomDB(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_db").build()
    }

    @Provides
    @Singleton
    fun getVehicleDb(appDatabase: AppDatabase): VehicleDao {
        return appDatabase.getVehicleDao()
    }

    @Provides
    @Singleton
    fun getPlanetDb(appDatabase: AppDatabase): PlanetDao {
        return appDatabase.getPlanetDao()
    }

    @Provides
    @Singleton
    fun getPeopleDao(appDatabase: AppDatabase): PeopleDao {
        return appDatabase.getPeopleDao()
    }
}