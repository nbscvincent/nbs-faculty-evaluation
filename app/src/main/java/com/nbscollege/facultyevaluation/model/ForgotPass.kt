package com.nbscollege.facultyevaluation.model

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newfacultyevaluation.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPass(){
    var email by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TextButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(10.dp),
                    tint = Color.White) }
        },
        modifier = Modifier.padding(12.dp),
        containerColor = Color.Transparent) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp),
            contentAlignment = Alignment.Center

        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier
                    .size(250.dp, 400.dp)
                    .align(Alignment.Center),
                contentScale = ContentScale.FillBounds)
            TextField(
                value = email,
                onValueChange = {email = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
                    .graphicsLayer(translationY = 450f),
                textStyle = TextStyle(fontSize = 30.sp, textAlign = TextAlign.Center, color = Color.Black),
                shape = RoundedCornerShape(20.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        text = "EMAIL",
                        fontSize = 30.sp,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 100.dp)
                    .graphicsLayer(translationY = 600f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),

            ) {
                Text(text = "SEND OTP", fontSize = 30.sp)
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 100.dp)
                    .graphicsLayer(translationY = 800f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),

                ) {
                Text(text = "Enter OTP", fontSize = 30.sp)
            }
        }

    }
}