package com.example.newfacultyevaluation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.newfacultyevaluation.ui.host.FacultyApp
import com.example.newfacultyevaluation.ui.screens.dash.portal.admin.AddUser
import com.example.newfacultyevaluation.ui.theme.NewFacultyEvaluationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewFacultyEvaluationTheme() {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                    //color = MaterialTheme.colorScheme.background,
                    color = Color.White,
                ) {
                    FacultyApp()

                }
            }
        }


    }
}

