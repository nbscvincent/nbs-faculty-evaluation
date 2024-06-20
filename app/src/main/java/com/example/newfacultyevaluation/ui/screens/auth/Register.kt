package com.example.newfacultyevaluation.ui.screens.auth


import android.icu.text.CaseMap.Upper
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.Key
import androidx.compose.material.icons.rounded.KeyOff
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newfacultyevaluation.R
import com.example.newfacultyevaluation.ui.FacultyAppViewModelProvider
import com.example.newfacultyevaluation.ui.nav.Auth
import com.example.newfacultyevaluation.ui.nav.Main
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(
    navController: NavController,
    viewModel: RegisterViewModel = viewModel(factory = FacultyAppViewModelProvider.Factory)
) {

    BackHandler {
        navController.popBackStack()
        navController.navigate(Auth.REGISTER.name)
    }

    var scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
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
            var fullName by rememberSaveable {
                mutableStateOf("")
            }
            var year by rememberSaveable {
                mutableStateOf("")
            }
            var pass by rememberSaveable {
                mutableStateOf("")
            }

            var seePass by rememberSaveable {
                mutableStateOf(false)
            }
            var expanded by rememberSaveable {
                mutableStateOf(false)
            }
            val programs = listOf("PROGRAM: ", "BSCS", "BSEntrep", "BSA", "BSAIS", "BSTM")
            var selectedProgram by rememberSaveable {
                mutableStateOf(programs[0])
            }

            var expanded1 by rememberSaveable {
                mutableStateOf(false)
            }

            var expanded2 by rememberSaveable {
                mutableStateOf(false)
            }
            //var role = listOf("ROLE: ","Admin", "Student", "Faculty")
            var role = listOf("ROLE:", "Student")
            var selectedRole by rememberSaveable {
                mutableStateOf(role[0])
            }

            val yearOptions = listOf("Select Year:", "1", "2", "3", "4")
            var selectedYear by rememberSaveable {
                mutableStateOf(yearOptions[0])
            }






            Icon(
                painterResource(id = R.drawable.nbsc_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .graphicsLayer(
                        translationX = 10f
                    )
                    .size(200.dp),
                tint = Color.Unspecified
            )


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Register to create your account", fontWeight = FontWeight.W500,
                    letterSpacing = 2.sp, color = Color.Black, modifier = Modifier.padding(10.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

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

                Spacer(modifier = Modifier.height(5.dp))



                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text(text = "Full Name", letterSpacing = 2.sp) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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
                Spacer(modifier = Modifier.height(5.dp))
                    OutlinedTextField(
                        value = pass,
                        onValueChange = { pass = it },
                        label = { Text(text = "Password", letterSpacing = 2.sp) },
                        visualTransformation = if (seePass) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            Icon(imageVector = if (seePass) Icons.Rounded.Visibility
                            else Icons.Rounded.VisibilityOff,
                                contentDescription = "Visibility",
                                tint = Color.DarkGray,
                                modifier = Modifier.clickable(
                                    onClick = { seePass = !seePass }
                                ))
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
                        )

                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    expanded1 = !expanded1
                                },
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Text(text = selectedRole, color = Color.Black)
                            Icon(
                                imageVector = if (expanded1) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                                contentDescription = "",
                                tint = Color.Black
                            )

                        }

                        DropdownMenu(
                            expanded = expanded1,
                            onDismissRequest = { expanded1 = false },
                            modifier = Modifier
                                .background(Color.White),
                            offset = DpOffset(x = 200.dp, y = 10.dp)
                        ) {
                            role.forEach { r ->
                                DropdownMenuItem(
                                    text = { Text(text = r) },
                                    onClick = { selectedRole = r; expanded1 = false },
                                    enabled = r != role[0]
                                )
                            }
                        }

                        if (selectedRole == "Student") {
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        expanded = !expanded
                                    },
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(text = selectedProgram, color = Color.Black)
                                Icon(
                                    imageVector = if (expanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                                    contentDescription = "",
                                    tint = Color.Black
                                )
                            }

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier
                                    .background(Color.White),
                                offset = DpOffset(x = 200.dp, y = 10.dp)
                            ) {
                                programs.forEach { c ->
                                    DropdownMenuItem(
                                        text = { Text(text = c) },
                                        onClick = { selectedProgram = c; expanded = false },
                                        enabled = c != programs[0]
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        expanded2 = !expanded2
                                    },
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Text(text = selectedYear, color = Color.Black)
                                Icon(
                                    imageVector = if (expanded2) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                                    contentDescription = "",
                                    tint = Color.Black
                                )

                            }

                            DropdownMenu(
                                expanded = expanded2,
                                onDismissRequest = { expanded2 = false },
                                modifier = Modifier
                                    .background(Color.White),
                                offset = DpOffset(x = 200.dp, y = 10.dp)
                            ) {
                                yearOptions.forEach { r ->
                                    DropdownMenuItem(
                                        text = { Text(text = r) },
                                        onClick = { selectedYear = r; expanded2 = false },
                                        enabled = r != role[0]
                                    )
                                }
                            }

                        }

                    }
                    val context = LocalContext.current
                    val scope = rememberCoroutineScope()
                    Button(
                        onClick =
                        {
                                if (selectedYear != yearOptions[0] && selectedProgram != programs[0]) {
                                    viewModel.userID = userID
                                    viewModel.fullName = fullName
                                    viewModel.pass = pass
                                    viewModel.selectedProgram = selectedProgram
                                    viewModel.role = selectedRole
                                    viewModel.year = selectedYear
                                    viewModel.date = LocalDateTime.now()
                                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                                    scope.launch {
                                        if (viewModel.insertUser()) {
                                            Toast.makeText(
                                                context,
                                                "Successfully Registered",
                                                Toast.LENGTH_LONG
                                            ).show()
                                            navController.popBackStack()
                                            navController.navigate(Auth.LOGIN.name)
                                        } else Toast.makeText(
                                            context,
                                            "UserID already taken",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                } else {
                                    Toast.makeText(
                                        context,
                                        "Please fill out all fields",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }


                        },
                        modifier = Modifier
                            .absolutePadding(
                                left = 40.dp,
                                right = 40.dp,
                                bottom = 25.dp,
                                top = 10.dp
                            )
                            .height(50.dp)
                            .fillMaxWidth(),

                        colors = ButtonDefaults.buttonColors(Color.Red),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = "Register", fontSize = 16.sp)
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {

                        Text(text = "Already have an account?", color = Color.Black)
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "Login Now!", modifier = Modifier.clickable(
                            onClick = {

                                navController.popBackStack()
                                navController.navigate(Auth.LOGIN.name)
                            }
                        ), color = Color.Red, fontWeight = FontWeight.W600)
                    }
                }

            }

        }
    }

