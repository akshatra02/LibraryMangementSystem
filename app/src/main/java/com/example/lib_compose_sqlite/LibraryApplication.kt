package com.example.lib_compose_sqlite

import android.app.Application
import com.example.lib_compose_sqlite.data.local.AppContainer
import com.example.lib_compose_sqlite.data.local.AppDataContainer

class LibraryApplication: Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppDataContainer(this)
    }
}