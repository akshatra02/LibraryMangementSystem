package com.example.lib_compose_sqlite.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lib_compose_sqlite.presentation.screens.AdminLoginScreen
import com.example.lib_compose_sqlite.presentation.screens.AdminSignupScreen
import com.example.lib_compose_sqlite.presentation.screens.HomeScreen
import com.example.lib_compose_sqlite.presentation.screens.StudentLoginScreen
import com.example.lib_compose_sqlite.presentation.screens.StudentSignupScreen
import com.example.lib_compose_sqlite.presentation.screens.admin.AdminScreen
import com.example.lib_compose_sqlite.presentation.screens.admin.BooksScreen
import com.example.lib_compose_sqlite.presentation.screens.books.addBook.AddBookScreen
import com.example.lib_compose_sqlite.presentation.screens.books.removeBook.BookRemoveScreen
import com.example.lib_compose_sqlite.presentation.screens.books.issueBook.IssueBookScreen
import com.example.lib_compose_sqlite.presentation.screens.books.returnBook.ReturnBookScreen
import com.example.lib_compose_sqlite.presentation.screens.books.studentBooks.StudentMyBookScreen
import com.example.lib_compose_sqlite.presentation.screens.student.StudentBooksScreen
import com.example.lib_compose_sqlite.presentation.screens.student.StudentScreen


@Composable
fun LibraryNavHost( ){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {

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
        composable(route = Screen.BookRemoveScreen.route){
            BookRemoveScreen(navController, LocalContext.current)
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