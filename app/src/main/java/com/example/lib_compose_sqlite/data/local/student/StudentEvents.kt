package com.example.lib_compose_sqlite.data.local.student

sealed interface StudentEvents {
    data class AddStudent(val name: String, val password: String) : StudentEvents
    data class LoginStudent(val name: String, val password: String) : StudentEvents
}