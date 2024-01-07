package com.example.newfacultyevaluation.ui.host

import android.content.SharedPreferences
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newfacultyevaluation.ui.FacultyAppViewModelProvider
import com.example.newfacultyevaluation.ui.nav.Main
import com.example.newfacultyevaluation.ui.nav.Auth
import com.example.newfacultyevaluation.ui.screens.Splash
import com.example.newfacultyevaluation.ui.screens.auth.Login
import com.example.newfacultyevaluation.ui.screens.auth.Register
import com.example.newfacultyevaluation.ui.screens.dash.Portal
import com.example.newfacultyevaluation.ui.nav.Portal
import com.example.newfacultyevaluation.ui.screens.auth.LoginViewModel
import com.example.newfacultyevaluation.ui.screens.dash.portal.student.StudentPortal
import java.util.Locale


@Composable
fun FacultyApp(){

    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel(factory = FacultyAppViewModelProvider.LoginFactory)

    val context = LocalContext.current
    val preferences = remember { context.getSharedPreferences("prefs", 0)}

    if (preferences.getBoolean("status", false)){
        loginViewModel.status = true
        loginViewModel.userID = preferences.getString("userID", "") ?: ""
        loginViewModel.password = preferences.getString("password", "") ?: ""
        loginViewModel.role = preferences.getString("role","") ?: ""
    }


    println(loginViewModel.role)
    NavHost(navController = navController, startDestination = Main.SPLASH.name){

        composable(route = Main.SPLASH.name){ Splash(navController = navController, loginViewModel) }

        navigation(startDestination = Auth.LOGIN.name,route = Main.AUTH.name){
            composable(Auth.LOGIN.name){
                Login(viewModel = loginViewModel,navController = navController)
            }
            composable(Auth.REGISTER.name){
                Register(navController = navController)
            }
        }



        navigation(
            startDestination = when(loginViewModel.role.lowercase()){
                "student" -> {Portal.STUDENT.name}
                "admin" -> {Portal.ADMIN.name}
                "faculty" -> Portal.FACULTY.name
                else -> "test"
            },
            route = Main.PORTAL.name
        ){

            composable(route = "test"){
                println("Test")
                println(preferences.all)
                val context1 = LocalContext.current
                Button(
                    onClick = {
                        loginViewModel.status = false
                        val prefer = context1.getSharedPreferences("prefs", 0)
                        prefer.edit()
                            .clear()
                            .apply()

                        navController.popBackStack()
                        navController.navigate(Main.AUTH.name)
                    }
                ) {
                    Text(text = "Test")
                }
            }
            composable(route = Portal.STUDENT.name){
                println("Student: ${preferences.all}")
                StudentPortal(loginViewModel,navController)
            }
            composable(route = Portal.FACULTY.name){
                println("faculty: ${preferences.all}")
                val context1 = LocalContext.current
                Button(
                    onClick = {
                        loginViewModel.status = false
                        val prefer = context1.getSharedPreferences("prefs", 0)
                        prefer.edit()
                            .clear()
                            .apply()

                        navController.popBackStack()
                        navController.navigate(Main.AUTH.name)
                    }
                ) {
                    Text(text = "Faculty Log out")
                }
            }
            composable(route = Portal.ADMIN.name){

            }
        }


    }

}

