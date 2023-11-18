package com.nbscollege.facultyevaluation.model

import ScreenViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
    navController: NavHostController = rememberNavController(),
    screenViewModel: ScreenViewModel
) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.nbsc_logo),
                contentDescription = "",
                modifier = Modifier
                    .size(400.dp, 280.dp)
                    .graphicsLayer(translationX = 6f))
            Spacer(modifier = Modifier.height(60.dp))
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(
                    onClick = {
                        navController.navigate(MainScreen.Login.name)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    modifier = Modifier
                        .size(280.dp, 50.dp),
                    shape = RoundedCornerShape(10.dp)

                ){
                    Text("Get Started", fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White)


                }
//                Column (
//                    verticalArrangement = Arrangement.spacedBy(10.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ){
//                    Text(text = "Don't have an account?", color = Color.Black,
//                        fontSize = 15.sp, fontWeight = FontWeight.W300)
//
//
//                    Button(
//                        onClick = {
//                            navController.navigate(MainScreen.Registration.name)
//                        },
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color.Red
//                        ),
//                        modifier = Modifier.size(280.dp, 50.dp),
//                        shape = RoundedCornerShape(10.dp)
//                    ) {
//                        Text(text = "REGISTER", fontSize = 20.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color.White)
//                    }
//                }

            }


        }

//    Box(
//        modifier = Modifier
////                .border(1.dp, Color.Black)
//            .zIndex(1f)
//            .graphicsLayer(translationY = 200f, translationX = -350f)
//    ){
//
//        Image(
//            painter = painterResource(id = R.drawable.nbsc_logo_main),
//            contentDescription = "",
//            colorFilter = ColorFilter.tint(color = Color(0x33000000)),
//            modifier = Modifier
//                .size(1500.dp, 2000.dp)
//                .rotate(56.03f)
////                    .border(1.dp, Color.Black)
//        )
//    }



    }




