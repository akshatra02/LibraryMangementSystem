package com.example.lib_compose_sqlite.presentation.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
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
import androidx.navigation.NavController
import com.example.lib_compose_sqlite.domain.Admin
import com.example.lib_compose_sqlite.domain.Person
import com.example.lib_compose_sqlite.domain.Student
import com.example.lib_compose_sqlite.data.DBHelper
import com.example.lib_compose_sqlite.presentation.components.Header
import com.example.lib_compose_sqlite.presentation.navigation.Screen
import com.example.lib_compose_sqlite.presentation.theme.LIB_COMPOSE_SQLITETheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController:NavController) {
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
            Text(text = "Welcome to our Library!\n Explore the world of books and expand your horizons.", textAlign = TextAlign.Center, style = TextStyle(fontSize = 20.sp))
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminSignupScreen(navController: NavController,context: Context) {
    var adminName by remember {
        mutableStateOf("")
    }
    var adminPassword by remember {
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()
    Header(navController,"Admin",Screen.AdminLoginScreen.route)
  { values ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(values),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val dbhelper = DBHelper(context)
            Text(text = "ADMIN SIGN UP PAGE")

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Name")
            TextField(
                value = adminName, onValueChange = {
                    adminName = it
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Password")
            TextField(
                value = adminPassword, onValueChange = {
                    adminPassword = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)

            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        try {
                            val admin = Person(adminName, adminPassword)
                            val addAdmin = dbhelper.addAdmin(admin)
                            if (addAdmin > 0) {
                                navController.navigate(Screen.AdminLoginScreen.route) {
                                    popUpTo(Screen.AdminLoginScreen.route) {
                                        inclusive = true
                                    }
                                }
                                Toast.makeText(
                                    context,
                                    "Admin - $addAdmin Signed Up successfully!",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "$addAdmin Admin Signup Failed!",
                                    Toast.LENGTH_LONG
                                )
                                    .show()

                            }
                        } catch (e: Exception) {
                            Toast.makeText(
                                context,
                                "Admin Signup Failed",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                }) {
                Text("Signup")

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminLoginScreen(navController: NavController,context: Context){
    var adminId by remember {
        mutableStateOf("")
    }
    var adminName by remember {
        mutableStateOf("")
    }
    var adminPassword by remember {
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()
    Header(navController,"Admin",Screen.HomeScreen.route)
   { values ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(values),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "ADMIN LOGIN PAGE")
                val dbhelper: DBHelper = DBHelper(context)
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "ID")
                TextField(
                    value = adminId, onValueChange = {
                        adminId = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)

                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Name")
                TextField(
                    value = adminName, onValueChange = {
                        adminName = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Password")
                TextField(
                    value = adminPassword, onValueChange = {
                        adminPassword = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
                )
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        coroutineScope.launch {
                        try {
                            val admin = Admin(adminId.toInt(), adminName, adminPassword)
                            when {
                                (adminName == "" || adminPassword == "") -> {
                                    Toast.makeText(
                                        context,
                                        "Please provide both your name and password.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }

                                (dbhelper.loginAdmin(admin)) -> {
                                    Toast.makeText(context, "Welcome $adminName", Toast.LENGTH_LONG).show()

                                    navController.navigate(Screen.AdminScreen.route)
                                }

                                else -> {
                                    Toast.makeText(
                                        context,
                                        "Invalid login credentials.",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()

                                }
                            }
                        } catch (e: Exception) {
                            Toast.makeText(
                                context,
                                "Admin Login Failed!",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                    }
                )
                {
                    Text("Login")

                }
                Spacer(modifier = Modifier.height(30.dp))

                ClickableText(
                    text = AnnotatedString("New User...?Sign up"),
                    onClick = {navController.navigate(Screen.AdminSignupScreen.route)},
                    style = TextStyle(fontSize = 20.sp, color = MaterialTheme.colorScheme.secondary)
                )
            }
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentSignupScreen(navController: NavController,context: Context)
{
    var studentName by remember {
        mutableStateOf("")
    }
    var studentPassword by remember{
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()
    Header(navController,"Student",Screen.StudentLoginScreen.route)
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
                    value = studentName, onValueChange = {
                        studentName = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Text(text = "Student Password")
                TextField(
                    value = studentPassword, onValueChange = {
                        studentPassword = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)

                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        coroutineScope.launch {

                        try {
                            val student = Person(studentName, studentPassword)
                            if (studentPassword == "" || studentName == "") {
                                Toast.makeText(context, "Please enter both name and password .", Toast.LENGTH_LONG)
                                    .show()
                            }
                            val addStudent = dbhelper.addStudent(student)
                            if (addStudent > 0) {
                                Toast.makeText(context, "Student - $addStudent Signup Successfull", Toast.LENGTH_LONG)
                                    .show()
                                navController.navigate(Screen.StudentLoginScreen.route) {
                                    popUpTo(Screen.HomeScreen.route) {
                                        inclusive = true
                                    }
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Student Signup Failed!",
                                    Toast.LENGTH_LONG
                                )
                                    .show()

                            }
                        } catch (e: Exception) {
                            Toast.makeText(
                                context,
                                "Student SignUp Failed!",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                    }
                ) {
                    Text("Signup")

                }
            }
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentLoginScreen(navController: NavController,context: Context) {
    var studentId by remember {
        mutableStateOf("")
    }
    var studentName by remember {
        mutableStateOf("")
    }
    var studentPassword by remember {
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()

    Header(navController,"Student",Screen.HomeScreen.route)
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
                    value = studentId, onValueChange = {
                        studentId = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)

                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Student Name")
                TextField(
                    value = studentName, onValueChange = {
                        studentName = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(text = "Student Password")
                TextField(
                    value = studentPassword, onValueChange = {
                        studentPassword = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)

                )
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        coroutineScope.launch {
                            try {
                                val student = Student(studentId.toInt(), studentName, studentPassword)
                                val studentIdLogin = dbhelper.loginStudent(student)
                                if (studentName == "") {
                                    Toast.makeText(
                                        context,
                                        "Please enter your name.",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()

                                } else if (studentIdLogin > 0) {
                                    Toast.makeText(
                                        context,
                                        "Login success $studentName",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                    navController.navigate("${Screen.StudentScreen.route}/$studentIdLogin")
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Invalid login credentials.",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()

                                }
                            } catch (e: Exception) {
                                Toast.makeText(
                                    context,
                                    "Student ID Login Failed!!",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                        }
                    }
                )
                {
                    Text("Login")

                }
                Spacer(modifier = Modifier.height(30.dp))
                ClickableText(
                    text = AnnotatedString("New User...?Sign up"),
                    onClick = { navController.navigate(Screen.StudentSignupScreen.route)},
                    style = TextStyle(fontSize = 20.sp, color = MaterialTheme.colorScheme.secondary)

                )
            }
    }
}
