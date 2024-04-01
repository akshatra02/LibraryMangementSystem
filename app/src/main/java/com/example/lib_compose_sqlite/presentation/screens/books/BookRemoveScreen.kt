package com.example.lib_compose_sqlite.presentation.screens.books

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.data.local.books.toBook
import com.example.lib_compose_sqlite.presentation.AppViewModelProvider
import com.example.lib_compose_sqlite.presentation.components.Header
import com.example.lib_compose_sqlite.presentation.navigation.Screen


@Composable
fun BookRemoveScreen(
    navController: NavController,
    context: Context,
    viewModel: BookRemoveViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    )
) {
    val bookDetails = viewModel.bookUiState.bookDetails
    Header(navController, "Remove Book", Screen.AdminScreen.route)
    { values ->
        Column(
            modifier = Modifier
                .padding(values)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Enter Book Id: ")
            TextField(
                value =if (bookDetails.id !=0) bookDetails.id.toString() else "",
                onValueChange = {
                    viewModel.updateUiState(bookDetails.copy(id = it.toIntOrNull() ?: 0))
                },
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)

            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    viewModel.removeBook(navController, context, viewModel.bookUiState.bookDetails.id)
                }) {
                Text("Remove Book")
            }
        }

    }
}