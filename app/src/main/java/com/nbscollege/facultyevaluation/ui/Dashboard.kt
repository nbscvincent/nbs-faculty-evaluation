package com.nbscollege.facultyevaluation.ui

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Person2
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nbscollege.facultyevaluation.navigation.routes.DashboardRoute
import com.nbscollege.facultyevaluation.ui.theme.fontFamily
import com.nbscollege.facultyevaluation.user.ui.Profile
import com.nbscollege.facultyevaluation.user.ui.currentAcct
import kotlin.system.exitProcess


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Dashboard(
    navController: NavController
) {
    val navControllerDash = rememberNavController()
    var selected by remember {
        mutableStateOf(true)
    }
    var item by remember {
        mutableIntStateOf(1)
    }


    var backHandlingEnabled by remember { mutableStateOf(true) }
//    NavHost(navController = navController, startDestination = MainScreen.Dashboard.name){
//        composable(route = MainScreen.Dashboard.name){
//            Dashboard(navController = navController)
//        }
//    }
    var pressedbackCount by remember {
        mutableIntStateOf(0)
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    BackHandler(backHandlingEnabled, onBack = {
        pressedbackCount++
        println(pressedbackCount)
    })

    if(showDialog){
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            dismissButton = {
                Button(onClick = {
                    showDialog = false
                }){
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
               Button(onClick = {
                   exitProcess(0)
               }) {
                  Text(text = "OK")
               }
            },
            title = {
                Text(
                    text = "Warning!"
                )},
            text = {
                Text(text = "Do you want to exit?")
            }
        )
    }
    else if(pressedbackCount == 2){
        showDialog = true
        pressedbackCount = 0
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),

                title = {
                Row (
                    modifier = Modifier
                        .height(100.dp),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(imageVector = Icons.Rounded.Person2, contentDescription = "Icon Profile", tint = Color.White, modifier = Modifier.size(30.dp))
                    Text(text = "Welcome, $currentAcct", color = Color.White, fontSize = 20.sp, fontFamily = fontFamily)
                }
            },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Red
                )

            )

        },
        bottomBar = {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(50.dp)
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = if(selected && item == 1){
                            Icons.Filled.Home
                        }
                        else {
                            Icons.Outlined.Home
                        },
                        contentDescription = "Home", tint = Color.Black, modifier = Modifier
                            .size(30.dp)
                            .clickable(
                                onClick = {
                                    item = 1
                                    selected  = true
                                    navControllerDash.navigate(DashboardRoute.Home.name)
                                }
                            ))
                    Icon(
                        imageVector =
                        if(selected && item == 2){
                            Icons.Filled.Person
                        }
                        else {
                            Icons.Outlined.Person
                        },
                        contentDescription = "Profile",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable(
                                onClick = {
                                    item = 2
                                    selected = true
                                    navControllerDash.navigate(DashboardRoute.Profile.name)
                                }
                            ))
                    Icon(
                        imageVector = if(selected && item == 3){
                        Icons.Filled.Settings
                    }
                    else {
                        Icons.Outlined.Settings
                    }, contentDescription = "Settings",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable(
                                onClick = {
                                    selected = true
                                    item = 3
                                    navControllerDash.navigate(DashboardRoute.Settings.name)
                                }
                            ))
                }

            }
        ) {
            NavHost(navController = navControllerDash, startDestination = DashboardRoute.Home.name, modifier = Modifier.padding(it)){

                composable(route = DashboardRoute.Home.name){
                    HomeDash(navController = navController)
                }
                composable(route = DashboardRoute.Profile.name){
                    Profile(navController = navController)
                }
                composable(route = DashboardRoute.Settings.name){
                    Settings(navController = navController)
                }


            }


    }

}


