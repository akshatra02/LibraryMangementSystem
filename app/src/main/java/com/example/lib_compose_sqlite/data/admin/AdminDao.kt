package com.example.lib_compose_sqlite.data.admin

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lib_compose_sqlite.data.books.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AdminDao {
    @Insert
    suspend fun addBook(book: BookEntity)

    @Delete
    suspend fun deleteBook(book: BookEntity)

    @Query("SELECT * FROM book_table ORDER BY book_id")
    fun getBooks(): Flow<List<BookEntity>>
}