package com.example.lib_compose_sqlite


enum class BookStatus{
    Available,Reserved
}
enum class BookType{
    Fiction,Biography,Historic,Magazine,Journal
}
data class Book(val bookId:Int, val title: String, val author: String, val bookType: BookType, var status: BookStatus = BookStatus.Available)