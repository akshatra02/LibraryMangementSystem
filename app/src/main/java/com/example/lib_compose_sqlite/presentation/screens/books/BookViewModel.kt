package com.example.lib_compose_sqlite.presentation.screens.books

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_compose_sqlite.data.books.BookDao
import com.example.lib_compose_sqlite.data.books.BookEntity
import com.example.lib_compose_sqlite.data.books.BookEvents
import com.example.lib_compose_sqlite.data.books.BookState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookViewModel(private val dao: BookDao): ViewModel() {
    private val booksortedbyid = MutableStateFlow(true)
    private var books =
        booksortedbyid.flatMapLatest {
            sort -> dao.getBooks()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val _state = MutableStateFlow(BookState())


    val state  = combine(
        _state,booksortedbyid,books){ _state,bookstoredbyid,books ->
            _state.copy (
                books = books
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), BookState())
    fun onEvent(event: BookEvents){
        when(event){
            is BookEvents.deleteBook -> {
                viewModelScope.launch {
                    dao.deleteBook(event.book)
                }
            }
            is BookEvents.SaveBook -> {
                val book = BookEntity(
                        bookTitle = _state.value.bookTitle.value,
                        bookAuthor = _state.value.bookAuthor.value,
                    )
                viewModelScope.launch {
                    dao.addBook(book)
                }
                _state.update {
                    it.copy(
                        bookTitle = mutableStateOf(""),
                        bookAuthor = mutableStateOf(""),
                    )
                }
            }
            BookEvents.sortBook -> {
                booksortedbyid
            }
        }
    }
}