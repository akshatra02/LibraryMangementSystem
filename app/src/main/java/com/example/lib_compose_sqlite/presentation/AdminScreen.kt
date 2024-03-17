package com.example.lib_compose_sqlite.presentation

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lib_compose_sqlite.Book
import com.example.lib_compose_sqlite.BookIssueStatus
//import com.example.lib_compose_sqlite.BookStatus
import com.example.lib_compose_sqlite.BookType
import com.example.lib_compose_sqlite.data.DBHelper
import com.example.lib_compose_sqlite.presentation.components.BooksList
import com.example.lib_compose_sqlite.presentation.components.CardComponent
import com.example.lib_compose_sqlite.presentation.components.Header
import com.example.lib_compose_sqlite.ui.theme.LIB_COMPOSE_SQLITETheme
import kotlinx.coroutines.*
@Composable
fun AdminScreen(navController: NavController) {
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                Header(navController,"Admin",Screen.HomeScreen.route)
            }
        ) { values ->
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
                    navController.navigate(Screen.RemoveBookScreen.route)
                }
            }
            }
        }
    }

@Composable
fun BooksScreen(navController: NavController,context: Context) {
    BooksList(navController,context,Screen.AdminScreen.route)
}

@Composable
fun IssueBookScreen(navController: NavController,context:Context){
    val dbHelper:DBHelper =DBHelper(context)
    var bookId by remember {
        mutableStateOf("")
    }
    var studentId by remember {
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Header(navController,"Issue Book",Screen.AdminScreen.route)
            }

                ) {
            values->
            Column(
                modifier = Modifier
                    .padding(values)
            ) {
                Text(text = "Enter Book Id: ")
                TextField(
                    value = bookId, onValueChange = {
                        bookId =it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                Text(text = "Enter Student Id: ")
                TextField(
                    value = studentId, onValueChange = {
                        studentId =it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)

                )
                Button(
                    onClick = {
                        coroutineScope.launch {
                        try {

                            val issueBook = dbHelper.issueBook(bookId.toInt(), studentId.toInt())
                            when (issueBook) {
                                BookIssueStatus.Failed -> {
                                    Toast.makeText(context, "Failed to issue the book. Please try again later.", Toast.LENGTH_LONG).show()
                                    navController.navigate(Screen.AdminScreen.route)
                                }

                                BookIssueStatus.StudentLimitExceeded -> {
                                    Toast.makeText(context, "Student's Book limit exceeded!", Toast.LENGTH_LONG).show()
                                    navController.navigate(Screen.AdminScreen.route)
                                }

                                BookIssueStatus.Successful -> {
                                    Toast.makeText(
                                        context,
                                        "BOOK-$bookId issued to Student-$studentId  successfully!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    navController.navigate(Screen.AdminScreen.route)

                                }

                                BookIssueStatus.AlreadyReserved -> {
                                    Toast.makeText(
                                        context,
                                        "Sorry Book-$bookId is already reserved!",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                }

                                BookIssueStatus.InvalidInput -> {
                                    Toast.makeText(
                                        context,
                                        "Please enter valid Book id and student id",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                }

                            }
                        }
                        catch (e: NumberFormatException){
                            Toast.makeText(
                                context,
                                "Book ID and Student ID must be number!",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                }
            }){
                    Text("Issue Book")
                }
            }

        }
    }

}
@Composable
fun AddBookScreen(navController: NavController, context: Context){
    val dbHelper:DBHelper =DBHelper(context)
    var booksTitle by remember {
        mutableStateOf("")
    }
    var booksAuthor by remember {
        mutableStateOf("")
    }
    var booksType by remember {
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Header(navController,"Add Book",Screen.AdminScreen.route)
            }
        ) {
                values->
            Column(
                modifier = Modifier
                    .padding(values)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Enter Book Title: ")
                TextField(
                    value = booksTitle, onValueChange = {
                        booksTitle =it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(text = "Enter Book Author: ")
                TextField(
                    value = booksAuthor, onValueChange = {
                        booksAuthor =it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(text = "Enter Book Type: ")
                TextField(
                    value = booksType, onValueChange = {
                        booksType =it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))

                val booksTypeList : List<String> = listOf("fiction","biography","historic","magazine","journal")
                val booksTypePresent = booksTypeList.contains(booksType.lowercase())
                var addBook:Long
                Button(
                    onClick = {
                        coroutineScope.launch {

                            try {
                                if (booksTypePresent) {
                                    val booksTypeEntered = booksType.replaceFirstChar { it -> it.uppercaseChar() }
                                    val bookType = BookType.valueOf(booksTypeEntered)
                                    addBook = dbHelper.addBook(booksTitle, booksAuthor, bookType)
                                } else {
                                    addBook = 0L
                                }
                                if (addBook == 0L) {
                                    Toast.makeText(
                                        context,
                                        "Sorry there is an issue! Kindly check the inputs.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else {
                                    Toast.makeText(context, "Book - $booksTitle added successfully!", Toast.LENGTH_LONG)
                                        .show()
                                    navController.navigate(Screen.AdminScreen.route)
                                }

                            }
                            catch (e: Error) {
                                Toast.makeText(
                                    context,
                                    "Failed to add book.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }){
                    Text("Add Book")
                }
            }

        }
    }
}
@Composable
fun RemoveBookScreen(navController: NavController, context: Context){
    val dbHelper:DBHelper =DBHelper(context)
    var bookId by remember {
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Header(navController,"Remove Book",Screen.AdminScreen.route)
            }
        ) {
                values->
            Column(
                modifier = Modifier
                    .padding(values)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Enter Book Id: ")
                TextField(
                    value = bookId, onValueChange = {
                        bookId =it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)

                )
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        coroutineScope.launch {
                            try {
                                if (dbHelper.removeBook(bookId.toInt())) {
                                    Toast.makeText(context, "Book - $bookId removed successfully!", Toast.LENGTH_LONG)
                                        .show()
                                    navController.navigate(Screen.AdminScreen.route)
                                } else {
                                    Toast.makeText(context, "Failed: Check Book ID", Toast.LENGTH_LONG)
                                        .show()
                                }
                            } catch (e: Exception) {
                                Toast.makeText(
                                    context,
                                    "Failed to remove book!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }


                        }
                    }){
                    Text("Remove Book")
                }
            }

        }
    }
}