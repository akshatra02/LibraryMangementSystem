package com.example.lib_compose_sqlite.data.local.student

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class StudentEntity(
    @ColumnInfo(name = "student_username") val studentUsername: String,
    @ColumnInfo(name = "student_password") val studentPassword: String,
    @ColumnInfo(name = "student_reserved_book_count") val studentReservedBookCount: Int = 0,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "student_id")val studentId: Long = 0,
)