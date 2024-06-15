package com.example.newfacultyevaluation.ui.screens.dash.portal.student

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.newfacultyevaluation.ui.screens.auth.LoginViewModel

@Composable
fun Profile (
    viewModel: LoginViewModel
){


    Column {
        Text(text = viewModel.userID)
        Text(text = viewModel.fullName)
        Text(text = viewModel.selectedCourse)
        Text(text = "${viewModel.year} year")
        Text(text = viewModel.role)
    }

}