package com.nbscollege.facultyevaluation.model

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.activity.compose.BackHandler
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nbscollege.facultyevaluation.navigation.routes.MainScreen
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
            bottomBar = {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ){
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color = Color.White)
                        .padding(horizontal = 20.dp)){
                        Text(text = "Hello")
                    }
                }

            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
//            TextButton(onClick = {
//
//            }) {
//                Icon(
//                    imageVector = Icons.Rounded.ArrowBack,
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(50.dp)
//                        .padding(10.dp),
//                    tint = Color.White
//                )
//            }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "PAGE UNDER CONSTRUCTION",
                        color = Color.White,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    )
                }


            }

    }

}