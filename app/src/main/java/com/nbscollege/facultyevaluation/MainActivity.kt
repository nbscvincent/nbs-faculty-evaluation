package com.nbscollege.facultyevaluation

import ScreenViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nbscollege.facultyevaluation.ui.theme.FacultyEvaluationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screenViewModel: ScreenViewModel by viewModels()
        screenViewModel.runSplashScreen()

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                screenViewModel.loading.value
            }
        }
        setContent {
            FacultyEvaluationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color(0xffD22023)
                ) {
                    FacultyApp()
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
