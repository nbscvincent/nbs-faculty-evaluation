package com.example.newfacultyevaluation.ui.screens.dash.portal.student

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.newfacultyevaluation.ui.nav.StudentNav
import com.example.newfacultyevaluation.ui.screens.auth.LoginViewModel

@Composable
fun SFaculty(
    viewModel: LoginViewModel,
    studentNavController: NavController,
    mainNav: NavController,
) {
    BackHandler {
        studentNavController.popBackStack()
        studentNavController.navigate(StudentNav.HOME.name)
    }


}