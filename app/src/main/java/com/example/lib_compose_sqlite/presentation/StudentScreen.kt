package com.example.lib_compose_sqlite.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.BookReturnStatus
//import com.example.lib_compose_sqlite.BookStatus
import com.example.lib_compose_sqlite.data.DBHelper
import com.example.lib_compose_sqlite.presentation.components.BooksList
import com.example.lib_compose_sqlite.presentation.components.CardComponent
import com.example.lib_compose_sqlite.presentation.components.Header
import com.example.lib_compose_sqlite.ui.theme.LIB_COMPOSE_SQLITETheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun StudentScreen(navController: NavController ,studentId:Int){
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                Header(navController,"Student",Screen.HomeScreen.route)
            }
        ) { values ->
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
}
@Composable
fun StudentBooksScreen(navController: NavController, context: Context,studentId:Int){
    BooksList(navController,context,"${Screen.StudentScreen.route}/$studentId")
}
@Composable
fun StudentMyBookScreen(navController: NavController,context: Context,studentId: Int) {
    val dbHelper = DBHelper(context)
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            topBar = {
                Header(navController,"My Books","${Screen.StudentScreen.route}/$studentId")
            }
        )
        { values ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(values),
            ) {
                runBlocking {
                    dbHelper.getStudentMyBook(studentId).collect { viewBooks ->
                        val booksSize = viewBooks.size
                        if (booksSize > 0) {
                            items(booksSize) {
                                val book = viewBooks[it]
                                val (bookId, title, author, bookType, status) = book
                                val bookStatus =
                                    if (status == 0) "\uD83D\uDFE2 Available" else "\uD83D\uDD34 Reserved"
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = "${bookId} $booksSize- ${title}",
                                        style = TextStyle(fontSize = 24.sp)
                                    )
                                    Text(
                                        text = "Author: ${author} - Type: ${bookType} "
                                    )
                                    Text(
                                        text = bookStatus
                                    )
                                    Spacer(modifier = Modifier.height(20.dp))
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                            }
                        }else {
                            items(1) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(30.dp)
                                ) {
                                    Text(
                                        text = "No books are reserved yet!",
                                        style = TextStyle(fontSize = 24.sp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ReturnBookScreen(navController: NavController,context: Context,studentId: Int){
    var bookid by remember{
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            topBar = {
                Header(navController,"Return Book","${Screen.StudentScreen.route}/$studentId")
            }
        )

        { values ->
            Column(
                modifier = Modifier
                    .padding(values),
            ) {
                    Text(
                        text = "Enter Book Id : ",
                        style = TextStyle(fontSize = 24.sp)
                    )
                TextField(
                    value = bookid,
                    onValueChange = {
                        bookid = it
                    },
                    modifier = Modifier .fillMaxWidth() ,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                Button(
                    onClick = {
                        coroutineScope.launch {
                            val dbHelper = DBHelper(context)
                            val returnbook = dbHelper.returnBook(bookid.toInt(), studentId)
                            when (returnbook) {
                                BookReturnStatus.WrongBookId -> {
                                    Toast.makeText(context, "Entered Wrong Book Id.", Toast.LENGTH_LONG).show()
                                }

                                BookReturnStatus.Successful -> {
                                    Toast.makeText(
                                        context,
                                        "Book = $bookid is returned successfully",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    navController.navigate("${Screen.StudentScreen.route}/$studentId") {
                                        popUpTo("${Screen.StudentScreen.route}/$studentId")
                                    }
                                }

                                BookReturnStatus.NoBookReserved -> {
                                    Toast.makeText(
                                        context,
                                        "No books are reserved to return!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    navController.navigate("${Screen.StudentScreen.route}/$studentId") {
                                        popUpTo("${Screen.StudentScreen.route}/$studentId")
                                    }
                                }

                                BookReturnStatus.Failed -> Toast.makeText(
                                    context,
                                    "Failed to return the book. Please try again later.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                )
                {
                    Text(text = "Return Book")
                }

            }
        }
    }
}