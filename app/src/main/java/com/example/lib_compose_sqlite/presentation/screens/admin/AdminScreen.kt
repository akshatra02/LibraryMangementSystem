package com.example.lib_compose_sqlite.presentation.screens.admin

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.R
//import com.example.lib_compose_sqlite.BookStatus
import com.example.lib_compose_sqlite.presentation.navigation.Screen
import com.example.lib_compose_sqlite.presentation.components.CardComponent
import com.example.lib_compose_sqlite.presentation.components.Header
import com.example.lib_compose_sqlite.presentation.screens.books.bookList.BooksListScreen

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
                stringResource(R.string.books)
            ) {
                navController.navigate(Screen.BooksScreen.route)
            }
            CardComponent(
                stringResource(R.string.issue_book)
            ) {
                navController.navigate(Screen.IssueBookScreen.route)
            }
            CardComponent(
                stringResource(R.string.add_book)
            ) {
                navController.navigate(Screen.AddBookScreen.route)
            }
            CardComponent(
                stringResource(R.string.remove_book)
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
