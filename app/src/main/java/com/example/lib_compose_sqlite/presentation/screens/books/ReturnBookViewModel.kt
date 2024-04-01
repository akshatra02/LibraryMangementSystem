package com.example.lib_compose_sqlite.presentation.screens.books

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.data.local.books.BookDetails
import com.example.lib_compose_sqlite.data.repository.BookRepository
import com.example.lib_compose_sqlite.data.repository.StudentRepository
import com.example.lib_compose_sqlite.presentation.navigation.Screen
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch


class ReturnBookViewModel(
    private val bookRepository: BookRepository,
    private val studentRepository: StudentRepository
) : ViewModel() {
    var bookUiState by mutableStateOf(BookDetails())
        private set

    fun updateBookUiState(bookDetails: BookDetails) {
        bookUiState = bookDetails
    }

    fun returnBook(studentId: Long, navController: NavController, context: Context) {
        viewModelScope.launch {
            val bookId = bookUiState.id
            var getBookById = bookRepository.getBookById(bookId)
            val getBook = bookRepository.getBooksByStudentId(studentId).firstOrNull()
            val isBookReservedByStudent: Boolean = getBook?.any { it.bookId == bookId } ?: false
            if (isBookReservedByStudent) {
                getBookById.reservedStudentId = 0
                val updateBook = bookRepository.updateBook(getBookById)
                val student = studentRepository.getStudentById(studentId)
                student.studentReservedBookCount - 1
                val updateStudent = studentRepository.updateStudent(student)
                if (updateBook > 0 && updateStudent > 0) {
                    Toast.makeText(context, "Book Returned successfully!", Toast.LENGTH_LONG).show()
                    navController.navigate("${Screen.StudentScreen.route}/$studentId")
                } else {
                    Toast.makeText(context, "Failed to return book", Toast.LENGTH_LONG).show()
                    navController.navigate("${Screen.StudentScreen.route}/$studentId")
                }
            }
            else{
                Toast.makeText(context, "The book is not reserved by the student", Toast.LENGTH_LONG).show()
                navController.navigate("${Screen.StudentScreen.route}/$studentId")

            }
        }
    }

}
