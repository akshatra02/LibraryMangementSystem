package com.example.lib_compose_sqlite.presentation.screens.books

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.data.local.books.BookDetails
import com.example.lib_compose_sqlite.data.local.books.BookEntity
import com.example.lib_compose_sqlite.data.local.books.BookState
import com.example.lib_compose_sqlite.data.local.books.toBook
import com.example.lib_compose_sqlite.data.repository.BookRepository
import com.example.lib_compose_sqlite.presentation.navigation.Screen
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class BookRemoveViewModel(
    private val bookRepository: BookRepository
): ViewModel() {

    var bookUiState by mutableStateOf(BookState())
        private set

    fun updateUiState(bookDetails: BookDetails){
        bookUiState = BookState(bookDetails)
    }
    fun removeBook(navController: NavController,context: Context,id: Int){
        viewModelScope.launch {
            val book = bookRepository.getBookById(id)
            val removeBook = bookRepository.deleteBook(book)
            if (removeBook > 0) {
                Toast.makeText(context, "Book Removed successfully!", Toast.LENGTH_LONG).show()
                navController.navigate(Screen.AdminScreen.route)
            }
            else{

                Toast.makeText(context, "Failed to remove book", Toast.LENGTH_LONG).show()
                navController.navigate(Screen.BookRemoveScreen.route)
            }
        }
    }
}