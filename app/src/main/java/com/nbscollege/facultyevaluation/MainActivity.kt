package com.nbscollege.facultyevaluation

import Registration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.nbscollege.facultyevaluation.model.ForgotPass
import com.nbscollege.facultyevaluation.model.HomePage
import com.nbscollege.facultyevaluation.model.Nav
import com.nbscollege.facultyevaluation.model.Question
import com.nbscollege.facultyevaluation.ui.theme.FacultyEvaluationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FacultyEvaluationTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color(0xffD22023)
                ) {
<<<<<<< Updated upstream
                    Question()
//                      Nav()
//                    Registration()
//                    ForgotPass()
=======

//                    Login()
                    FacultyApp()
//                    if(!screenViewModel.isUserSignedIn()){
//                        Login()
//                    }else{
//                        Dashboard()
//                    }
                    //Login()
                    //HomePage()
                    //Registration()
                    //ForgotPass()
                    //UserListAdd()
                    //UserList()
                    //SplashScreen()
                    //UserInfo()

>>>>>>> Stashed changes
                }
            }
        }
    }
}
