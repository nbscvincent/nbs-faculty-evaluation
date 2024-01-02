package com.nbscollege.facultyevaluation.registration.ui

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.widget.Toast
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nbscollege.facultyevaluation.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nbscollege.facultyevaluation.AppViewModelProvider
import com.nbscollege.facultyevaluation.user.model.RegistrationReq
import com.nbscollege.facultyevaluation.navigation.routes.MainScreen
import com.nbscollege.facultyevaluation.registration.viewmodel.RegistrationViewModel
import com.nbscollege.facultyevaluation.registration.viewmodel.UserDetails
import com.nbscollege.facultyevaluation.ui.theme.fontFamily
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registration(
    navController: NavHostController = rememberNavController(),
    viewModel: RegistrationViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    var studentNo by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var context = LocalContext.current
    var coroutineScope = rememberCoroutineScope();
    var uiState = viewModel.userUiState
    var expanded by remember { mutableStateOf(false) }
    var roles = listOf(
        "SELECT YOUR ROLE: ",
        "Student",
        "Faculty",
        "Admin"
    )
    var selectedIndex by remember { mutableStateOf(0) }
    var disabledItem = "SELECT YOUR ROLE: "

    Scaffold(
        topBar = {
            TextButton(onClick = {
                navController.navigate(MainScreen.Login.name)
            }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size(45.dp)
                        .padding(10.dp),
                    tint = Color.DarkGray)
            }
        },
        modifier = Modifier.padding(10.dp),
        containerColor = Color.Transparent) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.nbsc_logo),
                contentDescription = "",
                modifier = Modifier
                    .size(110.dp, 160.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(0.8f)

        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(translationY = 170f)) {
                OutlinedTextField(
                    value = studentNo,
                    onValueChange = {studentNo = it},
                    placeholder = {Text(text = "User ID", fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Gray)},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle(fontSize = 20.sp, letterSpacing = 2.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        textColor = Color.Black
                    ))

                OutlinedTextField(
                    value = firstName,
                    onValueChange = {firstName = it},
                    placeholder = {Text(text = "First Name", fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black)},
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
                    placeholder = {Text(text = "Last Name", fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black)},
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
                    placeholder = {Text(text = "School Email", fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black)},
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
                Box (
                    modifier = Modifier
                        .height(50.dp)
                        .border(1.dp, Color.Black, RoundedCornerShape(5.dp))
                        .wrapContentSize(Alignment.CenterStart)
                ){
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                onClick = { expanded = true }
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){

                        Text(
                            roles[selectedIndex],
                            modifier = Modifier
                                .fillMaxHeight()
                                .background(
                                    Color.Transparent
                                )
                                .wrapContentSize(Alignment.CenterStart)
                                .padding(start = 20.dp)
                        )
                        if(!expanded){
                            Icon(Icons.Rounded.KeyboardArrowDown, contentDescription = "DropDown", modifier = Modifier.size(40.dp))
                        }else{
                            tween<Icon>(1000)
                            Icon(Icons.Rounded.KeyboardArrowUp, contentDescription = "DropUp", modifier = Modifier.size(40.dp))
                        }

                    }


                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false },  modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                    ) {
                        roles.forEachIndexed { index, s ->
                            DropdownMenuItem(
                                text = {
                                    val disabledColor = if (s == disabledItem) {
                                        Color.Gray
                                    } else {
                                        Color.Black
                                    }
                                    Text(text = s, color =  disabledColor, fontFamily = fontFamily, fontSize = 20.sp)
                                },
                                onClick = {
                                    selectedIndex = index
                                    expanded = false

                                },
                                colors = MenuDefaults.itemColors(
                                    textColor = Color.Black
                                )

                            )
                        }
                    }

                }
                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it},
                    placeholder = {Text(text = "Password", fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black)},
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
//                Spacer(modifier = Modifier.height(50.dp))
                Button(
                    onClick = {
                        if(firstName.isNotEmpty() && lastName.isNotEmpty()
                            && email.isNotEmpty() && studentNo.isNotEmpty()
                            && password.isNotEmpty()){
                            coroutineScope.launch {

                                uiState.userDetails = UserDetails(studentNo = studentNo, password = password)
                                viewModel.saveUser()
                            }
                            RegistrationReq(studentNo, firstName,lastName,email, password)
                            navController.navigate(MainScreen.Login.name)


                        }
                        else {
                            Toast.makeText(context, "Please fill Up all fields",Toast.LENGTH_LONG)
                        }

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    modifier = Modifier.size(280.dp, 50.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "REGISTER", fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White)
                }

            }


        }

    }

}