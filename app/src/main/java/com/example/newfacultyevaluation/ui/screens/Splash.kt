package com.example.newfacultyevaluation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.newfacultyevaluation.R
import com.example.newfacultyevaluation.ui.nav.Main
import com.example.newfacultyevaluation.ui.screens.auth.LoginViewModel
import kotlinx.coroutines.delay

@Composable
fun Splash(
    navController: NavController,
    loginViewModel: LoginViewModel
) {

    var appearSplash by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = appearSplash){
        delay(2000)
        appearSplash = true
    }
        Column(
           modifier = Modifier
               .fillMaxSize()
               //.background(MaterialTheme.colorScheme.background),
               .background(color = Color.White),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(painterResource(id = R.drawable.nbsc_logo),
                contentDescription = "Logo",
                modifier = Modifier.graphicsLayer(
                    translationX = 15f
                ), tint = Color.Unspecified)

            LinearProgressIndicator(
                modifier = Modifier,
                color = Color.Red,
                trackColor = Color.Unspecified
            )

        }
        if(appearSplash){
            if(!loginViewModel.status){
                navController.navigate(Main.AUTH.name)
            }else {
                navController.navigate(Main.PORTAL.name)
            }
        }

}