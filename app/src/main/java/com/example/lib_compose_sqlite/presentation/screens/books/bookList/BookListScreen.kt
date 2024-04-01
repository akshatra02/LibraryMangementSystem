package com.example.lib_compose_sqlite.presentation.screens.books.bookList

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.R
import com.example.lib_compose_sqlite.presentation.AppViewModelProvider
import com.example.lib_compose_sqlite.presentation.components.Header
import com.example.lib_compose_sqlite.presentation.screens.books.bookList.BookViewModel


@Composable
fun BooksListScreen(
    navController: NavController,
    context: Context,
    route: String,
    viewModel: BookViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.bookUiState.collectAsState()
    Header(navController, stringResource(id = R.string.books), route)
    { values ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(values),
        ) {
            if(homeUiState.bookItem.size != 0) {
                items(items = homeUiState.bookItem)
                { bookItem ->
                    val (title, author, bookType, status, bookId) = bookItem
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
                            text = stringResource(R.string.author_type, author, bookType)
                        )

                        Text(
                            text = booksStatus

                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                }
            }
            else{
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.no_books_are_in_the_library),
                            style = TextStyle(fontSize = 24.sp)
                        )
                    }
                }
            }

        }
    }
}
