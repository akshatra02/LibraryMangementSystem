package com.example.lib_compose_sqlite.presentation.screens.books.studentBooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_compose_sqlite.data.local.books.BookUiDetailsState
import com.example.lib_compose_sqlite.data.repository.BookRepository
import com.example.lib_compose_sqlite.data.repository.StudentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class StudentMyBookViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    fun getStudentBooks(studentId: Long): StateFlow<BookUiDetailsState> =
        bookRepository.getBooksByStudentId(studentId)
            .map { BookUiDetailsState(it) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(2000),
                BookUiDetailsState()
            )
}
