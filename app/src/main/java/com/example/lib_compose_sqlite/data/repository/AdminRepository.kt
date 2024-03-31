package com.example.lib_compose_sqlite.data.repository

import com.example.lib_compose_sqlite.data.local.admin.AdminDao
import com.example.lib_compose_sqlite.data.local.admin.AdminEntity
import kotlinx.coroutines.flow.Flow

interface AdminRepository {
    suspend fun addAdmin(adminEntity: AdminEntity): Long
    suspend fun loginAdmin(adminId: Long,adminName: String, adminPassword: String): AdminEntity?

}

class AdminRepositoryImpl(private val adminDao: AdminDao) : AdminRepository{
    override suspend fun addAdmin(adminEntity: AdminEntity): Long = adminDao.addAdmin(adminEntity)


    override suspend fun loginAdmin(
        adminId: Long,
        adminName: String,
        adminPassword: String
    ): AdminEntity? = adminDao.loginAdmin(adminId,adminName,adminPassword)


}