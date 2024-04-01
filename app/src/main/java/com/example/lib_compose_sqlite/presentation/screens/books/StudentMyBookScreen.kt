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
import com.example.lib_compose_sqlite.data.DBHelper
import com.example.lib_compose_sqlite.presentation.AppViewModelProvider
import com.example.lib_compose_sqlite.presentation.components.Header
import com.example.lib_compose_sqlite.presentation.navigation.Screen
import kotlinx.coroutines.runBlocking


@Composable
fun StudentMyBookScreen(
    navController: NavController,
    context: Context,
    studentId: Int,
    viewModel: StudentMyBookViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val myBookUiState by viewModel.getStudentBooks(studentId.toLong()).collectAsState()

    Header(navController, "My Books", "${Screen.StudentScreen.route}/$studentId")
    { values ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(values),
        ) {
            items(items = myBookUiState.bookItem) {book ->
                val ( title, author, bookType, status,bookId) = book
                val bookStatus =
                    if (status == 0L) "\uD83D\uDFE2 Available" else "\uD83D\uDD34 Reserved"
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
                        text = bookStatus
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

    }
}
