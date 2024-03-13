package com.example.lib_compose_sqlite.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.lib_compose_sqlite.Admin
import com.example.lib_compose_sqlite.Student
import com.example.lib_compose_sqlite.data.DBHelper
import com.example.lib_compose_sqlite.ui.theme.LIB_COMPOSE_SQLITETheme
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
//            Text(text = "LIBRARY MANAGEMENT SYSTEM")
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
    var adminId by remember {
        mutableStateOf("")
    }
    var adminName by remember {
        mutableStateOf("")
    }
    var adminPassword by remember {
        mutableStateOf("")
    }
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Admin") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Screen.AdminLoginScreen.route) {
                                popUpTo(Screen.AdminLoginScreen.route) {
                                    inclusive = true
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }
        ) { values ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(values),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val dbhelper = DBHelper(context)
            Text(text = "ADMIN SIGN UP PAGE")
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "ID")
            TextField(
                value = adminId, onValueChange = {
                    adminId = it
                },
                modifier = Modifier
                    .fillMaxWidth()
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
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    try {
                        val admin = Admin(adminId.toInt(), adminName, adminPassword)
                        if (dbhelper.addAdmin(admin) > 0) {
                            navController.navigate(Screen.AdminLoginScreen.route) {
                                popUpTo(Screen.AdminLoginScreen.route) {
                                    inclusive = true
                                }
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Admin Signup Failed!",
                                Toast.LENGTH_LONG
                            )
                                .show()

                        }
                    }
                    catch (e : NumberFormatException){
                        Toast.makeText(
                            context,
                            "Admin ID must be number!",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }) {
                Text("Signup")

            }
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
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {Text(text = "Admin")},
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Screen.HomeScreen.route){
                                popUpTo(Screen.HomeScreen.route){
                                    inclusive = true
                                }
                            }
                        }){
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }
        ) { values ->
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
                        .fillMaxWidth()
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
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        try {
                            val admin = Admin(adminId.toInt(), adminName, adminPassword)
                            if (adminName == "" || adminPassword == "") {
                                Toast.makeText(
                                    context,
                                    "Please provide both your name and password.",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else if (dbhelper.loginadmin(admin)) {
                                Toast.makeText(context, "Welcome $adminName", Toast.LENGTH_LONG).show()

                                navController.navigate(Screen.AdminScreen.route)
                            } else {
                                Toast.makeText(
                                    context,
                                    "Invalid login credentials.",
                                    Toast.LENGTH_LONG
                                )
                                    .show()

                            }
                        }
                        catch (e : NumberFormatException){
                            Toast.makeText(
                                context,
                                "Admin ID must be number!",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }

                    }
                )
                {
                    Text("Login")

                }
                Spacer(modifier = Modifier.height(30.dp))

                ClickableText(
                    text = AnnotatedString("New User...?Sign up"),
                    onClick = { offset ->
                        navController.navigate(Screen.AdminSignupScreen.route)
                    },
                    style = TextStyle(fontSize = 20.sp)

                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentSignupScreen(navController: NavController,context: Context)
{
    var studentId by remember {
        mutableStateOf("")
    }
    var studentName by remember {
        mutableStateOf("")
    }
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Student") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Screen.StudentLoginScreen.route) {
                                popUpTo(Screen.StudentLoginScreen.route) {
                                    inclusive = true
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }
        ) { values ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(values),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val dbhelper: DBHelper = DBHelper(context)
                Text(text = "STUDENT SIGN UP PAGE")
                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Student ID")
                TextField(
                    value = studentId, onValueChange = {
                        studentId = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
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
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {

                        try {
                            val student = Student(studentId.toInt(), studentName)
                            if (studentId==""|| studentName=="" ){
                                Toast.makeText(context, "Please enter your name.", Toast.LENGTH_LONG).show()
                            }
                            else if (dbhelper.addStudent(student) > 0) {
                                Toast.makeText(context, "Signup Successfull",Toast.LENGTH_LONG).show()
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
                        }
                        catch (e : NumberFormatException){
                            Toast.makeText(
                                context,
                                "Student ID must be number!",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                ) {
                    Text("Signup")

                }
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
    LIB_COMPOSE_SQLITETheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Student") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Screen.HomeScreen.route) {
                                popUpTo(Screen.HomeScreen.route) {
                                    inclusive = true
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }
        ) { values ->
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
                        .fillMaxWidth()
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
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        try {
                            val student = Student(studentId.toInt(), studentName)
                            val studentIdLogin = dbhelper.loginstudent(student)
                            if(studentName==""){
                                Toast.makeText(
                                    context,
                                    "Please enter your name.",
                                    Toast.LENGTH_LONG
                                )
                                    .show()

                            }
                            else if (studentIdLogin > 0) {
                                Toast.makeText(
                                    context,
                                    "Login success $studentIdLogin",
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
                        }
                        catch (e : NumberFormatException){
                            Toast.makeText(
                                context,
                                "Student ID must be number!",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                )
                {
                    Text("Login")

                }
                Spacer(modifier = Modifier.height(30.dp))
                ClickableText(
                    text = AnnotatedString("New User...?Sign up"),
                    onClick = { offset ->
                        navController.navigate(Screen.StudentSignupScreen.route)
                    },
                    style = TextStyle(fontSize = 20.sp)

                )
            }
    }
}
}
