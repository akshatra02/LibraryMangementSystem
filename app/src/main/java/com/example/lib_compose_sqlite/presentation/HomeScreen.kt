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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lib_compose_sqlite.Admin
import com.example.lib_compose_sqlite.Student
import com.example.lib_compose_sqlite.data.DBHelper
import com.example.lib_compose_sqlite.ui.theme.LIB_COMPOSE_SQLITETheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(
    navController: NavController
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    )
    {

        composable(route = Screen.HomeScreen.route){
            HomeScreen(navController)
        }
        composable(route = Screen.AdminLoginScreen.route){
            AdminLoginScreen(navController,LocalContext.current)
        }
        composable(route = Screen.AdminSignupScreen.route){
            AdminSignupScreen(navController,LocalContext.current)
        }
        composable(route = Screen.AdminScreen.route){
            AdminScreen(navController)
        }
        composable(route = Screen.StudentLoginScreen.route){
            StudentLoginScreen(navController,LocalContext.current)
        }
        composable(route = Screen.StudentSignupScreen.route){
            StudentSignupScreen(navController,LocalContext.current)
        }
        composable(route = Screen.StudentScreen.route){
            StudentScreen(navController)
        }
        composable(route = Screen.BooksScreen.route){
            BooksScreen(navController,LocalContext.current)
        }
        composable(route = Screen.IssueBookScreen.route){
            IssueBookScreen(navController,LocalContext.current)
        }
        composable(route = Screen.AddBookScreen.route){
            AddBookScreen(navController,LocalContext.current)
        }
        composable(route = Screen.RemoveBookScreen.route){
            RemoveBookScreen(navController,LocalContext.current)
        }
        composable(route = Screen.StudentBooksScreen.route){
            StudentBooksScreen(navController,LocalContext.current)
        }
        composable(route = Screen.StudentMyBookScreen.route){
            StudentMyBookScreen(navController, LocalContext.current)
        }
        composable(route = Screen.SearchBookScreen.route){
            SearchBookScreen(navController, LocalContext.current)
        }
    }
}
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
//            Text(text = "LIBRARY MANAGEMENT SYSTEM")
            Button(
                onClick = {
                    navController.navigate(Screen.AdminLoginScreen.route)

                }) {
                Text("Admin")

            }
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
    var text by remember {
        mutableStateOf("")
    }
    var password by remember {
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
            var dbhelper: DBHelper = DBHelper(context)
            Text(text = "SIGN UP PAGE")
            Text(text = "Name")
            TextField(
                value = text, onValueChange = {
                    text = it
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(text = "Password")
            TextField(
                value = password, onValueChange = {
                    password = it
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Button(
                onClick = {
                    val admin = Admin(text, password)
                    if (dbhelper.addAdmin(admin) > 0) {
                        navController.navigate(Screen.HomeScreen.route) {
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
    var text by remember {
        mutableStateOf("")
    }
    var password by remember {
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
                Text(text = "LOGIN PAGE")
                var dbhelper: DBHelper = DBHelper(context)
                Text(text = "Name")
                TextField(
                    value = text, onValueChange = {
                        text = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(text = "Password")
                TextField(
                    value = password, onValueChange = {
                        password = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Button(
                    onClick = {
                        val admin = Admin(text, password)
                        if (dbhelper.loginadmin(admin)) {
                            navController.navigate(Screen.AdminScreen.route)
                        } else {
                            Toast.makeText(
                                context,
                                "Login Failed",
                                Toast.LENGTH_LONG
                            )
                                .show()

                        }
                    }
                )
                {
                    Text("Login")

                }
                ClickableText(
                    text = AnnotatedString("New User...?Sign up"),
                    onClick = { offset ->
                        navController.navigate(Screen.AdminSignupScreen.route)
                    }
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentSignupScreen(navController: NavController,context: Context)
{
    var student_id by remember {
        mutableStateOf("")
    }
    var student_name by remember {
        mutableStateOf("")
    }
    var student_password by remember {
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
                var dbhelper: DBHelper = DBHelper(context)
                Text(text = "STUDENT SIGN UP PAGE")
                Text(text = "Student ID")
                TextField(
                    value = student_id, onValueChange = {
                        student_id = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(text = "Student Name")
                TextField(
                    value = student_name, onValueChange = {
                        student_name = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(text = "Student Password")
                TextField(
                    value = student_password, onValueChange = {
                        student_password = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Button(
                    onClick = {
                        val student = Student(student_id.toInt(), student_name, student_password)
                        try {
                            if (dbhelper.addStudent(student) > 0) {
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
                        catch (e : Exception){
                            Toast.makeText(
                                context,
                                "ERROR!",
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
    var student_id by remember {
        mutableStateOf("")
    }
    var student_name by remember {
        mutableStateOf("")
    }
    var student_password by remember {
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
                var dbhelper: DBHelper = DBHelper(context)
                Text(text = "Student ID")
                TextField(
                    value = student_id, onValueChange = {
                        student_id = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(text = "Student Name")
                TextField(
                    value = student_name, onValueChange = {
                        student_name = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(text = "Student Password")
                TextField(
                    value = student_password, onValueChange = {
                        student_password = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Button(
                    onClick = {
                        val student = Student(student_id.toInt(), student_name, student_password)
                        if (dbhelper.loginstudent(student)) {
                            navController.navigate(Screen.StudentScreen.route)
                        } else {
                            Toast.makeText(
                                context,
                                "Login Failed",
                                Toast.LENGTH_LONG
                            )
                                .show()

                        }
                    }
                )
                {
                    Text("Login")

                }
                ClickableText(
                    text = AnnotatedString("New User...?Sign up"),
                    onClick = { offset ->
                        navController.navigate(Screen.StudentSignupScreen.route)
                    }
                )
            }
    }
}
}

