package com.nbscollege.facultyevaluation.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nbscollege.facultyevaluation.R
import com.nbscollege.facultyevaluation.navigation.routes.MainScreen


@Composable
fun HomePage(
    navController: NavHostController = rememberNavController()
) {

    Box(
        modifier = Modifier
            .border(1.dp, Color.Black)
            .zIndex(2f)
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Image(
                painter = painterResource(id = R.drawable.nbsc_logo_main),
                contentDescription = "",
                modifier = Modifier
                    .size(300.dp, 280.dp)
                    .zIndex(2f))
            Spacer(modifier = Modifier.height(60.dp))
            Button(
                onClick = {
                          navController.navigate("Login")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                ),
                modifier = Modifier
                    .size(280.dp, 50.dp)
                    .zIndex(2f)

            ){
                Text("SIGN IN", fontSize = 25.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black)


            }
        }

    }

    Box(
        modifier = Modifier
//                .border(1.dp, Color.Black)
            .zIndex(1f)
            .graphicsLayer(translationY = 200f, translationX = -350f)
    ){

        Image(
            painter = painterResource(id = R.drawable.nbsc_logo_main),
            contentDescription = "",
            colorFilter = ColorFilter.tint(color = Color(0x33000000)),
            modifier = Modifier
                .size(1500.dp, 2000.dp)
                .rotate(56.03f)
//                    .border(1.dp, Color.Black)
        )
    }
    Box(
        modifier = Modifier
            .graphicsLayer(translationY = 00f)
            .border(1.dp, color = Color.Black)
            .zIndex(3f),
        contentAlignment = Alignment.BottomCenter

    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Don't have an account?", color = Color.White,
                fontSize = 12.sp, fontWeight = FontWeight.SemiBold)

            Spacer(modifier = Modifier.height(5.dp))
            Button(
                onClick = {
                    navController.navigate("Registration")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray
                ),
                modifier = Modifier.size(280.dp, 50.dp)
            ) {
                Text(text = "REGISTER", fontSize = 25.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black)
            }
            Spacer(modifier = Modifier.height(80.dp))
        }

    }

}


