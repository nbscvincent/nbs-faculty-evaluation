package com.example.newfacultyevaluation.ui.screens.dash.portal.faculty

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newfacultyevaluation.ui.FacultyAppViewModelProvider
import com.example.newfacultyevaluation.ui.nav.FacultyNav
import com.example.newfacultyevaluation.ui.nav.Main
import com.example.newfacultyevaluation.ui.screens.auth.LoginViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacultyProfile (
//    viewModel: LoginViewModel
    loginViewModel: LoginViewModel,
    viewModel: FacultyViewModel = viewModel(factory = FacultyAppViewModelProvider.FacultyFactory),

){
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val context = LocalContext.current


    ModalNavigationDrawer(drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.primary,
                drawerContentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.width(230.dp)
            ) {
                Text("Settings", fontSize = 30.sp, modifier = Modifier.padding(20.dp))
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
                ) {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clickable {
                                navController.popBackStack()
                                navController.navigate(FacultyNav.PROFILE.name)
                            },
                        horizontalArrangement = Arrangement.spacedBy(40.dp ,Alignment.Start)
                    ){
                        Spacer(Modifier.width(5.dp))
                        Icon(
                            imageVector = Icons.Rounded.Person,
                            contentDescription = "Profile",
                            modifier = Modifier
                                .size(30.dp)
                        )
                        Text("Profile", fontSize = 20.sp)
                    }

                    Row(
                        modifier = Modifier
                            .height(50.dp)
                            .clickable {
                                loginViewModel.status = false
                                val preferences = context.getSharedPreferences("prefs", 0)
                                preferences
                                    .edit()
                                    .clear()
                                    .apply()
                                navController.popBackStack()
                                navController.navigate(Main.AUTH.name)
                            }
                            .fillMaxWidth()
                        ,
                        horizontalArrangement = Arrangement.spacedBy(40.dp ,Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Spacer(Modifier.width(5.dp))
                        Icon(
                            imageVector = Icons.Rounded.Logout,
                            contentDescription = "Log Out",
                            modifier = Modifier
                                .size(25.dp)
                        )
                        Text("Log Out", fontSize = 20.sp)
                    }
                }


            }
        }) {

        Scaffold(
            topBar = {
                NavigationBar(
                    containerColor = Color.Red,
                    modifier = Modifier.height(70.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Faculty Profile", fontSize = 30.sp)
                        Icon(
                            imageVector = Icons.Rounded.Settings,
                            contentDescription = "Settings",
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    scope.launch {
                                        // Handle settings click
                                    }
                                },
                            tint = Color.White
                        )
                    }
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "User ID",
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = loginViewModel.userID, fontSize = 20.sp)
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Full Name",
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = loginViewModel.fullName, fontSize = 20.sp)
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Role",
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = loginViewModel.role, fontSize = 20.sp)
                    }
                }
            }
        }

//    Column {
//        Text(text = viewModel.userID)
//        Text(text = viewModel.fullName)
//        Text(text = viewModel.role)
//    }

    }
}