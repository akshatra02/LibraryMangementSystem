package com.example.lib_compose_sqlite.presentation.screens.books

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.presentation.AppViewModelProvider
import kotlinx.coroutines.launch

@Composable
fun AddBookScreenRoom(
    navController: NavController,
    viewModel: BookAddViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                coroutineScope.launch {
viewModel.addBook(navController, context)
                }
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
            TextField(value = viewModel.bookUiState.bookDetails.title, onValueChange = {
                viewModel.updateUiState(viewModel.bookUiState.bookDetails.copy(title = it))
            })
            Text(text = "Author")

            TextField(value = viewModel.bookUiState.bookDetails.author, onValueChange = {
                viewModel.updateUiState(viewModel.bookUiState.bookDetails.copy(author = it))

            })

        }
    }
}