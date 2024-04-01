package com.example.lib_compose_sqlite.presentation.screens.books

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.R
import com.example.lib_compose_sqlite.presentation.AppViewModelProvider
import com.example.lib_compose_sqlite.presentation.components.Header


@Composable
fun BooksListScreen(
    navController: NavController,
    context: Context,
    route: String,
    viewModel: BookViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.bookUiState.collectAsState()
    Header(navController, "Books", route)
    { values ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(values),
        ) {
            items(items = homeUiState.bookItem) {bookItem ->
                val (title, author, bookType, status,bookId) = bookItem
                val booksStatus =
                    if (status == 0L) context.getString(R.string.available) else "\uD83D\uDD34 Reserved"

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

        }
    }
}
