package com.example.lib_compose_sqlite.presentation.screens.books

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_compose_sqlite.data.local.books.BookDao
import com.example.lib_compose_sqlite.data.local.books.BookEntity
import com.example.lib_compose_sqlite.data.local.books.BookState
import com.example.lib_compose_sqlite.data.local.books.toBook
import com.example.lib_compose_sqlite.data.repository.BookRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
    fun deleteBook(bookEntity: BookEntity){
        viewModelScope.launch {
            bookRepository.deleteBook(bookEntity)
        }
    }

}

data class BookUiDetailsState(
    val bookItem: List<BookEntity> = listOf()
)