package com.example.lib_compose_sqlite.presentation

import android.content.Context
import android.widget.Adapter
import android.widget.Toast
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.lib_compose_sqlite.BookStatus
import com.example.lib_compose_sqlite.data.DBHelper
import com.example.lib_compose_sqlite.ui.theme.LIB_COMPOSE_SQLITETheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.contracts.Returns

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentScreen(navController: NavController ,studentId:Int){
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
                    onClick = { navController.navigate("${Screen.StudentBooksScreen.route}/$studentId") },

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

                    onClick = {
                        navController.navigate("${Screen.StudentMyBookScreen.route}/$studentId")
                              },

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

                    onClick = {
                        navController.navigate("${Screen.ReturnBookScreen.route}/$studentId")
                              },

                    ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Return Book ",
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
fun StudentBooksScreen(navController: NavController, context: Context,studentId:Int){
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {Text(text = "Books")},
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate("${Screen.StudentScreen.route}/$studentId"){
                                popUpTo("${Screen.StudentScreen.route}/$studentId"){
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
                runBlocking {
                    val dbHelper = DBHelper(context)
                    val viewBooks = dbHelper.getBooks()
                    val booksSize = viewBooks.size
                    if (booksSize > 0) {
//                        delay(3000)
                        items(booksSize) {
                            val book = viewBooks.get(it)
                            val bookStatus: String
                            if (book.status == BookStatus.Available) {
                                bookStatus = " \uD83D\uDFE2 ${book.status}"
                            } else {
                                bookStatus = " \uD83D\uDD34 ${book.status}"
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
                                    text = bookStatus

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
                                    .height(30.dp)
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
fun StudentMyBookScreen(navController: NavController,context: Context,studentId: Int) {
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "My Books") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate("${Screen.StudentScreen.route}/$studentId") {
                                popUpTo("${Screen.StudentScreen.route}/$studentId") {
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

        { values ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(values),
            ) {
                runBlocking {
                    val dbHelper = DBHelper(context)
                    val viewBooks = dbHelper.get_student_my_book(studentId).collect{viewBooks ->
                    val booksSize = viewBooks.size
                    if (booksSize > 0) {
                        items(booksSize) {
                            val book = viewBooks.get(it)
                            val bookStatus: String
                            if (book.status == BookStatus.Available) {
                                bookStatus = " \uD83D\uDFE2 ${book.status}"
                            } else {
                                bookStatus = " \uD83D\uDD34 ${book.status}"
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
                                    text = bookStatus

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReturnBookScreen(navController: NavController,context: Context,studentId: Int){
    var bookid by remember{
        mutableStateOf("")
    }
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Return Books") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate("${Screen.StudentScreen.route}/$studentId") {
                                popUpTo("${Screen.StudentScreen.route}/$studentId") {
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
                    Text(
                        text = "Enter Book Id : ",
                        style = TextStyle(fontSize = 24.sp)
                    )
                TextField(
                    value = bookid,
                    onValueChange = {
                        bookid = it
                    },
                    modifier = Modifier .fillMaxWidth()
                )
                Button(
                    onClick = {
                        val dbHelper = DBHelper(context)
                        try {
                            val returnbook = dbHelper.return_book(bookid.toInt(), studentId)
                            when (returnbook) {
                                0 -> {
                                    Toast.makeText(context, "Entered Wrong Book Id.", Toast.LENGTH_LONG).show()
                                }
                                -2 ->{
                                    Toast.makeText(
                                        context,
                                        "No books are reserved to return!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    navController.navigate("${Screen.StudentScreen.route}/$studentId") {
                                        popUpTo("${Screen.StudentScreen.route}/$studentId")
                                    }
                                }

                                1 -> {
                                    Toast.makeText(
                                        context,
                                        "Book = $bookid is returned successfully",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    navController.navigate("${Screen.StudentScreen.route}/$studentId") {
                                        popUpTo("${Screen.StudentScreen.route}/$studentId")
                                    }
                                }

                                else -> Toast.makeText(context, "Failed to return the book. Please try again later.", Toast.LENGTH_LONG).show()
                            }

                        }
                        catch (e: NumberFormatException){
                            Toast.makeText(
                                context,
                                "Book ID must be number!",
                                Toast.LENGTH_LONG
                            ).show()
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