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
import androidx.lifecycle.SavedStateHandle
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
import com.example.lib_compose_sqlite.data.local.LibraryDatabase
import com.example.lib_compose_sqlite.presentation.navigation.LibraryNavHost
import com.example.lib_compose_sqlite.presentation.screens.books.AddBookScreenRoom
import com.example.lib_compose_sqlite.presentation.screens.books.BookScreenRoom
import com.example.lib_compose_sqlite.presentation.screens.books.BookViewModel




class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LIB_COMPOSE_SQLITETheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    LibraryNavHost()
                }
            }
        }
    }
}
