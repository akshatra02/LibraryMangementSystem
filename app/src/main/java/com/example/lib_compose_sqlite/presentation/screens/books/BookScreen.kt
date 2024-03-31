package com.example.lib_compose_sqlite.presentation.screens.books

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.data.local.books.BookEntity
import com.example.lib_compose_sqlite.data.local.books.BookState
import com.example.lib_compose_sqlite.presentation.AppViewModelProvider
import androidx.compose.foundation.lazy.items



//@Composable
//fun BookScreenRoom(
//    navigateToBookAdd: () -> Unit,
//    navController: NavController,
//    viewModel: BookViewModel = viewModel(
//        factory = AppViewModelProvider.Factory
//    )
//) {
//    val homeUiState by viewModel.bookUiState.collectAsState()
//    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(onClick = navigateToBookAdd) {
//                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
//            }
//        },
//        modifier = Modifier.padding(16.dp)
//    ) { paddingValues ->
//        LazyColumn(
//            contentPadding = paddingValues,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            val onEvent: (BookEvents) -> Unit = {}
//            items(items = homeUiState.bookItem) { book ->
//                bookItem(
//                    state = book,
//                    bookDelete = {viewModel.deleteBook(viewModel.bookUiState.value.bookItem[book.bookId])}
//                )
//
//            }
//
//
//        }
//    }
//}

@Composable
fun bookItem(
    state: BookEntity,
    bookDelete: () -> Unit ={}
) {
    Row(modifier = Modifier.fillMaxWidth())
    {
        Column(Modifier.weight(1f))
        {
            Text(text = state.bookTitle, fontSize = 20.sp)
            Text(text = state.bookAuthor, fontSize = 16.sp)
            Text(text = "book.bookAutho")
        }
        IconButton(onClick =bookDelete)
        {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "DeleteBook")
        }
    }
}

