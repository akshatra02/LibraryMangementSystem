package com.example.lib_compose_sqlite.presentation

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
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
import com.example.lib_compose_sqlite.BookStatus
import com.example.lib_compose_sqlite.BookType
import com.example.lib_compose_sqlite.data.DBHelper
import com.example.lib_compose_sqlite.ui.theme.LIB_COMPOSE_SQLITETheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(navController: NavController) {
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Admin") },
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
                    onClick = { navController.navigate(Screen.BooksScreen.route) },

                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Books",
                            style = TextStyle(fontSize = 24.sp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),

                    onClick = { navController.navigate(Screen.IssueBookScreen.route) },

                    ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Issue Book",
                            style = TextStyle(fontSize = 24.sp)

                        )
                    }               }
                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),

                    onClick = { navController.navigate(Screen.AddBookScreen.route) },

                    ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Add Book",
                            style = TextStyle(fontSize = 24.sp)

                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),

                    onClick = { navController.navigate(Screen.RemoveBookScreen.route) },

                    ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Remove Book",
                            style = TextStyle(fontSize = 24.sp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksScreen(navController: NavController,context: Context) {
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {Text(text = "Books")},
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Screen.AdminScreen.route){
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
                val dbHelper = DBHelper(context)
                runBlocking {
                    val viewBooks = dbHelper.getBooks()
                    val booksSize = viewBooks.size
                    if (booksSize > 0) {
                        items(booksSize) {
                            val book = viewBooks.get(it)
                            val booksStatus: String
                            if (book.status == BookStatus.Available) {
                                booksStatus = " \uD83D\uDFE2 ${book.status}"
                            } else {
                                booksStatus = " \uD83D\uDD34 ${book.status}"
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
                                    text = booksStatus

                                )
                                Spacer(modifier = Modifier.height(20.dp))
                            }
                            Spacer(modifier = Modifier.height(20.dp))

                        }
                    } else {
                        items(1) {
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
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IssueBookScreen(navController: NavController,context:Context){
    val dbHelper:DBHelper =DBHelper(context)
    var bookId by remember {
        mutableStateOf("")
    }
    var studentId by remember {
        mutableStateOf("")
    }
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = {Text(text = "Issue Book")},
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Screen.AdminScreen.route){
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
                    })}
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
                        .fillMaxWidth()
                )
                Text(text = "Enter Student Id: ")
                TextField(
                    value = studentId, onValueChange = {
                        studentId =it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Button(
                    onClick = {
                        try {

                            val issue_book = dbHelper.issuebook(bookId.toInt(), studentId.toInt())
                            when (issue_book) {
                                -1 -> {
                                    Toast.makeText(context, "Failed to issue the book. Please try again later.", Toast.LENGTH_LONG).show()
                                    navController.navigate(Screen.AdminScreen.route)
                                }

                                1 -> {
                                    Toast.makeText(context, "Student's Book limit exceeded!", Toast.LENGTH_LONG).show()
                                    navController.navigate(Screen.AdminScreen.route)
                                }

                                2 -> {
                                    Toast.makeText(
                                        context,
                                        "BOOK-$bookId issued to Student-$studentId  successfully!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    navController.navigate(Screen.AdminScreen.route)

                                }

                                3 -> {
                                    Toast.makeText(
                                        context,
                                        "Sorry Book-$bookId is already reserved!",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                }

                                else -> {
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

                }){
                    Text("Issue Book")
                }
            }

        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookScreen(navController: NavController, context: Context){
    val dbHelper:DBHelper =DBHelper(context)
    var bookId by remember {
        mutableStateOf("")
    }
    var booksTitle by remember {
        mutableStateOf("")
    }
    var booksAuthor by remember {
        mutableStateOf("")
    }
    var booksType by remember {
        mutableStateOf("")
    }
    val booksStatusValue = "Available"
    val booksStatus:BookStatus = BookStatus.valueOf(booksStatusValue)
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = {Text(text = "Add Book")},
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Screen.AdminScreen.route){
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
                    })}
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
                        .fillMaxWidth()
                )
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
                        try {
                            if (booksTypePresent) {
                                val booksTypeEntered = booksType.replaceFirstChar { it -> it.uppercaseChar() }
                                val bookType: BookType = BookType.valueOf(booksTypeEntered)
                                val newBook = Book(bookId.toInt(), booksTitle, booksAuthor, bookType, booksStatus)
                                addBook = dbHelper.addbook(newBook)
                            } else {
                                addBook = 0L
                            }
                            when(addBook){
                                0L -> Toast.makeText(context, "Sorry there is an issue! Kindly check the inputs.", Toast.LENGTH_LONG).show()
                                -1L -> Toast.makeText(context, "Book ID already exixts.", Toast.LENGTH_LONG).show()
                                else ->{Toast.makeText(context, "Book - $booksTitle added successfully!", Toast.LENGTH_LONG)
                                    .show()
                                    navController.navigate(Screen.AdminScreen.route)}
                            }
//                            if (addBook > 0) {
//                                Toast.makeText(context, "Book - $booksTitle added successfully!", Toast.LENGTH_LONG)
//                                    .show()
//                                navController.navigate(Screen.AdminScreen.route)
//                            }
//                            else {
//                                Toast.makeText(
//                                    context,
//                                    "Sorry there is an issue! Kindly check the inputs.",
//                                    Toast.LENGTH_LONG
//                                ).show()
////                            navController.navigate(Screen.AddBookScreen.route)
//                            }
                        }
                        catch (e: NumberFormatException){
                            Toast.makeText(
                                context,
                                "Book ID must be number!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        catch (e: Error){
                            Toast.makeText(
                                context,
                                "Book ID already exixts.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }){
                    Text("Add Book")
                }
            }

        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemoveBookScreen(navController: NavController, context: Context){
    val dbHelper:DBHelper =DBHelper(context)
    var bookId by remember {
        mutableStateOf("")
    }
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = {Text(text = "Remove Book")},
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Screen.AdminScreen.route){
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
                    })}
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
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        try {
                            if (dbHelper.removebook(bookId.toInt())) {
                                Toast.makeText(context, "Book - $bookId removed successfully!", Toast.LENGTH_LONG)
                                    .show()
                                navController.navigate(Screen.AdminScreen.route)
                            } else {
                                Toast.makeText(context, "Failed: Check Book ID", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                        catch (e: NumberFormatException){
                            Toast.makeText(
                                context,
                                "Book ID must be number!",
                                Toast.LENGTH_LONG
                            ).show()
                        }


                    }){
                    Text("Remove Book")
                }
            }

        }
    }
}