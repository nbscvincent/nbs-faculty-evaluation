package com.nbscollege.facultyevaluation

import ScreenViewModel
import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nbscollege.facultyevaluation.model.Dashboard
import com.nbscollege.facultyevaluation.model.ForgotPass
import com.nbscollege.facultyevaluation.model.Form
import com.nbscollege.facultyevaluation.model.HomePage
import com.nbscollege.facultyevaluation.model.Login
import com.nbscollege.facultyevaluation.model.Registration
import com.nbscollege.facultyevaluation.model.SplashScreen
import com.nbscollege.facultyevaluation.navigation.routes.DashboardRoute
import com.nbscollege.facultyevaluation.navigation.routes.MainScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ComposableNavGraphInComposeScope")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacultyApp(){
    val viewModel: ScreenViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainScreen.Dashboard.name
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
        composable(route = DashboardRoute.Form.name){
            Form(navController)
        }





//        composable(route = MainScreen.UserList.name){
//            Dashboard(navController = navController)
//        }
//        composable(route = MainScreen.UserListAdd.name){
//            Dashboard(navController = navController)
//        }


    }


}

