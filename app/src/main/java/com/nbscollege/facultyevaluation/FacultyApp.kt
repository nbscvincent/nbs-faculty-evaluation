package com.nbscollege.facultyevaluation

import ScreenViewModel
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nbscollege.facultyevaluation.ui.Dashboard
import com.nbscollege.facultyevaluation.user.ui.ForgotPass
import com.nbscollege.facultyevaluation.ui.HomePage
import com.nbscollege.facultyevaluation.user.ui.Login
import com.nbscollege.facultyevaluation.registration.ui.Registration
import com.nbscollege.facultyevaluation.ui.SplashScreen
import com.nbscollege.facultyevaluation.navigation.routes.MainScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ComposableNavGraphInComposeScope")
@Composable
fun FacultyApp(){
    val viewModel: ScreenViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainScreen.Splash.name
    ){

        composable(route = MainScreen.Home.name){
            HomePage(navController = navController, viewModel)
        }
        composable(route = MainScreen.Login.name){

            Login(navController = navController)
        }
        composable(route = MainScreen.Splash.name){
            SplashScreen(navController = navController)
        }
        composable(route = MainScreen.Registration.name){
            Registration(navController = navController)
        }
        composable(route = MainScreen.ForgotPass.name){
            ForgotPass(navController = navController)
        }
        composable(route = MainScreen.Dashboard.name){
            Dashboard(navController)
        }

    }


}

