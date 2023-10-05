package com.tasha.core

import android.app.Application
import android.util.Log
import com.pluto.Pluto
import com.pluto.plugins.exceptions.PlutoExceptions
import com.pluto.plugins.exceptions.PlutoExceptionsPlugin
import com.pluto.plugins.network.PlutoNetworkPlugin
import com.pluto.plugins.rooms.db.PlutoRoomsDBWatcher
import com.pluto.plugins.rooms.db.PlutoRoomsDatabasePlugin
import com.tasha.persistance.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Pluto.Installer(this)
            .addPlugin(PlutoExceptionsPlugin())
            .addPlugin(PlutoRoomsDatabasePlugin())
            .addPlugin(PlutoNetworkPlugin())
        .install()

        PlutoRoomsDBWatcher.watch("app_db", AppDatabase::class.java)
        PlutoExceptions.setExceptionHandler { thread, throwable ->
            Log.d("exception_demo", "uncaught exception handled on thread: " + thread.name, throwable)
        }
    }

}