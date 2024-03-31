package com.example.lib_compose_sqlite.data.local.student

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.lib_compose_sqlite.data.local.admin.AdminEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Insert
    suspend fun addStudent(studentEntity: StudentEntity): Long

    @Query("SELECT * FROM student_table WHERE student_id = :studentId AND student_username = :studentName AND student_password = :studentPassword")
    suspend fun loginStudent(studentId: Long,studentName: String, studentPassword: String): StudentEntity?
}