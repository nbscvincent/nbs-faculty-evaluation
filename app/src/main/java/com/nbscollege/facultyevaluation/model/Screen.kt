package com.nbscollege.facultyevaluation.model

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object RegScreen: Screen("reg_screen")
    object LogInScreen: Screen("logIn_screen")
}
