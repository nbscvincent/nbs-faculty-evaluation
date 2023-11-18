package com.nbscollege.facultyevaluation.model

import android.text.InputType
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nbscollege.facultyevaluation.R
import com.nbscollege.facultyevaluation.model.data.LoginReq
import com.nbscollege.facultyevaluation.navigation.routes.MainScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    navController: NavHostController

){

    var studentNo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(value = false) }
    var error by remember {
        mutableStateOf(false)
    }


//    Box(
//        modifier = Modifier
//            .border(1.dp, Color.Black)
//            .zIndex(2f)
//            .fillMaxWidth(),
//        contentAlignment = Alignment.TopCenter
//
//    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
//            Box(modifier = Modifier.fillMaxWidth()) {
//
//                TextButton(onClick = {
//                    navController.navigate(MainScreen.Home.name)
//                }) {
//                    Icon(
//                        imageVector = Icons.Rounded.ArrowBack,
//                        contentDescription = null,
//                        modifier = Modifier
//                            .size(45.dp)
//                            .padding(10.dp),
////                            .border(1.dp, Color.Black),
//                        tint = Color.DarkGray
//                    )
//                }
//            }

            Image(
                painter = painterResource(id = R.drawable.nbsc_logo),
                contentDescription = "",
                modifier = Modifier
//                    .size(225.dp ,300.dp)
                    .size(220.dp, 220.dp)
                    .graphicsLayer(translationX = 6f),
//                    .border(1.dp, Color.Black),
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.Center
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Welcome!", fontWeight = FontWeight.Bold, fontSize = 30.sp)
                Text(text = "Sign In to access your account", fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier.border(1.dp, Color.Black)
            ) {
                TextField(
                    value = studentNo,
                    onValueChange = { studentNo = it },
                    placeholder = { Text(text = "Student ID Number") },
                    textStyle = TextStyle(fontSize = 14.sp, letterSpacing = 2.sp),
                    modifier = Modifier
                        .height(50.dp)
                        .border(1.dp, Color.DarkGray, RoundedCornerShape(10.dp)),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text(text = "Password") },
                    textStyle = TextStyle(fontSize = 14.sp, letterSpacing = 2.sp),
                    modifier = Modifier
                        .height(50.dp)
                        .border(1.dp, Color.DarkGray, RoundedCornerShape(10.dp)),
                    singleLine = true,
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    visualTransformation = if (showPassword) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        if (showPassword) {
                            IconButton(onClick = { showPassword = false }) {
                                Icon(
                                    imageVector = Icons.Filled.Visibility,
                                    contentDescription = "hide_password"
                                )
                            }
                        } else {
                            IconButton(
                                onClick = { showPassword = true }) {
                                Icon(
                                    imageVector = Icons.Filled.VisibilityOff,
                                    contentDescription = "hide_password"
                                )
                            }
                        }
                    },
                    isError = error

                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
//                        .border(1.dp, Color.Black),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(text = "Forgot Password?", modifier = Modifier.clickable(
                        onClick = { navController .navigate(MainScreen.ForgotPass.name) }
                    ), fontSize = 13.sp)

                }


            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(top = 20.dp)
            ) {

                Button(
                    onClick = {
                        if(loginAuth(studentNo,password)){

                            navController.navigate(MainScreen.Dashboard.name)
                        }

                        else error = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    modifier = Modifier
                        .size(280.dp, 50.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "SIGN IN", fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White)
                }
                Row {
                    Text(text = "Don't have an account? ",fontSize = 14.sp)
                    Text(
                        text = "Register Here",
                        modifier = Modifier
                            .clickable(
                            onClick = {
                                navController.navigate(MainScreen.Registration.name)
                            }),
                        fontSize = 14.sp,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold

                    )
                }
                if(error){
                    AlertDialog(
                        onDismissRequest = { error = false },
                        confirmButton = {
                            Button(
                                onClick = {
                                navController.navigate(MainScreen.Login.name)
                            },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Red,
                                    contentColor = Color.White
                                )) {
                                Text(text = "OK")
                            }

                        },
                        title = { Text("Invalid Credentials") },
                        text = { Text(text = "Incorrect Student Number or Password")},
                        containerColor = Color.White,
                        textContentColor = Color.DarkGray,
                        titleContentColor = Color.DarkGray
                    )

                }

            }

        }




//    }


}

fun loginAuth(studentNo: String, password: String): Boolean{

    var success = false
    val userList = listOf(
        LoginReq(1, "101", "test1"),
        LoginReq(2, "102", "test2"),
        LoginReq(3, "103", "test3")
    )

    for (i in userList){
        if(studentNo == i.studentNo && password == i.password){
            println("Successfully Logged In")
            success = true
        }

    }
    println("Invalid Credentials")

    return success
}


















//    }

//    Box(
//        modifier = Modifier
//            .zIndex(1f)
//            .graphicsLayer(translationY = 200f, translationX = -350f)
//    ){
//
////        Image(
////            painter = painterResource(id = R.drawable.nbsc_logo),
////            contentDescription = "",
////            colorFilter = ColorFilter.tint(color = Color(0x33000000)),
////            modifier = Modifier
////                .size(1500.dp, 2000.dp)
////                .rotate(56.03f)
////                .zIndex(2f))
//
//    }

//    Box(
//        modifier = Modifier
//            .graphicsLayer(translationY = 1f)
//            .border(3.dp, color = Color.Black)
//            .zIndex(2f),
//        contentAlignment = Alignment.BottomCenter
//
//    ){
