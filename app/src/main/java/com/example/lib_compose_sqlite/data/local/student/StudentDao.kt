package com.example.lib_compose_sqlite.data.local.student

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lib_compose_sqlite.data.local.admin.AdminEntity
import com.example.lib_compose_sqlite.data.local.books.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Insert
    suspend fun addStudent(studentEntity: StudentEntity): Long

    @Update
    suspend fun updateStudent(studentEntity: StudentEntity): Int


    @Query("SELECT * FROM student_table WHERE student_id = :id")
    suspend fun getStudentById(id: Long): StudentEntity

    @Query("SELECT * FROM student_table WHERE student_id = :studentId AND student_username = :studentName AND student_password = :studentPassword")
    suspend fun loginStudent(studentId: Long,studentName: String, studentPassword: String): StudentEntity?
}