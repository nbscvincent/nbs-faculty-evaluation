package com.example.newfacultyevaluation.ui.screens.dash.portal.student

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.newfacultyevaluation.ui.nav.Auth
import com.example.newfacultyevaluation.ui.nav.Main
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
    Text("SFaculthy")
    val context = LocalContext.current

    Button(
        onClick = {
            viewModel.status = false
            val preferences = context.getSharedPreferences("prefs",0)
            preferences.edit()
                .clear()
                .apply()

            mainNav.popBackStack()
            mainNav.navigate(Main.AUTH.name)
        }
    ) {
        Text(text = "Log out")
    }
}