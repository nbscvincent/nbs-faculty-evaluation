package com.example.newfacultyevaluation.ui.screens.dash.portal.admin


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newfacultyevaluation.data.model.User
import com.example.newfacultyevaluation.ui.FacultyAppViewModelProvider




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserList(
    viewModel: AdminViewModel = viewModel(factory = FacultyAppViewModelProvider.AdminFactory),
) {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .border(1.dp, Color.Magenta),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "USER LIST", fontWeight = FontWeight.Bold, fontSize = 25.sp, modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(
                Color.Red
            )
            .padding(5.dp), textAlign = TextAlign.Center, color = Color.White)
            val users = viewModel.getAllUsers().observeAsState()
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .width(500.dp)
                    .verticalScroll(rememberScrollState())
                    .height(550.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {


                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f)
                ){

                    Text(text = "User ID", textAlign = TextAlign.Center, modifier = Modifier
                        .padding(5.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        users.value?.forEach {
                            Text(text = it.userID, modifier = Modifier
                                .height(50.dp))
                        }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f)
                ) {

                    Text(text = "Name", textAlign = TextAlign.Center, modifier = Modifier
                        .padding(5.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    users.value?.forEach {
                        Text(text = "${it.fullName}", modifier = Modifier
                            .height(50.dp))
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f)
                ) {

                    Text(text = "Role", textAlign = TextAlign.Center, modifier = Modifier
                        .padding(5.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    users.value?.forEach {
                        Text(text = it.role, modifier = Modifier
                            .height(50.dp))
                    }
                }


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                ) {

                    Text(text = "", textAlign = TextAlign.Center, modifier = Modifier
                        .padding(5.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    var showEdit by remember {
                        mutableStateOf(false)
                    }
                    users.value?.forEach { user ->
                        Row(
                            modifier = Modifier
                                .width(100.dp)
                                .height(50.dp)
                        ){
                            Icon(Icons.Filled.Edit, contentDescription = "Edit",modifier = Modifier
                                .clickable {
                                    showEdit = true
                                }
                                .weight(1f)
                            )
                            Icon(Icons.Filled.Delete, contentDescription = "Delete",modifier = Modifier
                                .weight(1f))
                        }

                        if(showEdit){
                            Dialog(onDismissRequest = { }) {
                                Column(
                                    modifier = Modifier.background(Color.White).height(500.dp).padding(20.dp),
                                    verticalArrangement = Arrangement.Center
                                ) {

                                    OutlinedTextField(value = user.userID, onValueChange = { }, enabled = false)
                                    OutlinedTextField(value = user.fullName.toString(), onValueChange = { }, enabled = false)
                                    Row(){
                                        var clicked by remember {
                                            mutableStateOf(false)
                                        }
                                        OutlinedTextField(
                                            value = user.password,
                                            onValueChange = { },
                                            visualTransformation = if(!clicked) PasswordVisualTransformation() else VisualTransformation.None,
                                            trailingIcon = { Icon(imageVector = if(clicked) Icons.Filled.Visibility else Icons.Filled.VisibilityOff, contentDescription = "Password", modifier = Modifier.clickable { clicked =!clicked })}
                                        )

                                    }
                                    Row {
                                        Button(onClick = {
                                            showEdit = false
        //                                    viewModel.updateUser(user = User())
                                        }) {
                                            Text(text = "Save")
                                        }
                                        Button(onClick = {
                                            showEdit = false
                                        }, colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.LightGray,
                                            contentColor = Color.Black
                                        )) {
                                            Text(text = "Cancel")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }




            }

        }

    }


