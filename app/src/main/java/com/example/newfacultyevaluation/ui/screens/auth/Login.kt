package com.example.newfacultyevaluation.ui.screens.auth


import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import com.example.newfacultyevaluation.R
import com.example.newfacultyevaluation.data.User
import com.example.newfacultyevaluation.ui.FacultyAppViewModelProvider
import com.example.newfacultyevaluation.ui.nav.Auth
import com.example.newfacultyevaluation.ui.nav.Main
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    navController: NavController,
    viewModel: LoginViewModel,
    roles: HashMap<String, String> = hashMapOf("20" to "Student", "30" to "Faculty", "40" to "Admin")
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
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
        var expanded by rememberSaveable {
            mutableStateOf(false)
        }

        Icon(
            painterResource(id = R.drawable.nbsc_logo),
            contentDescription = "Logo",
            modifier = Modifier.graphicsLayer(
                translationX = 15f
            ), tint = Color.Unspecified
        )
        Column(
            modifier = Modifier.height(50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome",
                fontSize = 20.sp,
                fontWeight = FontWeight.W700,
                letterSpacing = 4.sp
            )
            Text(
                text = "Sign In to access your account",
                fontWeight = FontWeight.W300,
                letterSpacing = 2.sp
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

                OutlinedTextField(
                    value = userID,
                    onValueChange = { userID = it },
                    label = { Text(text = "User ID", letterSpacing = 4.sp) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = pass,
                    onValueChange = { pass = it },
                    label = { Text(text = "Password", letterSpacing = 4.sp) },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(
                            5.dp,
                            Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = checked,
                            onCheckedChange = { checked = !checked },
                            modifier = Modifier
                                .size(20.dp)
                        )
                        Text(text = "Remember Me")
                    }
                    Text(text = "Forgot Password?", modifier = Modifier.clickable(
                        onClick = {}
                    ))
                }
                val context = LocalContext.current
                val user by viewModel.getUser(userID).observeAsState()
                Button(
                    onClick =
                    {

                        if (user?.password == pass) {

                            roles.entries.forEach { role ->

                                if(userID.startsWith(role.key)){

                                    navController.popBackStack()
                                    navController.navigate(Main.PORTAL.name)
                                    viewModel.status = true
                                    viewModel.userID = userID
                                    viewModel.password = pass
                                    viewModel.role = roles[role.key].toString()
                                    println(viewModel.role)
                                    val preferences = context.getSharedPreferences("prefs", 0)
                                    preferences.edit()
                                        .putBoolean("status", true)
                                        .putString("userID", viewModel.userID)
                                        .putString("password", viewModel.password)
                                        .putString("role", viewModel.role)
                                        .apply()

                                    Toast.makeText(context, "Logged In Successfully", Toast.LENGTH_SHORT)
                                        .show()
                                    return@Button
                                }


                            }
                            Toast.makeText(context, "ID not found in the list", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT)
                                .show()
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
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


