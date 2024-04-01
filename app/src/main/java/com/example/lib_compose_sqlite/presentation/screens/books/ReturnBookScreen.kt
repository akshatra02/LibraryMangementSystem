package com.example.lib_compose_sqlite.presentation.screens.books

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.data.DBHelper
import com.example.lib_compose_sqlite.domain.BookReturnStatus
import com.example.lib_compose_sqlite.presentation.AppViewModelProvider
import com.example.lib_compose_sqlite.presentation.components.Header
import com.example.lib_compose_sqlite.presentation.navigation.Screen
import kotlinx.coroutines.launch


@Composable
fun ReturnBookScreen(
    navController: NavController, context: Context,
    studentId: Int,
    viewModel: ReturnBookViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val bookUiState = viewModel.bookUiState
    Header(navController, "Return Book", "${Screen.StudentScreen.route}/$studentId")
    { values ->
        Column(
            modifier = Modifier
                .padding(values),
        ) {
            Text(
                text = "Enter Book Id : ",
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
                Text(text = "Return Book")
            }

        }
    }
}