package com.example.newfacultyevaluation.ui.screens.auth


import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newfacultyevaluation.R
import com.example.newfacultyevaluation.ui.nav.Auth
import com.example.newfacultyevaluation.ui.nav.Main
import com.example.newfacultyevaluation.ui.nav.Portal


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    navController: NavController,
    viewModel: LoginViewModel
) {

    BackHandler{
        navController.popBackStack()
        navController.navigate(Main.AUTH.name)
    }

    val scrollState = rememberScrollState()
    var showPassword by remember { mutableStateOf(value = false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
    ) {

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            var userID by rememberSaveable {
                mutableStateOf("")
            }
            var pass by rememberSaveable {
                mutableStateOf("")
            }
            var checked by rememberSaveable {
                mutableStateOf(false)
            }




            Icon(
                painterResource(id = R.drawable.nbsc_logo),
                contentDescription = "Logo",
                modifier = Modifier.graphicsLayer(
                    translationX = 15f,
                ), tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Welcome",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.W700,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Sign In to access your account",
                    fontWeight = FontWeight.W300,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                OutlinedTextField(
                    value = userID,
                    onValueChange = { userID = it },
                    label = { Text(text = "User ID", letterSpacing = 2.sp) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .absolutePadding(left = 40.dp, right = 40.dp, bottom = 11.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(10.dp)),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = pass,
                    onValueChange = { pass = it },
                    label = { Text(text = "Password", letterSpacing = 2.sp) },
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

                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .absolutePadding(left = 40.dp, right = 40.dp, bottom = 11.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(10.dp)),

                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),

                    )




//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp),
//                    horizontalArrangement = Arrangement.End,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(text = "Forgot Password?", modifier = Modifier.clickable(
//                        onClick = {
//                            navController.popBackStack()
//                            navController.navigate(Auth.FORGOTPASS.name)
//                        }
//                    ))
//                }


                val context = LocalContext.current
                val user by viewModel.getUser(userID).collectAsState(null)
                val onlineUser by viewModel.getUser(userID).collectAsState(null)

                Button(
                    onClick =
                    {

                        if (user?.password == pass && onlineUser!!.password ==  pass) {

                            navController.popBackStack()
                            navController.navigate(Main.PORTAL.name)
                            viewModel.status = true
                            viewModel.userID = userID
                            viewModel.password = pass
                            viewModel.role = user?.role.toString()
                            println(viewModel.role)
                            val preferences = context.getSharedPreferences("prefs", 0)
                            preferences.edit()
                                .putBoolean("status", true)
                                .putString("userID", viewModel.userID)
                                .putString("password", viewModel.password)
                                .putString("role", viewModel.role)
                                .apply()
                            println("Online user: $onlineUser")
                            println("Online user: $user")
                            Toast.makeText(context, "Logged In Successfully", Toast.LENGTH_SHORT)
                                .show()
                            return@Button
                        } else {
                            Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT)
                                .show()
                        }
                    },
                    modifier = Modifier
                        .absolutePadding(
                            left = 40.dp,
                            right = 40.dp,
                            bottom = 25.dp,
                            top = 25.dp
                        )
                        .height(50.dp)
                        .fillMaxWidth(),

                    colors = ButtonDefaults.buttonColors(Color.Red),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Login", fontSize = 16.sp)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Don't have an account?")
                    Text(text = "Register Now!", modifier = Modifier.clickable {
                        navController.popBackStack()
                        navController.navigate(Auth.REGISTER.name)
                    }, color = Red, fontWeight = FontWeight.W600)
                }


            }

        }
    }

}


