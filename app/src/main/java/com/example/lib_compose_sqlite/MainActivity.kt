package com.example.lib_compose_sqlite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.lib_compose_sqlite.presentation.*
import com.example.lib_compose_sqlite.ui.theme.LIB_COMPOSE_SQLITETheme
import java.time.LocalDate


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LIB_COMPOSE_SQLITETheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation(){
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
            AdminLoginScreen(navController, LocalContext.current)
        }
        composable(route = Screen.AdminSignupScreen.route){
            AdminSignupScreen(navController, LocalContext.current)
        }
        composable(route = Screen.AdminScreen.route){
            AdminScreen(navController)
        }
        composable(route = Screen.StudentLoginScreen.route){
            StudentLoginScreen(navController, LocalContext.current)
        }
        composable(route = Screen.StudentSignupScreen.route){
            StudentSignupScreen(navController, LocalContext.current)
        }
        composable(route = Screen.BooksScreen.route){
            BooksScreen(navController, LocalContext.current)
        }
        composable(route = Screen.IssueBookScreen.route){
            IssueBookScreen(navController, LocalContext.current)
        }
        composable(route = Screen.AddBookScreen.route){
            AddBookScreen(navController, LocalContext.current)
        }
        composable(route = Screen.RemoveBookScreen.route){
            RemoveBookScreen(navController, LocalContext.current)
        }
        composable(
            route = "${Screen.StudentScreen.route}/{studentId}",
            arguments = listOf(navArgument("studentId") { type = NavType.IntType })
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getInt("studentId") ?: -1
            StudentScreen(navController, studentId)
        }
        composable(route = "${Screen.StudentBooksScreen.route}/{studentId}",
            arguments = listOf(navArgument("studentId"){type = NavType.IntType})
        ){ backStackEntry ->
            val studentId = backStackEntry.arguments?.getInt("studentId") ?: -1
            StudentBooksScreen(navController, LocalContext.current,studentId)
        }
        composable(route = "${Screen.StudentMyBookScreen.route}/{studentId}",
            arguments = listOf(navArgument("studentId"){type = NavType.IntType})
        ){ backStackEntry ->
            val studentId = backStackEntry.arguments?.getInt("studentId") ?: -1
            StudentMyBookScreen(navController, LocalContext.current,studentId)
        }
        composable(route="${Screen.ReturnBookScreen.route}/{studentId}",
            arguments = listOf(navArgument("studentId"){type = NavType.IntType})
        ){ backStackEntry ->
            val studentId = backStackEntry.arguments?.getInt("studentId") ?: -1
            ReturnBookScreen(navController, LocalContext.current,studentId)
        }
    }
}