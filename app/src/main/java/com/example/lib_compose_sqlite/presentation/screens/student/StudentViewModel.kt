package com.example.lib_compose_sqlite.presentation.screens.student

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.data.local.admin.AdminDetails
import com.example.lib_compose_sqlite.data.local.admin.AdminState
import com.example.lib_compose_sqlite.data.local.admin.toAdminEntity
import com.example.lib_compose_sqlite.data.local.student.StudentDetails
import com.example.lib_compose_sqlite.data.local.student.StudentState
import com.example.lib_compose_sqlite.data.local.student.toStudentEntity
import com.example.lib_compose_sqlite.data.repository.AdminRepository
import com.example.lib_compose_sqlite.data.repository.StudentRepository
import com.example.lib_compose_sqlite.presentation.navigation.Screen
import kotlinx.coroutines.launch

class StudentViewModel(
    private val studentRepository: StudentRepository
) : ViewModel() {
    var studentUiState by mutableStateOf(StudentState())
        private set

    private fun validateInput(uiState: StudentDetails = studentUiState.studentDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && password.isNotBlank()
        }
    }

    fun updateUiState(studentDetails: StudentDetails) {
        studentUiState =
            StudentState(
                studentDetails = studentDetails,
                isEntryValid = validateInput(studentDetails)
            )
    }

    fun addStudent(navController: NavController, context: Context) {
        viewModelScope.launch {
            val student =
                studentRepository.addStudent(studentUiState.studentDetails.toStudentEntity())
            Toast.makeText(
                context,
                "Student - $student Signed Up successfully!",
                Toast.LENGTH_LONG
            ).show()
            navController.navigate(Screen.StudentLoginScreen.route)

        }
    }

    fun loginStudent(navController: NavController, context: Context) {
        viewModelScope.launch {
            val studentDetails = studentUiState.studentDetails
//            try {
            val student = studentRepository.loginStudent(
                studentDetails.id,
                studentDetails.name,
                studentDetails.password
            )
            if (student != null) {
                // Login successful
                Toast.makeText(context, "Welcome ${studentDetails.name}", Toast.LENGTH_LONG).show()
                navController.navigate("${Screen.StudentScreen.route}/${studentDetails.id}")
            } else {
                // Login failed
                Toast.makeText(context, "Invalid credentials!", Toast.LENGTH_LONG).show()
            }
//            } catch (e: Exception) {
//                // Handle failure cases
//                Toast.makeText(context, "Login failed!", Toast.LENGTH_LONG).show()
//            }
        }
    }
}