package com.example.lib_compose_sqlite.presentation.screens.books

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.presentation.AppViewModelProvider
import com.example.lib_compose_sqlite.presentation.components.Header
import com.example.lib_compose_sqlite.presentation.navigation.Screen


@Composable
fun IssueBookScreen(
    navController: NavController,
    context: Context,
    viewModel: IssueBookViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val bookUiState = viewModel.bookUiState
    val studentUiState = viewModel.studentUiState
    Header(navController, "Issue Book", Screen.AdminScreen.route)
    { values ->
        Column(
            modifier = Modifier
                .padding(values)
        ) {
            Text(text = "Enter Book Id: ")
            TextField(
                value = if (bookUiState.id != 0 )bookUiState.id.toString() else "",
                onValueChange = {
                    viewModel.updateBookUiState(bookUiState.copy(id = it.toIntOrNull() ?: 0))
                },
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            Text(text = "Enter Student Id: ")
            TextField(
                value = if (studentUiState.id != 0L)studentUiState.id.toString() else "", onValueChange = {
                    viewModel.updateStudentUiState(studentUiState.copy(id = it.toLongOrNull() ?: 0L))
                },
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)

            )
            Button(
                onClick = {
                    viewModel.issueBook(navController,context)
                }) {
                Text("Issue Book")
            }
        }

    }
}
