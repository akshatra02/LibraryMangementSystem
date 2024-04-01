package com.example.lib_compose_sqlite

import android.app.Application
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.lib_compose_sqlite.data.local.AppContainer
import com.example.lib_compose_sqlite.data.local.AppDataContainer

class LibraryApplication: Application() {
    lateinit var appContainer: AppContainer
//    val context = LocalContext

    override fun onCreate() {
        super.onCreate()
        appContainer = AppDataContainer(this)
    }
}