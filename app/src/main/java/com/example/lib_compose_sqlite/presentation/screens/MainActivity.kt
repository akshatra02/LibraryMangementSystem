package com.example.lib_compose_sqlite.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.lib_compose_sqlite.presentation.navigation.Screen
import com.example.lib_compose_sqlite.presentation.screens.admin.AddBookScreen
import com.example.lib_compose_sqlite.presentation.screens.admin.AdminScreen
import com.example.lib_compose_sqlite.presentation.screens.admin.BooksScreen
import com.example.lib_compose_sqlite.presentation.screens.admin.IssueBookScreen
import com.example.lib_compose_sqlite.presentation.screens.admin.RemoveBookScreen
import com.example.lib_compose_sqlite.presentation.screens.student.ReturnBookScreen
import com.example.lib_compose_sqlite.presentation.screens.student.StudentBooksScreen
import com.example.lib_compose_sqlite.presentation.screens.student.StudentMyBookScreen
import com.example.lib_compose_sqlite.presentation.screens.student.StudentScreen
import com.example.lib_compose_sqlite.presentation.theme.LIB_COMPOSE_SQLITETheme
import com.example.lib_compose_sqlite.data.books.BookDatabase
import com.example.lib_compose_sqlite.presentation.screens.books.AddBookScreenRoom
import com.example.lib_compose_sqlite.presentation.screens.books.BookScreenRoom
import com.example.lib_compose_sqlite.presentation.screens.books.BookViewModel




class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            BookDatabase::class.java,
            name = "book.db"
        ).build()
    }
    private val viewModel by viewModels<BookViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return BookViewModel(db.bookDao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LIB_COMPOSE_SQLITETheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    Navigation()
                    val state by viewModel.state.collectAsState()
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "BookScreenRoom"){
                        composable("BookScreenRoom"){
                            BookScreenRoom(state = state, navController = navController, onEvent = viewModel::onEvent )
                        }
                        composable("AddBookScreenRoom"){
                            AddBookScreenRoom(state = state, navController = navController, onEvent = viewModel::onEvent )
                        }
                    }
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