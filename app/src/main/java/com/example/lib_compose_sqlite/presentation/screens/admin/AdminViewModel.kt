package com.example.lib_compose_sqlite.presentation.screens.admin

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.R
import com.example.lib_compose_sqlite.data.local.admin.AdminDetails
import com.example.lib_compose_sqlite.data.local.admin.AdminState
import com.example.lib_compose_sqlite.data.local.admin.toAdminEntity
import com.example.lib_compose_sqlite.data.repository.AdminRepository
import com.example.lib_compose_sqlite.presentation.navigation.Screen
import kotlinx.coroutines.launch

class AdminViewModel(
    private val adminRepository: AdminRepository
) : ViewModel() {
    var adminUiState by mutableStateOf(AdminState())
        private set
    private fun validateInput(uiState: AdminDetails = adminUiState.adminDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && password.isNotBlank()
        }
    }
    fun updateUiState(adminDetails: AdminDetails) {
        adminUiState =
            AdminState(adminDetails = adminDetails, isEntryValid = validateInput(adminDetails))
    }

    fun addAdmin(navController: NavController, context: Context){
        viewModelScope.launch {
            try {
                val admin = adminRepository.addAdmin(adminUiState.adminDetails.toAdminEntity())
                Toast.makeText(context,
                    context.getString(R.string.admin_signed_up_successfully, admin), Toast.LENGTH_LONG).show()
                navController.navigate(Screen.AdminLoginScreen.route)
            } catch (e: Exception) {
                // Handle failure cases
                Toast.makeText(context,
                    context.getString(R.string.admin_signup_failed), Toast.LENGTH_LONG).show()
            }
        }
    }
    fun loginAdmin(navController: NavController, context: Context){
        viewModelScope.launch {
             val adminDetails = adminUiState.adminDetails
//            try {
                val admin = adminRepository.loginAdmin(adminDetails.id, adminDetails.name, adminDetails.password)
                if (admin != null) {
                    // Login successful
                    Toast.makeText(context,  context.getString(R.string.welcome), Toast.LENGTH_LONG).show()
                    navController.navigate(Screen.AdminScreen.route)
                } else {
                    // Login failed
                    Toast.makeText(context,  context.getString(R.string.invalid_credentials), Toast.LENGTH_LONG).show()
                }
//            } catch (e: Exception) {
//                // Handle failure cases
//                Toast.makeText(context, "Login failed!", Toast.LENGTH_LONG).show()
//            }
        }
    }
}