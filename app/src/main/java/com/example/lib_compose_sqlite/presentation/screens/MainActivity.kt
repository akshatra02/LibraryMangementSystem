package com.example.lib_compose_sqlite.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.lib_compose_sqlite.presentation.theme.LIB_COMPOSE_SQLITETheme
import com.example.lib_compose_sqlite.presentation.navigation.LibraryNavHost

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LIB_COMPOSE_SQLITETheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    LibraryNavHost()
                }
            }
        }
    }
}
