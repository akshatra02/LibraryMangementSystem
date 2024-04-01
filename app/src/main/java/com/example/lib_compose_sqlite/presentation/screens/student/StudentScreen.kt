package com.example.lib_compose_sqlite.presentation.screens.student

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
fun StudentScreen(navController: NavController ,studentId:Int){
    Header(navController,"Student", Screen.HomeScreen.route)
    { values ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .padding(values),
            ) {
                CardComponent(
                    stringResource(R.string.all_books)
                ) {
                    navController.navigate("${Screen.StudentBooksScreen.route}/$studentId")
                }
                CardComponent(
                    stringResource(R.string.my_books)

                ) {
                    navController.navigate("${Screen.StudentMyBookScreen.route}/$studentId")
                }
                CardComponent(
                    stringResource(R.string.return_book)

                ) {
                    navController.navigate("${Screen.ReturnBookScreen.route}/$studentId")
                }
            }
        }
    }
@Composable
fun StudentBooksScreen(navController: NavController, context: Context,studentId:Int){
    BooksListScreen(navController,context,"${Screen.StudentScreen.route}/$studentId")
}