package com.nbscollege.facultyevaluation.model

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nbscollege.facultyevaluation.R
import java.sql.RowId

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListAdd(
    navController: NavHostController = rememberNavController()
){

    var empId by remember { mutableStateOf("")}
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var jobTitle by remember{ mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }



    Scaffold(
        topBar = {
            TextButton(onClick = {
                navController.navigate("UserList")
            }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp),
                    tint = Color.White)
            }
        },
        modifier = Modifier.padding(10.dp),
        containerColor = Color.Transparent) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 5.dp)
        )
        {
            Image(
                painter = painterResource(id = R.drawable.nbsc_logo_main),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp, 130.dp)
                    .align(Alignment.TopEnd)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth()
                .padding(top = 80.dp),
            contentAlignment = Alignment.BottomCenter
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(translationY = 130f)) {
                OutlinedTextField(
                    value = empId,
                    onValueChange = {empId = it},
                    placeholder = { Text(text = "Employee ID Number", fontSize = 20.sp, textAlign = TextAlign.Center) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle(fontSize = 20.sp, letterSpacing = 2.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(40.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        textColor = Color.Black
                    ))

                OutlinedTextField(
                    value = firstName,
                    onValueChange = {firstName = it},
                    placeholder = { Text(text = "First Name", fontSize = 20.sp, textAlign = TextAlign.Center) },
                    textStyle = TextStyle(fontSize = 20.sp, letterSpacing = 2.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(40.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        textColor = Color.Black
                    ))

                OutlinedTextField(
                    value = lastName,
                    onValueChange = {lastName = it},
                    placeholder = { Text(text = "Last Name", fontSize = 20.sp, textAlign = TextAlign.Center) },
                    textStyle = TextStyle(fontSize = 20.sp, letterSpacing = 2.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(40.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        textColor = Color.Black
                    ))

                OutlinedTextField(
                    value = email,
                    onValueChange = {email = it},
                    placeholder = { Text(text = "Company Email", fontSize = 20.sp, textAlign = TextAlign.Center) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    textStyle = TextStyle(fontSize = 20.sp, letterSpacing = 2.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(40.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        textColor = Color.Black
                    ))

                OutlinedTextField(
                    value = jobTitle,
                    onValueChange = {jobTitle = it},
                    placeholder = { Text(text = "Job Title", fontSize = 20.sp, textAlign = TextAlign.Center) },
                    textStyle = TextStyle(fontSize = 20.sp, letterSpacing = 2.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(40.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        textColor = Color.Black
                    ))

                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it},
                    placeholder = { Text(text = "Password", fontSize = 20.sp, textAlign = TextAlign.Center) },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    textStyle = TextStyle(fontSize = 20.sp, letterSpacing = 2.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(40.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        textColor = Color.Black
                    ))
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(280.dp, 50.dp)
                        .padding(start = 60.dp, end = 60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray
                    )) {
                    Text(text = "ADD", fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black)
                }

            }

        }
    }

}