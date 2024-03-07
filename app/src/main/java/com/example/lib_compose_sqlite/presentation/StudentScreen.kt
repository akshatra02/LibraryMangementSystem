package com.example.lib_compose_sqlite.presentation

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.BookStatus
import com.example.lib_compose_sqlite.data.DBHelper
import com.example.lib_compose_sqlite.ui.theme.LIB_COMPOSE_SQLITETheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentScreen(navController: NavController){
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Student") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Screen.HomeScreen.route) {
                                popUpTo(Screen.HomeScreen.route) {
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
            }
        ) { values ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .padding(values),
//                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    onClick = { navController.navigate(Screen.StudentBooksScreen.route) },

                    ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "All Books",
                            style = TextStyle(fontSize = 24.sp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),

                    onClick = { navController.navigate(Screen.StudentMyBookScreen.route) },

                    ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "My Book",
                            style = TextStyle(fontSize = 24.sp)

                        )
                    }               }
                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),

                    onClick = { navController.navigate(Screen.SearchBookScreen.route) },

                    ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Search Book",
                            style = TextStyle(fontSize = 24.sp)

                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentBooksScreen(navController: NavController, context: Context){
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {Text(text = "Books")},
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Screen.StudentScreen.route){
                                popUpTo(Screen.AdminScreen.route){
                                    inclusive = true
                                }
                            }
                        }){
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }
        ){values ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(values),
            ) {
                var dbhelper = DBHelper(context)
                val viewbooks = dbhelper.getBooks()
                val books_size = viewbooks.size
                items(books_size) {
                    val book = viewbooks.get(it)
                    val bookstatus: String
                    if (book.status == BookStatus.Available) {
                        bookstatus = " \uD83D\uDFE2 ${book.status}"
                    } else {
                        bookstatus = " \uD83D\uDD34 ${book.status}"
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "${book.bookId} - ${book.title}",
                            style = TextStyle(fontSize = 24.sp)
                        )
                        Text(
                            text = "Author: ${book.author} - Type: ${book.bookType} "
                        )

                        Text(
                            text = "$bookstatus"

                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentMyBookScreen(navController: NavController,context: Context){
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "My Books") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Screen.StudentScreen.route) {
                                popUpTo(Screen.StudentScreen.route) {
                                    inclusive = true
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "Go Back"
                            )
                        }
                    }
                )
            }
        )

         {
            values ->
            Column(
                modifier = Modifier
                    .padding(values),
            ) {
                val dbHelper:DBHelper = DBHelper(context)
//                val view_my_books = dbHelper.getmyBooks()
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "My books will be represented",
                        style = TextStyle(fontSize = 24.sp)
                    )
                }

                }
            }
        }
    }
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBookScreen(navController: NavController,context: Context){
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Search Books") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Screen.StudentScreen.route) {
                                popUpTo(Screen.StudentScreen.route) {
                                    inclusive = true
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "Go Back"
                            )
                        }
                    }
                )
            }
        )

        {
                values ->
            Column(
                modifier = Modifier
                    .padding(values),
            ) {
                val dbHelper:DBHelper = DBHelper(context)
//                val view_my_books = dbHelper.getmyBooks()
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = " ",
                        style = TextStyle(fontSize = 24.sp)
                    )
                }

            }
        }
    }
}