package com.example.newfacultyevaluation.ui.screens.dash.portal.student

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newfacultyevaluation.data.Course
import com.example.newfacultyevaluation.ui.nav.StudentNav
import com.example.newfacultyevaluation.ui.screens.auth.LoginViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentPortal(
    loginViewModel: LoginViewModel,
    navController: NavController
) {
    val studentNav = rememberNavController()
    var openDrawer by remember {
        mutableStateOf(false)
    }

    Scaffold (
        topBar = {
            NavigationBar(
                containerColor = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                openDrawer = true
                            }
                    )
                    Icon(imageVector = Icons.Rounded.Settings, contentDescription = "Settings", modifier = Modifier.size(50.dp))
                }
            }
        }
    ){


        NavHost(navController = studentNav, startDestination = StudentNav.HOME.name, modifier = Modifier.padding(it)){
            composable(route = StudentNav.HOME.name){
                SFaculty(loginViewModel,studentNav, navController)
            }
            composable(StudentNav.COURSES.name){
                Courses(navController = studentNav)
            }
            composable(StudentNav.FORM.name){
                Form(navController = studentNav)
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
                    Text(text = course.name)
                    if(!course.isCompleted)
                        Icon(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "Arrow Right")
                }

            }
        }
    }


}