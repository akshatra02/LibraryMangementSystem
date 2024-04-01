
package com.example.lib_compose_sqlite.data.local.books

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBook(book: BookEntity): Long

    @Update
    suspend fun updateBook(bookEntity: BookEntity): Int

    @Delete
    suspend fun deleteBook(book: BookEntity): Int

    @Query("SELECT * FROM book_table WHERE book_id = :id")
    suspend fun getBookById(id: Int): BookEntity

    @Query("SELECT * FROM book_table ORDER BY book_id")
    fun getBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM book_table WHERE reserved_student_id = :studentId")
    fun getBooksByStudentId(studentId: Long): Flow<List<BookEntity>>
}