package com.example.lib_compose_sqlite.presentation.screens.books.addBook

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.R
import com.example.lib_compose_sqlite.presentation.AppViewModelProvider
import com.example.lib_compose_sqlite.presentation.components.Header
import com.example.lib_compose_sqlite.presentation.navigation.Screen

@Composable
fun AddBookScreen(
    navController: NavController,
    context: Context,
    viewModel: BookAddViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Header(navController, stringResource(id = R.string.add_book), Screen.AdminScreen.route)
    { values ->
        Column(
            modifier = Modifier
                .padding(values)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = stringResource(R.string.enter_book_title))
            TextField(
                value = viewModel.bookUiState.bookDetails.title, onValueChange = {
                    viewModel.updateUiState(viewModel.bookUiState.bookDetails.copy(title = it))
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(text = stringResource(R.string.enter_book_author))
            TextField(
                value = viewModel.bookUiState.bookDetails.author, onValueChange = {
                    viewModel.updateUiState(viewModel.bookUiState.bookDetails.copy(author = it))
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(text = stringResource(R.string.enter_book_type))
            TextField(
                value = viewModel.bookUiState.bookDetails.type, onValueChange = {
                    viewModel.updateUiState(viewModel.bookUiState.bookDetails.copy(type = it))
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    viewModel.addBook(navController,context)
                }) {
                Text(stringResource(id = R.string.add_book))
            }
        }

    }
}