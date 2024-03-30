package com.example.lib_compose_sqlite.presentation.screens.books

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.data.books.BookEvents
import com.example.lib_compose_sqlite.data.books.BookState


@Composable
fun BookScreenRoom(state: BookState, navController: NavController, onEvent: (BookEvents) -> Unit) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                state.bookTitle.value = ""
                state.bookAuthor.value = ""
                navController.navigate("AddBookScreenRoom")
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
        modifier = Modifier.padding(16.dp)
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.books.size) { index ->
                bookItem(state = state, index = index, onEvent = onEvent)

            }


        }
    }
}

@Composable
fun bookItem(
    state: BookState,
    index: Int,
    onEvent: (BookEvents) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth())
    {
        Column(Modifier.weight(1f))
        {
            Text(text = state.books[index].bookTitle, fontSize = 20.sp)
            Text(text = state.books[index].bookAuthor, fontSize = 16.sp)
            Text(text = "book.bookAutho")
        }
        IconButton(onClick =
        {
            onEvent(BookEvents.deleteBook(state.books[index]))
        })
        {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "DeleteBook")
        }
    }
}

@Composable
fun AddBookScreenRoom(
    navController: NavController,
    state: BookState,
    onEvent: (BookEvents) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(
                    BookEvents.SaveBook(
                        title = state.bookTitle.value,
                        author = state.bookTitle.value
                    )
                )
                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save")
            }
        },
        modifier = Modifier.padding(16.dp)
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Text(text = "Title")
            TextField(value = state.bookTitle.value, onValueChange = {
                state.bookTitle.value = it
            })
            Text(text = "Author")

            TextField(value = state.bookAuthor.value, onValueChange = {
                state.bookAuthor.value = it
            })

        }
    }

}