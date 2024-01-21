package com.example.newfacultyevaluation.ui.screens.dash.portal.admin


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newfacultyevaluation.ui.FacultyAppViewModelProvider




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
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {


                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(10.dp)
                ){

                    Text(text = "User ID", textAlign = TextAlign.Center, modifier = Modifier
                        .padding(5.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        users.value?.forEach {
                            Text(text = it.userID)
                        }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(10.dp)
                ) {

                    Text(text = "Name", textAlign = TextAlign.Center, modifier = Modifier
                        .padding(5.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    users.value?.forEach {
                        Text(text = "${it.fullName}")
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(10.dp)
                ) {

                    Text(text = "Role", textAlign = TextAlign.Center, modifier = Modifier
                        .padding(5.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    users.value?.forEach {
                        Text(text = it.role)
                    }
                }


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(10.dp)
                ) {

                    Text(text = "", textAlign = TextAlign.Center, modifier = Modifier
                        .padding(5.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold)

                    users.value?.forEach {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit")

                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(10.dp)
                ) {

                    Text(text = "", textAlign = TextAlign.Center, modifier = Modifier
                        .padding(5.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold)

                    users.value?.forEach {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete")

                    }
                }


            }

        }

    }


