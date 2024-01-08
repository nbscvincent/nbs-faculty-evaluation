package com.example.newfacultyevaluation.ui.screens.dash.portal.admin

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newfacultyevaluation.ui.FacultyAppViewModelProvider
import com.example.newfacultyevaluation.ui.nav.Main
import com.example.newfacultyevaluation.ui.screens.auth.LoginViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPortal(
    navController: NavController,
    viewModel: AdminViewModel = viewModel(factory = FacultyAppViewModelProvider.AdminFactory),
    loginViewModel: LoginViewModel
) {
    var facultyID by rememberSaveable {
        mutableStateOf("")
    }
    var fullName by rememberSaveable {
        mutableStateOf("")
    }
    var program by rememberSaveable {
        mutableStateOf("")
    }

    val courses by remember {
        mutableStateOf(mutableStateListOf(""))
    }


    var password by remember {
        mutableStateOf("")
    }

    Column {
        Text(text = "Add FacultyID")
        OutlinedTextField(
            value = facultyID,
            onValueChange = { facultyID = it },
            label = { Text(text = "Enter Faculty ID") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
        )
        Text(text = "Full Name")
        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text(text = "Enter Full Name") }
        )
        Text(text = "Password")
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Enter Password") }
        )
        Text(text = "Add Program")
        OutlinedTextField(
            value = program,
            onValueChange = { program = it },
            label = { Text(text = "Enter Program") }
        )

        Button(onClick = {
            viewModel.fullName = fullName
                viewModel.password = password
                viewModel.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))

                courses.forEach { course ->
                viewModel.facultyID = facultyID
                    viewModel.program = program
                    viewModel.course = course
                    viewModel.setFaculty()
                }
            }) {
            Text(text = "Add Faculty")
            }

        val context = LocalContext.current
        Button(onClick = {
            loginViewModel.status = false
                val preferences = context.getSharedPreferences("prefs",0)
                preferences.edit()
                    .clear()
                    .apply()

                navController.popBackStack()
                navController.navigate(Main.AUTH.name)
            }) {

        }
    }
}
