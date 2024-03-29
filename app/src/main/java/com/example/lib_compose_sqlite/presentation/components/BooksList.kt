package com.example.lib_compose_sqlite.presentation.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.data.DBHelper
import com.example.lib_compose_sqlite.presentation.theme.LIB_COMPOSE_SQLITETheme
import kotlinx.coroutines.runBlocking

@Composable
fun BooksList(navController:NavController, context: Context, route:String) {

    Header(navController,"Books",route)
    { values ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(values),
            ) {
                try {
                    runBlocking {
                        val dbHelper = DBHelper(context)
                        dbHelper.getBooks().collect { viewBooks ->
                            val booksSize = viewBooks.size
                            if (booksSize > 0) {
                                items(booksSize) {
                                    val book = viewBooks[it]
                                    val (bookId, title, author, bookType, status) = book
                                    val booksStatus =
                                        if (status == 0) "\uD83D\uDFE2 Available" else "\uD83D\uDD34 Reserved"

                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "${bookId} - ${title}",
                                            style = TextStyle(fontSize = 24.sp)
                                        )
                                        Text(
                                            text = "Author: ${author} - Type: ${bookType} "
                                        )

                                        Text(
                                            text = booksStatus

                                        )
                                        Spacer(modifier = Modifier.height(20.dp))
                                    }
                                    Spacer(modifier = Modifier.height(20.dp))

                                }
                            } else {
                                item {
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "No books are in the Library.",
                                            style = TextStyle(fontSize = 24.sp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                catch (e : Exception){
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                        ){
                            Text(
                                text = "There is an issue!",
                                style = TextStyle(fontSize = 24.sp)
                            )
                        }
                    }
                }
            }
        }
    }
