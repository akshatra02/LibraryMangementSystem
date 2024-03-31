package com.example.lib_compose_sqlite.presentation.screens.books

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_compose_sqlite.data.local.books.BookDao
import com.example.lib_compose_sqlite.data.local.books.BookEntity
import com.example.lib_compose_sqlite.data.local.books.BookEvents
import com.example.lib_compose_sqlite.data.local.books.BookState
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
    private val itemsRepository: BookRepository
) : ViewModel() {

    private val booksortedbyid = MutableStateFlow(true)
    val bookUiState: StateFlow<BookUiDetailsState> =
        itemsRepository.getBooks()
            .map { BookUiDetailsState(it) }
            .stateIn(
                viewModelScope, SharingStarted.WhileSubscribed(2000),
                BookUiDetailsState()
            )
    fun deleteBook(bookEntity: BookEntity){
        viewModelScope.launch {
            itemsRepository.deleteBook(bookEntity)
        }
    }

}
//    private var books =
//        booksortedbyid.flatMapLatest { sort ->
//            itemsRepository.getBooks()
//        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
//
//    val _state = MutableStateFlow(BookState())
//
//
//    val state = combine(
//        _state, booksortedbyid, books
//    ) { _state, bookstoredbyid, books ->
//        _state.copy(
//            books = books
//        )
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), BookState())
//
//    fun onEvent(event: BookEvents) {
//        when (event) {
//            is BookEvents.deleteBook -> {
//                viewModelScope.launch {
//                    itemsRepository.deleteBook(event.book)
//                }
//            }
//
//            is BookEvents.SaveBook -> {
//                val book = BookEntity(
//                    bookTitle = _state.value.bookTitle.value,
//                    bookAuthor = _state.value.bookAuthor.value,
//                )
//                viewModelScope.launch {
//                    itemsRepository.addBook(book)
//                }
//                _state.update {
//                    it.copy(
//                        bookTitle = mutableStateOf(""),
//                        bookAuthor = mutableStateOf(""),
//                    )
//                }
//            }
//
//            BookEvents.sortBook -> {
//                booksortedbyid
//            }
//        }
//    }
//}

data class BookUiDetailsState(
    val bookItem: List<BookEntity> = listOf()
)