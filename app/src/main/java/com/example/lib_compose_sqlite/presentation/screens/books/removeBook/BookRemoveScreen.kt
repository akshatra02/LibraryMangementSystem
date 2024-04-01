package com.example.lib_compose_sqlite.presentation.screens.books.removeBook

import android.content.Context
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.R
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
    Header(navController, stringResource(id = R.string.remove_book), Screen.AdminScreen.route)
    { values ->
        Column(
            modifier = Modifier
                .padding(values)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = stringResource(id = R.string.enter_book_id))
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
                Text(stringResource(id = R.string.remove_book))
            }
        }

    }
}