package com.example.lib_compose_sqlite.data.local.admin


data class AdminState(
    val adminDetails: AdminDetails = AdminDetails(),
    val isEntryValid: Boolean = false,
)

data class AdminDetails(
    val id: Long = 0,
    val name: String = "",
    val password: String = "",
)

fun AdminDetails.toAdminEntity(): AdminEntity = AdminEntity(
    adminId = id,
    adminUsername = name,
    adminPassword = password
)