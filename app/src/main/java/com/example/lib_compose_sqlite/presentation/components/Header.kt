package com.example.lib_compose_sqlite.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.presentation.theme.LIB_COMPOSE_SQLITETheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(navController: NavController,title: String, route: String,content : (PaddingValues) ->Unit) {
    LIB_COMPOSE_SQLITETheme {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = { Text(text = title) },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(route) {
                                popUpTo(route) {
                                    inclusive = true
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }) { values ->
            content(values)
        }
    }
}