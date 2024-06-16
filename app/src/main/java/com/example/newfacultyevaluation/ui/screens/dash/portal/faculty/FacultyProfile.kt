package com.example.newfacultyevaluation.ui.screens.dash.portal.faculty

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.newfacultyevaluation.ui.screens.auth.LoginViewModel

@Composable
fun FacultyProfile (
    viewModel: LoginViewModel
){


    Column {
        Text(text = viewModel.userID)
        Text(text = viewModel.fullName)
        Text(text = viewModel.role)
    }

}