package com.example.newfacultyevaluation.ui.screens.dash.portal.admin

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.newfacultyevaluation.data.model.Course

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCourses() {

    var courseName by remember {
        mutableStateOf("")
    }

    var courses by remember {
        mutableStateOf(mutableListOf(Course("","")))
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
       LazyColumn {
           items(courses.size-1){index ->
               Column {
                   OutlinedTextField(
                       value = courses[index].courseID,
                       onValueChange = { courses[index] = courses[index].copy(courseID = it) },
                       label = { Text("Enter Course Code")}
                   )
                   OutlinedTextField(
                       value = courses[index].courseName,
                       onValueChange = { courses[index] = courses[index].copy(courseName = it) },
                       label = {Text("Enter Course Name")}
                   )
               }
           }
           item {
               Text("${courses[0]}")
               Column {
                   OutlinedTextField(
                       value = courses[0].courseID,
                       onValueChange = { courses[0] = courses[0].copy(courseID = it) },
                       label = { Text("Enter Course Code")}
                   )
                   OutlinedTextField(
                       value = courses[0].courseName,
                       onValueChange = { courses[0] = courses[0].copy(courseName = it) },
                       label = {Text("Enter Course Name")}
                   )
                   Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add", modifier = Modifier.clickable {
                       courses = courses.toMutableList().apply { add(Course("","")) }
                   })
               }
           }
       }







    }
}