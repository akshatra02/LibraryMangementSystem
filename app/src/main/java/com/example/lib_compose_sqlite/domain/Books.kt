package com.example.lib_compose_sqlite.domain


//enum class BookStatus{
//    Available,Reserved
//}
enum class BookType{
    Fiction,
    Biography,
    Historic,
    Magazine,
    Journal,
}

data class Book(
    val bookId: Int,
    val title: String,
    val author: String,
    val bookType: BookType,
    val reservedStudentId: Int?,
)

enum class BookReturnStatus(){
    WrongBookId,
    Successful,
    NoBookReserved,
    Failed,
}
enum class BookIssueStatus{
    Failed,
    Successful,
    AlreadyReserved,
    StudentLimitExceeded,
    InvalidInput,
}
