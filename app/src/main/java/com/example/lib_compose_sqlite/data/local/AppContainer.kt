package com.example.lib_compose_sqlite.data.local

import android.content.Context
import com.example.lib_compose_sqlite.data.repository.AdminRepository
import com.example.lib_compose_sqlite.data.repository.AdminRepositoryImpl
import com.example.lib_compose_sqlite.data.repository.BookRepository
import com.example.lib_compose_sqlite.data.repository.BookRepositoryImpl
import com.example.lib_compose_sqlite.data.repository.StudentRepository
import com.example.lib_compose_sqlite.data.repository.StudentRepositoryImpl

interface AppContainer {
    val bookRepository:BookRepository
    val adminRepository: AdminRepository
    val studentRepository: StudentRepository
}
class AppDataContainer(private val context: Context): AppContainer{
    override val bookRepository: BookRepository by lazy {
        BookRepositoryImpl(LibraryDatabase.getDatabase(context).bookDao())
    }
    override val adminRepository: AdminRepository by lazy {
        AdminRepositoryImpl(LibraryDatabase.getDatabase(context).adminDao())
    }
    override val studentRepository: StudentRepository by lazy {
        StudentRepositoryImpl(LibraryDatabase.getDatabase(context).studentDao())
    }

}