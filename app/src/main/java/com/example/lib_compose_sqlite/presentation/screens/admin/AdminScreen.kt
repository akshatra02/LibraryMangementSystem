package com.example.lib_compose_sqlite.presentation.screens.admin

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.domain.BookIssueStatus
//import com.example.lib_compose_sqlite.BookStatus
import com.example.lib_compose_sqlite.domain.BookType
import com.example.lib_compose_sqlite.data.DBHelper
import com.example.lib_compose_sqlite.presentation.AppViewModelProvider
import com.example.lib_compose_sqlite.presentation.navigation.Screen
import com.example.lib_compose_sqlite.presentation.components.CardComponent
import com.example.lib_compose_sqlite.presentation.components.Header
import com.example.lib_compose_sqlite.presentation.screens.books.BookAddViewModel
import com.example.lib_compose_sqlite.presentation.screens.books.BooksListScreen
import kotlinx.coroutines.*

@Composable
fun AdminScreen(navController: NavController) {
    Header(navController, "Admin", Screen.HomeScreen.route)
    { values ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .padding(values),
        ) {
            CardComponent(
                "Books"
            ) {
                navController.navigate(Screen.BooksScreen.route)
            }
            CardComponent(
                "Issue Book"
            ) {
                navController.navigate(Screen.IssueBookScreen.route)
            }
            CardComponent(
                "Add Book"
            ) {
                navController.navigate(Screen.AddBookScreen.route)
            }
            CardComponent(
                "Remove Book"
            ) {
                navController.navigate(Screen.BookRemoveScreen.route)
            }
        }
    }
}

@Composable
fun BooksScreen(navController: NavController, context: Context) {
    BooksListScreen(navController, context, Screen.AdminScreen.route)
}
