package com.example.lib_compose_sqlite.data.repository

import com.example.lib_compose_sqlite.data.local.books.BookDao
import com.example.lib_compose_sqlite.data.local.books.BookEntity
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getBooks(): Flow<List<BookEntity>>
    suspend fun addBook(book: BookEntity): Long
    suspend fun updateBook(bookEntity: BookEntity): Int

    suspend fun deleteBook(book: BookEntity): Int
    suspend fun getBookById(id: Int): BookEntity
    fun getBooksByStudentId(studentId: Long): Flow<List<BookEntity>>



}

class BookRepositoryImpl(private val bookDao: BookDao) : BookRepository {
    override fun getBooks(): Flow<List<BookEntity>> = bookDao.getBooks()

    override suspend fun addBook(book: BookEntity): Long = bookDao.addBook(book)
    override suspend fun updateBook(bookEntity: BookEntity): Int = bookDao.updateBook(bookEntity)

    override suspend fun deleteBook(book: BookEntity): Int = bookDao.deleteBook(book)
    override suspend fun getBookById(id: Int): BookEntity = bookDao.getBookById(id)

    override fun getBooksByStudentId(studentId: Long): Flow<List<BookEntity>> = bookDao.getBooksByStudentId(studentId)

}