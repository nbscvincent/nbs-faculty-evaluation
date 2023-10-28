package com.nbscollege.facultyevaluation.model

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.waterfallPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.AlertDialogDefaults.shape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nbscollege.facultyevaluation.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPass(
    navController: NavHostController = rememberNavController()
){
    var email by remember { mutableStateOf("")}
    var otpCode by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TextButton(onClick = {
                navController.navigate("Login")
            }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp),
                    tint = Color.White) }
        },
        modifier = Modifier.padding(12.dp),
        containerColor = Color.Transparent) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            contentAlignment = Alignment.Center

        ){
            Image(
                painter = painterResource(id = R.drawable.nbsc_logo_main),
                contentDescription = "",
                modifier = Modifier
                    .size(200.dp, 250.dp)
                    .align(Alignment.Center),
                contentScale = ContentScale.FillBounds)
            TextField(
                value = email,
                onValueChange = {email = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .graphicsLayer(translationY = 370f),
                singleLine = true,
                textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center,
                    color = Color.Black),
                shape = RoundedCornerShape(40.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        text = "Enter your School Email",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Left
                    )
                }
            )
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(start = 70.dp, end = 70.dp)
                    .graphicsLayer(translationY = 500f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray
                ),

                ) {
                Text(text = "SEND OTP", fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold)
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(start = 70.dp, end = 70.dp)
                    .graphicsLayer(translationY = 830f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray
                ),

                ) {
                Text(text = "VERIFY ACCOUNT", fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold)
            }
            TextField(
                value = otpCode,
                onValueChange = {otpCode = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .graphicsLayer(translationY = 700f),
                singleLine = true,
                textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center,
                    color = Color.Black),
                shape = RoundedCornerShape(40.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        text = "Enter the OTP Code",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Left
                    )
                }
            )
        }

    }
}