package com.example.lib_compose_sqlite.presentation.screens.books.bookList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_compose_sqlite.data.local.books.BookUiDetailsState
import com.example.lib_compose_sqlite.data.repository.BookRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class BookViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {

    val bookUiState: StateFlow<BookUiDetailsState> =
        bookRepository.getBooks()
            .map { BookUiDetailsState(it) }
            .stateIn(
                viewModelScope, SharingStarted.WhileSubscribed(2000),
                BookUiDetailsState()
            )
}
