package com.example.lib_compose_sqlite.data.local.admin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "admin_table")
data class AdminEntity(
    @ColumnInfo(name = "admin_username") val adminUsername: String,
    @ColumnInfo(name = "admin_password") val adminPassword: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "admin_id")val adminId: Long = 0,
)
