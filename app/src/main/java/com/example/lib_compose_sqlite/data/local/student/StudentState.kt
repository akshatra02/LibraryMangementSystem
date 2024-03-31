package com.example.lib_compose_sqlite.data.local.student


data class StudentState(
    val studentDetails:StudentDetails = StudentDetails(),
    val isEntryValid: Boolean = false,
)

data class StudentDetails(
    val id: Long = 0,
    val name: String = "",
    val password: String = "",
    val bookCount: Int = 0,
)

fun StudentDetails.toStudentEntity(): StudentEntity = StudentEntity(
    studentId = id,
    studentUsername = name,
    studentPassword = password,
    studentReservedBookCount = bookCount
)