package com.example.lib_compose_sqlite.data.local.books

sealed interface BookEvents {
    data class deleteBook(val book : BookEntity) : BookEvents
    object sortBook : BookEvents
    data class SaveBook(val title: String, val author: String) : BookEvents
}