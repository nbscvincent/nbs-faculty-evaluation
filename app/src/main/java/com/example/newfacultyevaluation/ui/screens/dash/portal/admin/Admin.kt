package com.example.newfacultyevaluation.ui.screens.dash.portal.admin

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material.icons.rounded.LibraryAdd
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.People
import androidx.compose.material.icons.rounded.PeopleAlt
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newfacultyevaluation.ui.FacultyAppViewModelProvider
import com.example.newfacultyevaluation.ui.nav.AdminNav
import com.example.newfacultyevaluation.ui.nav.Main
import com.example.newfacultyevaluation.ui.nav.Portal
import com.example.newfacultyevaluation.ui.nav.StudentNav
import com.example.newfacultyevaluation.ui.screens.auth.LoginViewModel
import com.example.newfacultyevaluation.ui.screens.dash.portal.student.Courses
import com.example.newfacultyevaluation.ui.screens.dash.portal.student.Form
import com.example.newfacultyevaluation.ui.screens.dash.portal.student.SFaculty
import kotlinx.coroutines.launch
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

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val scope = rememberCoroutineScope()
    val context = LocalContext.current



    ModalNavigationDrawer(drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.White, // Change background color to whit
                drawerContentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.width(230.dp)
            ) {
                Text(
                    "Menu",
                    fontSize = 30.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .background(color = Color.Red)
                        .padding()
                        .wrapContentSize(Alignment.Center)
                        .padding(vertical = 16.dp), // Adjust vertical padding as needed
                    color = Color.White,
                )

                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clickable {
                                adminNavController.popBackStack()
                                adminNavController.navigate(AdminNav.UserList.name)
                                scope.launch { // Launch a coroutine scope
                                    drawerState.close() // Close the drawer after navigation
                                }
                            },
                        horizontalArrangement = Arrangement.spacedBy(30.dp ,Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Spacer(Modifier.width(5.dp))
                        Icon(
                            imageVector = Icons.Rounded.Person,
                            contentDescription = "User List",
                            modifier = Modifier
                                .size(30.dp),
                            tint = Color.Black
                        )
                        Text("User List", fontSize = 20.sp , color = Color.Black)
                    }
                    Row(
                        modifier = Modifier
                            .height(50.dp)
                            .clickable {
                                adminNavController.popBackStack()
                                adminNavController.navigate(AdminNav.AddUser.name)
                                scope.launch { // Launch a coroutine scope
                                    drawerState.close() // Close the drawer after navigation
                                }
                            }
                            .fillMaxWidth()
                        ,
                        horizontalArrangement = Arrangement.spacedBy(30.dp ,Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Spacer(Modifier.width(5.dp))
                        Icon(
                            imageVector = Icons.Rounded.PersonAdd,
                            contentDescription = "Add User",
                            modifier = Modifier
                                .size(25.dp),
                                    tint = Color.Black
                        )
                        Text("Add User", fontSize = 20.sp , color = Color.Black)
                    }

                    Row(
                        modifier = Modifier
                            .height(50.dp)
                            .clickable {
                                adminNavController.popBackStack()
                                adminNavController.navigate(AdminNav.AddCourse.name)
                                scope.launch { // Launch a coroutine scope
                                    drawerState.close() // Close the drawer after navigation
                                }
                            }
                            .fillMaxWidth()
                        ,
                        horizontalArrangement = Arrangement.spacedBy(30.dp ,Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Spacer(Modifier.width(5.dp))
                        Icon(
                            imageVector = Icons.Rounded.Bookmark,
                            contentDescription = "Add Course",
                            modifier = Modifier
                                .size(25.dp),
                            tint = Color.Black
                        )
                        Text("Add Course", fontSize = 20.sp , color = Color.Black)
                    }

                    Row(
                        modifier = Modifier
                            .height(50.dp)
                            .clickable {
                                adminNavController.popBackStack()
                                adminNavController.navigate(AdminNav.EditForm.name)
                                scope.launch { // Launch a coroutine scope
                                    drawerState.close() // Close the drawer after navigation
                                }
                            }
                            .fillMaxWidth()
                        ,
                        horizontalArrangement = Arrangement.spacedBy(30.dp ,Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Spacer(Modifier.width(5.dp))
                        Icon(
                            imageVector = Icons.Rounded.Edit,
                            contentDescription = "Edit Form",
                            modifier = Modifier
                                .size(25.dp),
                            tint = Color.Black
                        )
                        Text("Edit Form", fontSize = 20.sp , color = Color.Black)
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
                        horizontalArrangement = Arrangement.spacedBy(30.dp ,Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Spacer(Modifier.width(5.dp))
                        Icon(
                            imageVector = Icons.Rounded.Logout,
                            contentDescription = "Log Out",
                            modifier = Modifier
                                .size(25.dp),
                            tint = Color.Black
                        )
                        Text("Log Out", fontSize = 20.sp, color = Color.Black)
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
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Rounded.Menu,
                            contentDescription = "Menu",
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    scope.launch {
                                        drawerState.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                },
                            tint = Color.White
                        )
                    }
                }
            }
        ) {


            NavHost(
                navController = adminNavController,
                startDestination = AdminNav.UserList.name,
                modifier = Modifier.padding(it)
            ) {
                composable(route = AdminNav.UserList.name){
                    UserList()
                }
                composable(route = AdminNav.AddUser.name){
                    AddUser(adminNavController,viewModel)
                }
                composable(route = AdminNav.AddCourse.name){
                    AddCourses(adminNavController,viewModel)
                }
                composable(route = AdminNav.EditForm.name){
                    SessionScreen()
                }
            }

        }
    }


}
