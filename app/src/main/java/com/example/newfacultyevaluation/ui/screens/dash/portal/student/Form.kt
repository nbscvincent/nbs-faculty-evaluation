package com.example.newfacultyevaluation.ui.screens.dash.portal.student

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.newfacultyevaluation.ui.nav.StudentNav

@Composable
fun Form(
    navController: NavController
) {
    BackHandler {
        navController.popBackStack()
        navController.navigate(StudentNav.HOME.name)
    }
}