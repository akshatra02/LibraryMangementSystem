package com.example.lib_compose_sqlite.presentation.screens.books

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.lib_compose_sqlite.data.local.books.BookEntity
import com.example.lib_compose_sqlite.data.local.books.BookState
import com.example.lib_compose_sqlite.data.repository.BookRepository

class BookAddViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    var bookUiState by mutableStateOf(BookUiState())
        private set

    fun updateUiState(bookDetails:BookDetails){
        bookUiState = BookUiState(bookDetails)
    }

    suspend fun addBook(){
bookRepository.addBook(bookUiState.bookItems.toBook())
    }
}
data class BookUiState(
    val bookItems: BookDetails = BookDetails()
)
data class BookDetails(
    val id: Int = 0,
    val name: String = "",
    val title: String = "",
)
fun BookDetails.toBook(): BookEntity = BookEntity(
    bookId = id,
    bookAuthor = name,
    bookTitle = title
)
