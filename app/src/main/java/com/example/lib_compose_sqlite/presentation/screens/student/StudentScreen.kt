package com.example.lib_compose_sqlite.presentation.screens.student

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.domain.BookReturnStatus
//import com.example.lib_compose_sqlite.BookStatus
import com.example.lib_compose_sqlite.data.DBHelper
import com.example.lib_compose_sqlite.presentation.navigation.Screen
import com.example.lib_compose_sqlite.presentation.components.CardComponent
import com.example.lib_compose_sqlite.presentation.components.Header
import com.example.lib_compose_sqlite.presentation.screens.books.BooksListScreen
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
                    "All Books"
                ) {
                    navController.navigate("${Screen.StudentBooksScreen.route}/$studentId")
                }
                CardComponent(
                    "My Book"
                ) {
                    navController.navigate("${Screen.StudentMyBookScreen.route}/$studentId")
                }
                CardComponent(
                    "Return Book"
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