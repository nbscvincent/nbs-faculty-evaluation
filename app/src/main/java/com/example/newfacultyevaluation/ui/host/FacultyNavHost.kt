package com.example.newfacultyevaluation.ui.host

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newfacultyevaluation.ui.nav.Main
import com.example.newfacultyevaluation.ui.nav.Auth
import com.example.newfacultyevaluation.ui.screens.Splash
import com.example.newfacultyevaluation.ui.screens.auth.Login
import com.example.newfacultyevaluation.ui.screens.auth.Register
import com.example.newfacultyevaluation.ui.screens.dash.Portal
import com.example.newfacultyevaluation.ui.nav.Portal
import com.example.newfacultyevaluation.ui.screens.dash.portal.student.StudentPortal




@Composable
fun FacultyApp(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Main.SPLASH.name){
        composable(route = Main.SPLASH.name){ Splash(navController = navController) }
        navigation(startDestination = Auth.LOGIN.name, route = Main.AUTH.name){
            composable(Auth.LOGIN.name){
                Login(navController = navController)
            }
            composable(Auth.REGISTER.name){
                Register(navController = navController)
            }
        }
        composable(
            route = Main.PORTAL.name+"{role}",
            arguments = listOf(navArgument("role") { type = NavType.StringType })
        ){
            it.arguments?.getString("role")?.let { role ->  Portal(navController = navController, role) }
        }

        composable(route = Portal.STUDENT.name){
            StudentPortal()
        }
        composable(route = Portal.FACULTY.name){

        }
        composable(route = Portal.ADMIN.name){

        }

    }
}