package com.nbscollege.facultyevaluation

import ScreenViewModel
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nbscollege.facultyevaluation.model.Dashboard
import com.nbscollege.facultyevaluation.model.Login
import com.nbscollege.facultyevaluation.navigation.routes.MainScreen
import com.nbscollege.facultyevaluation.ui.theme.FacultyEvaluationTheme
import kotlinx.coroutines.delay
import javax.security.auth.login.LoginException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screenViewModel: ScreenViewModel by viewModels()

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                screenViewModel.loading.value
            }

        }

        screenViewModel.runSplashScreen()

        setContent {
            FacultyEvaluationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color.White
                ) {

                    FacultyApp()
//                    if(!screenViewModel.isUserSignedIn()){
//                        Login()
//                    }else{
//                        FacultyApp()
//                    }
                    //Login()
                    //HomePage()
                    //Registration()
                    //ForgotPass()
                    //UserListAdd()
                    //UserList()
                    //SplashScreen()
                    //UserInfo()

                }
            }
        }
    }
}

