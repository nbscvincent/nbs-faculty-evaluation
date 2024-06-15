package com.example.newfacultyevaluation.ui.screens.dash.portal.admin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newfacultyevaluation.ui.FacultyAppViewModelProvider
import com.example.newfacultyevaluation.ui.nav.AdminNav
import com.typesafe.config.ConfigException.Null
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUser(

    navController: NavController,
    viewModel: AdminViewModel = viewModel(factory = FacultyAppViewModelProvider.AdminFactory)

) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp).background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        var userID by rememberSaveable {
            mutableStateOf("")
        }
        var fullName by rememberSaveable {
            mutableStateOf("")
        }
        var pass by rememberSaveable {
            mutableStateOf("")
        }

        val year = listOf("Year: ", "1st", "2nd", "3rd", "4th")
        var selectedYear by rememberSaveable {
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
        val role = listOf("ROLE: ", "Admin", "Student", "Faculty")
        var selectedRole by rememberSaveable {
            mutableStateOf(role[0])
        }

        Column(
            modifier = Modifier
                .padding(5.dp),
//                .border(1.dp, Color.Magenta),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Text(
                text = "ADD NEW USER", fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .background(
                        Color.Red
                    )
                    .padding(5.dp), textAlign = TextAlign.Center, color = Color.White
            )

            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(10.dp)
                ) {

                    Spacer(
                        modifier = Modifier
                            .size(20.dp)
                    )

                    OutlinedTextField(
                        value = userID,
                        onValueChange = { userID = it },
                        label = { Text(text = "User ID", letterSpacing = 2.sp, color = Color.Black) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.DarkGray, RoundedCornerShape(10.dp)),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            textColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                    Spacer(
                        modifier = Modifier
                            .size(20.dp)
                    )

                    OutlinedTextField(
                            value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text(text = "Full Name", letterSpacing = 2.sp, color = Color.Black) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.DarkGray, RoundedCornerShape(10.dp)),

                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                    )

                    Spacer(
                        modifier = Modifier
                            .size(20.dp)
                    )





                    OutlinedTextField(
                        value = pass,
                        onValueChange = { pass = it },
                        label = { Text(text = "Password", letterSpacing = 2.sp, color = Color.Black) },
                        visualTransformation = if (seePass) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            Icon(imageVector = if (seePass) Icons.Rounded.Visibility
                            else Icons.Rounded.VisibilityOff,
                                contentDescription = "Visibility", modifier = Modifier.clickable(
                                    onClick = { seePass = !seePass }
                                ))
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.DarkGray, RoundedCornerShape(10.dp)),

                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            textColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )

                    Spacer(
                        modifier = Modifier
                            .size(20.dp)
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

                            Text(text = selectedRole , color = Color.Black)
                            Icon(
                                imageVector = if (expanded1) Icons.Rounded.KeyboardArrowUp
                                else Icons.Rounded.KeyboardArrowDown, contentDescription = "",
                                tint = Color.Black
                            )
                        }

                        DropdownMenu(
                            expanded = expanded1,
                            onDismissRequest = { expanded1 = false },
                            modifier = Modifier
                                .background(Color.White),
                            offset = DpOffset(x = 10.dp, y = 10.dp)
                        ) {
                            role.forEach { r ->
                                DropdownMenuItem(
                                    text = { Text(text = r , color = Color.Black) },
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
                                        text = { Text(text = c, color = Color.Black) },
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
                                Text(text = if (selectedYear.isEmpty()) "Year" else selectedYear , color = Color.Black)
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
                                year.forEach { c ->
                                    DropdownMenuItem(
                                        text = { Text(text = c , color = Color.Black) },
                                        onClick = { selectedYear = c; expanded2 = false },
                                        enabled = c != year[0]
                                    )
                                }
                            }

                        }
                    }


                    val context = LocalContext.current
                    val user = viewModel.checkUserID(userID,pass).collectAsState(null)

                    Spacer(
                        modifier = Modifier
                            .size(50.dp)
                    )

                    Button(
                        onClick =
                        {
                            if (user.value == null) {
                                viewModel.userID = userID
                                viewModel.fullName = fullName
                                viewModel.year = selectedYear
                                viewModel.pass = pass
                                viewModel.selectedProgram = selectedProgram
                                viewModel.role = selectedRole
                                viewModel.date = LocalDateTime.now()
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                                if (viewModel.insertUser()) {
                                    println("ajslkdakldakjl${viewModel.userID}")
                                    Toast.makeText(context, "Successfully Added", Toast.LENGTH_LONG)
                                        .show()
                                        navController.popBackStack()
                                        navController.navigate(AdminNav.UserList.name)
                                } else Toast.makeText(
                                    context,
                                    "Please fill out all fields",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "UserID is already taken",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .width(200.dp)
                            .height(50.dp)
                    ) {
                        Text(text = "Add User", fontSize = 16.sp)
                    }


                }

            }

        }
    }
}

