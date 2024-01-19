package com.example.newfacultyevaluation.ui.screens.dash.portal.admin

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newfacultyevaluation.ui.FacultyAppViewModelProvider
import com.example.newfacultyevaluation.ui.nav.AdminNav
import com.example.newfacultyevaluation.ui.nav.Main
import com.example.newfacultyevaluation.ui.nav.Portal
import com.example.newfacultyevaluation.ui.screens.auth.LoginViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPortal(
    navController: NavController,
    viewModel: AdminViewModel = viewModel(factory = FacultyAppViewModelProvider.AdminFactory),
    loginViewModel: LoginViewModel
) {
    BackHandler{
        navController.popBackStack()
        navController.navigate(Portal.ADMIN.name)
    }
    val adminNavController = rememberNavController()
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){

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
                    Text(text = "Log out")
                }
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){

                    Button(onClick = {
                        adminNavController.popBackStack()
                        adminNavController.navigate(AdminNav.UserList.name)
                    }) {
                        Text(text = "User List")
                    }
                    Button(onClick = {
                        adminNavController.popBackStack()
                        adminNavController.navigate(AdminNav.AddCourse.name)
                    }) {
                        Text(text = "+ Course")
                    }
                    Button(onClick = {
                        adminNavController.popBackStack()
                        adminNavController.navigate(AdminNav.EditForm.name)
                    }) {
                        Text(text = "Edit Form")
                    }
                }

            }
        }

    ){

        NavHost(navController = adminNavController, startDestination = AdminNav.UserList.name,modifier = Modifier.padding(it)){

            composable(route = AdminNav.UserList.name){
                UserList()
            }
            composable(route = AdminNav.AddCourse.name){
                AddCourses(viewModel)
            }
            composable(route = AdminNav.EditForm.name){
                EditForm()
            }
        }

    }


}
