package com.example.lib_compose_sqlite.data.local.books

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class BookState(
    val books:List<BookEntity> = emptyList(),
    val bookTitle:MutableState<String> = mutableStateOf(""),
    val bookAuthor:MutableState<String> = mutableStateOf(""),
)
