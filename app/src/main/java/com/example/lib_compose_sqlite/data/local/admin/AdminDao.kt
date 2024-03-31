package com.example.lib_compose_sqlite.data.local.admin

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AdminDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAdmin(adminEntity: AdminEntity) : Long

    @Query("SELECT * FROM admin_table WHERE admin_id = :adminId AND admin_username = :adminName AND admin_password = :adminPassword")
    suspend fun loginAdmin(adminId: Long,adminName: String, adminPassword: String): AdminEntity?
}