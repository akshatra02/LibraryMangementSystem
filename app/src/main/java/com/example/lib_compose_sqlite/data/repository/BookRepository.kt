package com.example.lib_compose_sqlite.data.repository

import com.example.lib_compose_sqlite.data.local.books.BookDao
import com.example.lib_compose_sqlite.data.local.books.BookEntity
import kotlinx.coroutines.flow.Flow

interface BookRepository{
    fun getBooks(): Flow<List<BookEntity>>
    suspend fun addBook(book: BookEntity): Long
    suspend fun deleteBook(book: BookEntity)


}
class BookRepositoryImpl(private val bookDao: BookDao): BookRepository {
    override fun getBooks(): Flow<List<BookEntity>> = bookDao.getBooks()

    override suspend fun addBook(book: BookEntity): Long = bookDao.addBook(book)

    override suspend fun deleteBook(book: BookEntity) = bookDao.deleteBook(book)

}