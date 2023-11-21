package com.nbscollege.facultyevaluation.model

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Person2
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nbscollege.facultyevaluation.model.data.LoginReq
import kotlin.system.exitProcess


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Dashboard(
    navController: NavHostController
) {
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
                    .padding(20.dp)
                    .fillMaxWidth(),

                title ={
                Row (
                    modifier = Modifier
                        .height(100.dp),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(imageVector = Icons.Rounded.Person2, contentDescription = "Icon Profile", tint = Color.White, modifier = Modifier.size(30.dp))
                    Text(text = "Welcome, $currentAcct", color = Color.White, fontSize = 25.sp)
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
                    .height(50.dp)
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceAround
                ){
                    Icon(imageVector = Icons.Rounded.Home, contentDescription = "Home", tint = Color.Black, modifier = Modifier.size(30.dp))
                    Icon(imageVector = Icons.Rounded.Person, contentDescription = "Profile", tint = Color.Black, modifier = Modifier.size(30.dp))
                    Icon(imageVector = Icons.Rounded.Settings, contentDescription = "Settings", tint = Color.Black, modifier = Modifier.size(30.dp))
                }

            }
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(it)
            ){
                Spacer(modifier = Modifier.height(50.dp))
                Text(text = "Dashboard", color = Color.Black, fontSize = 30.sp, modifier = Modifier.padding(horizontal = 20.dp))
            }

    }

}