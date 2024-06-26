package com.example.lib_compose_sqlite.presentation.screens.books.issueBook

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.R
import com.example.lib_compose_sqlite.data.local.books.BookDetails
import com.example.lib_compose_sqlite.data.local.books.BookEntity
import com.example.lib_compose_sqlite.data.local.student.StudentDetails
import com.example.lib_compose_sqlite.data.local.student.StudentEntity
import com.example.lib_compose_sqlite.data.repository.BookRepository
import com.example.lib_compose_sqlite.data.repository.StudentRepository
import com.example.lib_compose_sqlite.presentation.navigation.Screen
import kotlinx.coroutines.launch

class IssueBookViewModel(private val bookRepository: BookRepository, private val studentRepository: StudentRepository): ViewModel()
{
    var bookUiState by mutableStateOf(BookDetails())
        private set

    var studentUiState by mutableStateOf(StudentDetails())
        private set

    fun updateBookUiState(bookDetails: BookDetails){
        bookUiState = bookDetails
    }
    fun updateStudentUiState(studentDetails: StudentDetails){
        studentUiState = studentDetails
    }

    fun issueBook(navController: NavController, context: Context){
        viewModelScope.launch {
            val bookId = bookUiState.id
            val studentId = studentUiState.id
            var studentBookCount = studentUiState.bookCount
            if (studentBookCount < 3) {
                studentBookCount ++
                val getBook = bookRepository.getBookById(bookId)
                val getStudent = studentRepository.getStudentById(studentId)
                val book: BookEntity = getBook.copy(reservedStudentId = studentId)
                val student: StudentEntity =
                    getStudent.copy(studentReservedBookCount = studentBookCount)
                val updateBook = bookRepository.updateBook(book)
                val updateStudent = studentRepository.updateStudent(student)
                if (updateBook != 0 && updateStudent != 0) {
                    Toast.makeText(context,
                        context.getString(R.string.book_issued_successfully), Toast.LENGTH_LONG).show()
                    navController.navigate(Screen.AdminScreen.route)
                } else {

                    Toast.makeText(context,
                        context.getString(R.string.failed_to_issue_book), Toast.LENGTH_LONG).show()
                    navController.navigate(Screen.IssueBookScreen.route)
                }
            } else {

                Toast.makeText(context,
                    context.getString(R.string.student_s_book_limit_exceeded), Toast.LENGTH_LONG).show()
                navController.navigate(Screen.AdminScreen.route)
            }
        }
    }
}