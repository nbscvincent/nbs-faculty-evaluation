package com.nbscollege.facultyevaluation.model

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nbscollege.facultyevaluation.R
import com.nbscollege.facultyevaluation.navigation.routes.MainScreen
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ForgotPass(
    navController: NavHostController = rememberNavController()
){
    var email by remember { mutableStateOf("")}
    var otpCode by remember { mutableStateOf("") }
    var timer by remember { mutableIntStateOf(60 * 5) }
    var timerSec by remember {
        mutableIntStateOf(60)
    }
    var otpClicked by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black)) {

                TextButton(onClick = {
                    navController.navigate(MainScreen.Login.name)
                }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(45.dp)
                            .padding(10.dp),
//                            .border(1.dp, Color.Black),
                        tint = Color.DarkGray
                    )
                }

            }

        },
        containerColor = Color.White,
        content = {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .padding(vertical = 60.dp, horizontal = 20.dp)
                    .border(1.dp, Color.Black)
            ){
                Image(
                    painter = painterResource(id = R.drawable.nbsc_logo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(200.dp, 250.dp),
                    contentScale = ContentScale.FillBounds
                )

                Row (
                    modifier = Modifier
                        .border(3.dp, Color.Black)
                        .fillMaxWidth(),
//                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    TextField(
                        value = email,
                        onValueChange = {email = it},
                        modifier = Modifier
                            .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                            .size(200.dp, 50.dp),
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            color = Color.Black),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        placeholder = {
                            Text(
                                text = "Enter School Email",
                                fontSize = 15.sp
                            )
                        }
                    )
                    Button(
                        onClick = {
                                  otpClicked = true
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        ),

                        ) {
                        Text(text = "Send OTP", fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold)
                    }
                }

                Row(
                    modifier = Modifier
                        .border(3.dp, Color.Black)
                        .fillMaxWidth(),
//                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly

                ) {
                    TextField(
                        value = otpCode,
                        onValueChange = {otpCode = it},
                        modifier = Modifier
                            .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                            .size(200.dp, 50.dp),
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            color = Color.Black),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        placeholder = {
                            Text(
                                text = "Enter OTP",
                                fontSize = 15.sp
                            )
                        }
                    )
                    Button(
                        onClick = {
                                  navController.navigate(MainScreen.Login.name)
                        },

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        ),

                        ) {
                        Text(text = "VERIFY", fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold)
                    }
                }
                if(otpClicked){
                    if(timerSec == 0){
                        timerSec = 60
                    }
                    LaunchedEffect(key1 = true){
                        while (true){
                            delay(1000)
                            timer -= 1;
                            timerSec -= 1
                        }
                    }
                    Text(
                        text = "Resend Code in ${
                            timer / 60
                        } minutes and $timerSec seconds",
                        fontSize = 15.sp)
                }
            }
        })
}