package com.example.newfacultyevaluation.ui.screens.dash.portal.student

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.newfacultyevaluation.ui.screens.auth.LoginViewModel

@Composable
fun Profile (
    viewModel: LoginViewModel
){
    Column(

    ) {
        Text(text = viewModel.userID)
        Text(text = viewModel.fullName)
        Text(text = viewModel.password)
        Text(text = viewModel.year)
        Text(text = viewModel.role)

    }

}