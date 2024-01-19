package com.example.newfacultyevaluation.ui.screens.dash.portal.student

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.newfacultyevaluation.ui.screens.auth.LoginViewModel

@Composable
fun Profile (
    viewModel: LoginViewModel
){
    Text(text = "${viewModel.userID}")
}