package com.example.lib_compose_sqlite.presentation

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.lib_compose_sqlite.LibraryApplication
import com.example.lib_compose_sqlite.presentation.screens.AdminViewModel
import com.example.lib_compose_sqlite.presentation.screens.books.BookAddViewModel
import com.example.lib_compose_sqlite.presentation.screens.books.BookViewModel
import com.example.lib_compose_sqlite.presentation.screens.student.StudentViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            BookViewModel(
                libraryApplication().appContainer.bookRepository
            )
        }
        initializer {
            BookAddViewModel(libraryApplication().appContainer.bookRepository)
        }

        initializer {
            AdminViewModel(libraryApplication().appContainer.adminRepository)
        }
        initializer {
            StudentViewModel(libraryApplication().appContainer.studentRepository)
        }
    }
}
fun CreationExtras.libraryApplication() : LibraryApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as LibraryApplication)