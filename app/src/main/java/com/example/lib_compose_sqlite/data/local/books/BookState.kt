package com.example.lib_compose_sqlite.data.local.books

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.lib_compose_sqlite.domain.BookType

data class BookState(
    val bookDetails: BookDetails = BookDetails()
)
data class BookDetails(
    val id: Int = 0,
    val author: String = "",
    val title: String = "",
    val type: String = "",
    val reservedStudentId: Long = 0L,

    )
fun BookDetails.toBook(): BookEntity = BookEntity(
    bookId = id,
    bookAuthor = author,
    bookTitle = title,
    bookType = BookType.valueOf(type),
    reservedStudentId = reservedStudentId
)
data class BookUiDetailsState(
    val bookItem: List<BookEntity> = listOf()
)