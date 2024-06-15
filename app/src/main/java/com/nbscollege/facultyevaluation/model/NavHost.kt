package com.nbscollege.facultyevaluation.model

import Registration
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ComposableNav(startDest: String,
                  navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = startDest
    ){
        composable("Home"){
            HomePage()
        }
        composable("Registration"){
            Registration()
        }
    }
}