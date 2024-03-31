package com.example.lib_compose_sqlite.presentation.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.data.DBHelper
import com.example.lib_compose_sqlite.presentation.AppViewModelProvider
import com.example.lib_compose_sqlite.presentation.components.Header
import com.example.lib_compose_sqlite.presentation.navigation.Screen
import com.example.lib_compose_sqlite.presentation.screens.student.StudentViewModel
import com.example.lib_compose_sqlite.presentation.theme.LIB_COMPOSE_SQLITETheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "LIBRARY MANAGEMENT SYSTEM") },
                )
            }
        ) { values ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(values),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(100.dp))
                Text(
                    text = "Welcome to our Library!\n Explore the world of books and expand your horizons.",
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 20.sp)
                )
                Spacer(modifier = Modifier.height(100.dp))

                Button(
                    onClick = {
                        navController.navigate(Screen.AdminLoginScreen.route)

                    }) {
                    Text("Admin")

                }
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        navController.navigate(Screen.StudentLoginScreen.route)
                    }) {
                    Text("Student")

                }
            }
        }
    }
}

@Composable
fun AdminSignupScreen(
    navController: NavController, context: Context,
    viewmodel: AdminViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    )
) {
    val adminUiDetails = viewmodel.adminUiState.adminDetails
    Header(navController, "Admin", Screen.AdminLoginScreen.route)
    { values ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(values),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "ADMIN SIGN UP PAGE")

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Name")
            TextField(
                value = adminUiDetails.name, onValueChange = {
                    viewmodel.updateUiState(adminUiDetails.copy(name = it))
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Password")
            TextField(
                value = adminUiDetails.password, onValueChange = {
                    viewmodel.updateUiState(adminUiDetails.copy(password = it))
                },
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)

            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                enabled = viewmodel.adminUiState.isEntryValid,
                onClick = {
                    viewmodel.addAdmin(navController, context)
                }) {
                Text("Signup")

            }
        }
    }
}

@Composable
fun AdminLoginScreen(
    navController: NavController, context: Context, viewmodel: AdminViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    )
) {
    val adminUiDetails = viewmodel.adminUiState.adminDetails
    Header(navController, "Admin", Screen.HomeScreen.route)
    { values ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(values),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "ADMIN LOGIN PAGE")
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "ID")
            TextField(
                value = if (adminUiDetails.id != 0L) adminUiDetails.id.toString() else "",
                onValueChange = {
                    viewmodel.updateUiState(adminUiDetails.copy(id = it.toLongOrNull() ?: 0))
                },
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)

            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Name")
            TextField(
                value = adminUiDetails.name, onValueChange = {
                    viewmodel.updateUiState(adminUiDetails.copy(name = it))
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Password")
            TextField(
                value = adminUiDetails.password, onValueChange = {
                    viewmodel.updateUiState(adminUiDetails.copy(password = it))
                },
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                enabled = viewmodel.adminUiState.isEntryValid,
                onClick = {
                    viewmodel.loginAdmin(navController, context)
                }
            )
            {
                Text("Login")

            }
            Spacer(modifier = Modifier.height(30.dp))

            ClickableText(
                text = AnnotatedString("New User...?Sign up"),
                onClick = { navController.navigate(Screen.AdminSignupScreen.route) },
                style = TextStyle(fontSize = 20.sp, color = MaterialTheme.colorScheme.secondary)
            )
        }
    }
}

@Composable
fun StudentSignupScreen(
    navController: NavController,
    context: Context,
    viewModel: StudentViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val studentUiDetails = viewModel.studentUiState.studentDetails

    Header(navController, "Student", Screen.StudentLoginScreen.route)
    { values ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(values),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val dbhelper: DBHelper = DBHelper(context)
            Text(text = "STUDENT SIGN UP PAGE")
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Student Name")
            TextField(
                value = studentUiDetails.name, onValueChange = {
                    viewModel.updateUiState(studentUiDetails.copy(name = it))

                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(text = "Student Password")
            TextField(
                value = studentUiDetails.password, onValueChange = {
                    viewModel.updateUiState(studentUiDetails.copy(password = it))

                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)

            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                enabled = viewModel.studentUiState.isEntryValid,
                onClick = {
                    viewModel.addStudent(navController, context)
                }
            ) {
                Text("Signup")

            }
        }
    }
}

@Composable
fun StudentLoginScreen(
    navController: NavController,
    context: Context,
    viewModel: StudentViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val studentUiDetails = viewModel.studentUiState.studentDetails
    Header(navController, "Student", Screen.HomeScreen.route)
    { values ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(values),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "STUDENT LOGIN PAGE")
            val dbhelper = DBHelper(context)
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Student ID")
            TextField(
                value = if (studentUiDetails.id != 0L) studentUiDetails.id.toString() else "",
                onValueChange = {
                    viewModel.updateUiState(studentUiDetails.copy(id = it.toLongOrNull() ?: 0))
                },
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)

            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Student Name")
            TextField(
                value = studentUiDetails.name, onValueChange = {
                    viewModel.updateUiState(studentUiDetails.copy(name = it))
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(text = "Student Password")
            TextField(
                value = studentUiDetails.password, onValueChange = {
                    viewModel.updateUiState(studentUiDetails.copy(password = it))
                },
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)

            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                enabled = viewModel.studentUiState.isEntryValid,
                onClick = {
                    viewModel.loginStudent(navController, context)
                }
            )
            {
                Text("Login")

            }
            Spacer(modifier = Modifier.height(30.dp))
            ClickableText(
                text = AnnotatedString("New User...?Sign up"),
                onClick = { navController.navigate(Screen.StudentSignupScreen.route) },
                style = TextStyle(fontSize = 20.sp, color = MaterialTheme.colorScheme.secondary)

            )
        }
    }
}
