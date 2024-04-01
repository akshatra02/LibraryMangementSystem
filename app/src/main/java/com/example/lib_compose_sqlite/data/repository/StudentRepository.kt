package com.example.lib_compose_sqlite.data.repository

import com.example.lib_compose_sqlite.data.local.student.StudentDao
import com.example.lib_compose_sqlite.data.local.student.StudentEntity

interface StudentRepository {
    suspend fun addStudent(studentEntity: StudentEntity): Long
    suspend fun loginStudent(studentId: Long,studentName: String, studentPassword: String): StudentEntity?
    suspend fun updateStudent(studentEntity: StudentEntity): Int
    suspend fun getStudentById(id: Long): StudentEntity



}
class StudentRepositoryImpl(private val studentDao: StudentDao): StudentRepository{
    override suspend fun addStudent(studentEntity: StudentEntity): Long = studentDao.addStudent(studentEntity)

    override suspend fun loginStudent(
        studentId: Long,
        studentName: String,
        studentPassword: String
    ): StudentEntity? = studentDao.loginStudent(studentId,studentName,studentPassword)
    override suspend fun updateStudent(studentEntity: StudentEntity): Int = studentDao.updateStudent(studentEntity)

    override suspend fun getStudentById(id: Long): StudentEntity = studentDao.getStudentById(id)


}