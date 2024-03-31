package com.example.lib_compose_sqlite.presentation.screens.books

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.data.local.books.BookDetails
import com.example.lib_compose_sqlite.data.local.books.BookEntity
import com.example.lib_compose_sqlite.data.local.books.BookState
import com.example.lib_compose_sqlite.data.local.books.toBook
import com.example.lib_compose_sqlite.data.repository.BookRepository
import com.example.lib_compose_sqlite.presentation.navigation.Screen
import kotlinx.coroutines.launch

class BookAddViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    var bookUiState by mutableStateOf(BookState())
        private set

    fun updateUiState(bookDetails: BookDetails) {
        bookUiState = BookState(bookDetails)
    }

    fun addBook(navController: NavController ,context: Context) {
        viewModelScope.launch {
            val book =bookRepository.addBook(bookUiState.bookDetails.toBook())
            if (book > 0) {
                Toast.makeText(context, "Book Added successfully!", Toast.LENGTH_LONG).show()
                navController.navigate(Screen.AdminScreen.route)
            }
            else{

                Toast.makeText(context, "Failed to add book", Toast.LENGTH_LONG).show()
                navController.navigate(Screen.AddBookScreen.route)
            }
        }
    }
}

