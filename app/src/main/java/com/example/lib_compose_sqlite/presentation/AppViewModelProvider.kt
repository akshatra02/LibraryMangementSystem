package com.example.lib_compose_sqlite.presentation

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.lib_compose_sqlite.LibraryApplication
import com.example.lib_compose_sqlite.presentation.screens.admin.AdminViewModel
import com.example.lib_compose_sqlite.presentation.screens.books.addBook.BookAddViewModel
import com.example.lib_compose_sqlite.presentation.screens.books.removeBook.BookRemoveViewModel
import com.example.lib_compose_sqlite.presentation.screens.books.bookList.BookViewModel
import com.example.lib_compose_sqlite.presentation.screens.books.issueBook.IssueBookViewModel
import com.example.lib_compose_sqlite.presentation.screens.books.returnBook.ReturnBookViewModel
import com.example.lib_compose_sqlite.presentation.screens.books.studentBooks.StudentMyBookViewModel
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

        initializer {
            BookRemoveViewModel(libraryApplication().appContainer.bookRepository)
        }

        initializer {
            IssueBookViewModel(libraryApplication().appContainer.bookRepository,libraryApplication().appContainer.studentRepository)
        }
        initializer {
            StudentMyBookViewModel(libraryApplication().appContainer.bookRepository)
        }
        initializer {
            ReturnBookViewModel(libraryApplication().appContainer.bookRepository,libraryApplication().appContainer.studentRepository)
        }

    }
}
fun CreationExtras.libraryApplication() : LibraryApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as LibraryApplication)