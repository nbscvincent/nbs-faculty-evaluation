package com.nbscollege.facultyevaluation.model

import Registration
import android.graphics.ColorSpace
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nbscollege.facultyevaluation.R


@Composable
fun HomePage(){
        val navHost: NavHostController
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
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(300.dp, 400.dp)
                        .zIndex(2f))
                Spacer(modifier = Modifier.height(100.dp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                    ),
                    modifier = Modifier
                        .size(400.dp, 100.dp)
                        .zIndex(2f)

                ){
                    Text("SIGN IN", fontSize = 35.sp)
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
                painter = painterResource(id = R.drawable.logo),
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
                Text(text = "Don't have an account?", color = Color.White, fontSize = 25.sp)
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
//                        navHost.navigate("Register")

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier.size(350.dp, 60.dp)
                ) {
                    Text(text = "REGISTER", fontSize = 25.sp)
                }
                Spacer(modifier = Modifier.height(80.dp))
            }

        }

}
@Composable
fun ComposablNav(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "First"){
        composable("Home"){
            HomePage()
        }
        composable("Registration"){
            Registration()
        }
    }
}

