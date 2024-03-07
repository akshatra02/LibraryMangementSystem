package com.example.lib_compose_sqlite.presentation

import android.content.Context
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lib_compose_sqlite.Book
import com.example.lib_compose_sqlite.BookStatus
import com.example.lib_compose_sqlite.BookType
import com.example.lib_compose_sqlite.Greeting
import com.example.lib_compose_sqlite.data.DBHelper
import com.example.lib_compose_sqlite.ui.theme.LIB_COMPOSE_SQLITETheme
import kotlinx.coroutines.GlobalScope


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(navController: NavController) {
    var text by remember {
        mutableStateOf("")
    }
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
fun IssueBookScreen(navController: NavController,context:Context){
    val dbHelper:DBHelper =DBHelper(context)
    var book_id by remember {
        mutableStateOf("")
    }
    var student_id by remember {
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
                    value = book_id, onValueChange = {
                        book_id =it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(text = "Enter Student Id: ")
                TextField(
                    value = student_id, onValueChange = {
                        student_id =it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Button(
                    onClick = {

                        val issue_book = dbHelper.issuebook(book_id.toInt(),student_id.toInt())
                        when(issue_book){

                            1 -> Toast.makeText(context, "Student's Book limit exceeded!", Toast.LENGTH_LONG).show()
                            2-> Toast.makeText(context, "BOOK-$book_id issued to Student-$student_id  successfully!", Toast.LENGTH_LONG).show()
                            3-> Toast.makeText(context, "Sorry Book-$book_id is already reserved!", Toast.LENGTH_LONG).show()
                        }
                        navController.navigate(Screen.AdminScreen.route)

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
    var book_id by remember {
        mutableStateOf("")
    }
    var book_title by remember {
        mutableStateOf("")
    }
    var book_author by remember {
        mutableStateOf("")
    }
    var book_type by remember {
        mutableStateOf("")
    }
    val bookstatus = "Available"
    val book_status:BookStatus = BookStatus.valueOf(bookstatus)
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
                Text(text = "Enter Book Id: ")
                TextField(
                    value = book_id, onValueChange = {
                        book_id =it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(text = "Enter Book Title: ")
                TextField(
                    value = book_title, onValueChange = {
                        book_title =it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(text = "Enter Book Author: ")
                TextField(
                    value = book_author, onValueChange = {
                        book_author =it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(text = "Enter Book Type: ")
                TextField(
                    value = book_type, onValueChange = {
                        book_type =it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                val book_type_list : List<String> = listOf("Fiction","Biography","Historic","Magazine","Journal")
                val booktype_isthere = book_type_list.contains(book_type)
                var add_book:Long
                if (booktype_isthere) {
                    val bookType: BookType = BookType.valueOf(book_type)
                    val new_book: Book = Book(book_id.toInt(), book_title, book_author, bookType, book_status)
                    add_book = dbHelper.addbook(new_book)
                }
                else{
                    add_book = -1
                }
                Button(
                    onClick = {
                        if (add_book > 0) {
                            Toast.makeText(context, "Book - $book_title added successfully!", Toast.LENGTH_LONG).show()
                            navController.navigate(Screen.AdminScreen.route)
                        }
                        else{
                            Toast.makeText(context, "Sorry there is an issue! Kindly check the inputs.", Toast.LENGTH_LONG).show()
                            navController.navigate(Screen.AddBookScreen.route)
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
    var book_id by remember {
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
                Text(text = "Enter Book Id: ")
                TextField(
                    value = book_id, onValueChange = {
                        book_id =it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Button(
                    onClick = {
                            if (dbHelper.removebook(book_id)) {
                                Toast.makeText(context, "Book - $book_id removed successfully!", Toast.LENGTH_LONG)
                                    .show()
                                navController.navigate(Screen.AdminScreen.route)
                            } else {
                                Toast.makeText(context, "Failed: Check Book ID", Toast.LENGTH_LONG)
                                    .show()
                                navController.navigate(Screen.RemoveBookScreen.route)
                            }


                    }){
                    Text("Remove Book")
                }
            }

        }
    }
}