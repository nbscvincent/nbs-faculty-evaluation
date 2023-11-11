package com.nbscollege.facultyevaluation

import ScreenViewModel
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nbscollege.facultyevaluation.navigation.routes.MainScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nbscollege.facultyevaluation.model.Dashboard
import com.nbscollege.facultyevaluation.model.ForgotPass
import com.nbscollege.facultyevaluation.model.HomePage
import com.nbscollege.facultyevaluation.model.Login
import com.nbscollege.facultyevaluation.model.Registration
import com.nbscollege.facultyevaluation.model.SplashScreen



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacultyApp(
    viewModel: ScreenViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
//    val viewModel: ScreenViewModel = viewModel()
//    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainScreen.Login.name,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(900)
            )
},
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(900)
            )
        }
    ){
        composable(route = MainScreen.Home.name){
            HomePage(navController = navController)
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
            Dashboard(navController = navController)
        }
//        composable(route = MainScreen.UserList.name){
//            Dashboard(navController = navController)
//        }
//        composable(route = MainScreen.UserListAdd.name){
//            Dashboard(navController = navController)
//        }


    }

}

