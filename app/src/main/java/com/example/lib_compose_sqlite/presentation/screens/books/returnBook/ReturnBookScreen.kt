package com.example.lib_compose_sqlite.presentation.screens.books.returnBook

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.R
import com.example.lib_compose_sqlite.presentation.AppViewModelProvider
import com.example.lib_compose_sqlite.presentation.components.Header
import com.example.lib_compose_sqlite.presentation.navigation.Screen


@Composable
fun ReturnBookScreen(
    navController: NavController, context: Context,
    studentId: Int,
    viewModel: ReturnBookViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val bookUiState = viewModel.bookUiState
    Header(navController,
        stringResource(R.string.return_book), "${Screen.StudentScreen.route}/$studentId")
    { values ->
        Column(
            modifier = Modifier
                .padding(values),
        ) {
            Text(
                text = stringResource(id = R.string.enter_book_id),
                style = TextStyle(fontSize = 24.sp)
            )
            TextField(
                value = if(bookUiState.id !=0 ) bookUiState.id.toString() else "",
                onValueChange = {
                    viewModel.updateBookUiState(bookUiState.copy(id = it.toIntOrNull() ?: 0))
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            Button(
                onClick = {
                    viewModel.returnBook(studentId.toLong(),navController,context)
                }
            )
            {
                Text(text = stringResource(id = R.string.return_book))
            }

        }
    }
}