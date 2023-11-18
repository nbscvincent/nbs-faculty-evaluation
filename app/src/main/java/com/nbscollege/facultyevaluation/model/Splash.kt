package com.nbscollege.facultyevaluation.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nbscollege.facultyevaluation.R
import com.nbscollege.facultyevaluation.navigation.routes.MainScreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController = rememberNavController()
) {

    LaunchedEffect(key1 = true) {
        delay(2000L)
        navController.navigate(MainScreen.Home.name)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.nbsc_logo),
            contentDescription = "NBS College",
            modifier = Modifier
                .size(1400.dp,350.dp)
        )

        LinearProgressIndicator(
            modifier = Modifier,
            color = Color.Green,
            trackColor = Color.Gray
        )

    }
}