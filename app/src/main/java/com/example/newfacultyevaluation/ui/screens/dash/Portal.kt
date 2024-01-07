package com.example.newfacultyevaluation.ui.screens.dash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.newfacultyevaluation.ui.nav.Portal

@Composable
fun Portal(
    navController: NavController,
    role: String
) {
    navController.popBackStack()
    println(role)
    when(role){
        "Student" -> {
            navController.navigate(Portal.STUDENT.name)
        }
        "Faculty" -> {
            navController.navigate(Portal.FACULTY.name)
        }
        "Admin" -> {
            navController.navigate(Portal.ADMIN.name)
        }
    }
}