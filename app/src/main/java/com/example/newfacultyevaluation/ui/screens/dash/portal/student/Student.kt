package com.example.newfacultyevaluation.ui.screens.dash.portal.student

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.ui.FacultyAppViewModelProvider
import com.example.newfacultyevaluation.ui.nav.Main
import com.example.newfacultyevaluation.ui.nav.StudentNav
import com.example.newfacultyevaluation.ui.screens.auth.LoginViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentPortal(
    viewModel: StudentViewModel  = viewModel(factory = FacultyAppViewModelProvider.StudentFactory),
    loginViewModel: LoginViewModel,
    navController: NavController
) {
    val studentNav = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
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
                            navController.navigate(StudentNav.PROFILE.name)
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
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Rounded.Settings,
                            contentDescription = "Settings",
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
                navController = studentNav,
                startDestination = StudentNav.HOME.name,
                modifier = Modifier.padding(it)
            ) {
                composable(route = StudentNav.HOME.name) {
                    SFaculty(loginViewModel = loginViewModel, studentNav, navController, viewModel)
//                    Form(navController = studentNav)
                }
                composable(StudentNav.COURSES.name) {
                    Courses(navController = studentNav)
                }
                composable(StudentNav.FORM.name) {
                    Form(navController = studentNav, viewModel)
                }

            }

        }
    }
}

@Composable
fun getCourses(courseList: List<Course>){
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
    ) {
        courseList.forEach { course ->
            Card(
                modifier = Modifier
                    .heightIn(min = 50.dp)
                    .fillMaxWidth()
                    .height(50.dp)

            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
//                    Text(text = course.name)
//                    if(!course.isCompleted)
//                        Icon(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "Arrow Right")
                }

            }
        }
    }


}