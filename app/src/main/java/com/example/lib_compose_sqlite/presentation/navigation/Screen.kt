package com.example.lib_compose_sqlite.presentation.navigation
    sealed class Screen(val route:String) {
        object HomeScreen : Screen("home_screen")
        object AdminSignupScreen : Screen("admin_signup_screen")
        object AdminLoginScreen : Screen("admin_login_screen")
        object AdminScreen : Screen("admin_screen")
        object StudentSignupScreen : Screen("student_signup_screen")
        object StudentLoginScreen : Screen("student_login_screen")
        object StudentScreen : Screen("student_screen")
        object BooksScreen : Screen("books_screen")
        object IssueBookScreen : Screen("issue_books_screen")
        object AddBookScreen : Screen("add_books_screen")
        object BookRemoveScreen : Screen("removebooks_screen")
        object StudentBooksScreen : Screen("student_books_screen")
        object StudentMyBookScreen : Screen("student_mybooks_screen")
        object ReturnBookScreen : Screen("return_book_screen")
}