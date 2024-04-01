package com.example.lib_compose_sqlite.data.local.books

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lib_compose_sqlite.domain.BookType

@Entity(tableName = "book_table")
data class BookEntity(
    @ColumnInfo(name = "book_title") val bookTitle: String,
    @ColumnInfo(name = "book_author") val bookAuthor: String,
    @ColumnInfo(name = "book_type") val bookType: BookType,
    @ColumnInfo(name = "reserved_student_id") var reservedStudentId: Long = 0,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_id")val bookId: Int = 0,
)

