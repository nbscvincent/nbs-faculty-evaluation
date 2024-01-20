package com.example.newfacultyevaluation.ui.screens.dash.portal.admin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.ui.nav.AdminNav

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCourses(
    navController: NavController,
    adminViewModel: AdminViewModel
) {
    var courses by remember {
        mutableStateOf(mutableStateListOf(Course("", "")))
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    var selectedFaculty by remember {
        mutableStateOf("Select Faculty")
    }

    var selectedFacultyID by remember {
        mutableStateOf("")
    }

    var validAll by remember {
        mutableStateOf(false)
    }
    
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val users = adminViewModel.getAllUsers().observeAsState()

        Row (
            modifier = Modifier
                .clickable {
                    expanded = !expanded
                },
            horizontalArrangement = Arrangement.Center
        ){

            Text(text = selectedFaculty)
            Icon(imageVector = if(expanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown, contentDescription = "")

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(Color.White)
            ) {
                users.value?.forEach { user ->
                    if(user.role == "Faculty"){
                        DropdownMenuItem(text = { Text(text = user.fullName.toString()) }, onClick = { selectedFacultyID = user.userID;selectedFaculty = user.fullName.toString();expanded = false })
                    }
                }

            }
        }
        LazyColumn (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .height(550.dp)
                .padding(10.dp)
                .border(1.dp, Color.Black)
        ){
            item {
                // Additional row for entering a new course
                Text(text = "Course 1")
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = courses[0].courseID,
                        onValueChange = { newValue ->
                            courses[0] = courses[0].copy(courseID = newValue) },
                        label = { Text("Enter Course Code") }
                    )

                    OutlinedTextField(
                        value = courses[0].courseName,
                        onValueChange = { newValue ->
                            courses[0] = courses[0].copy(courseName = newValue) },
                        label = { Text("Enter Course Name") }
                    )


                }
            }
            items(courses.size - 1) { index ->
                Text(text = "Course ${index+2}")
                OutlinedTextField(
                    value = courses[index + 1].courseID,
                    onValueChange = { newValue ->
                        courses[index + 1] = courses[index + 1].copy(courseID = newValue)
                    },
                    label = { Text("Enter Course Code") }
                )
                OutlinedTextField(
                    value = courses[index + 1].courseName,
                    onValueChange = { newValue ->
                        courses[index + 1] = courses[index + 1].copy(courseName = newValue)
                    },
                    label = { Text("Enter Course Name") }
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = 50.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ){
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete",
                        modifier = Modifier
                            .clickable {
                                // Handle adding the new course to the list
                                courses.removeAt(index + 1)
                            }
                            .size(30.dp)
                    )
                }

            }
            

        }
        Row (
            modifier = Modifier
                .border(2.dp, Color.Black)
                .height(50.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            val context = LocalContext.current

            Button(onClick = {
                courses.forEach {
                    validAll = it.courseID.isNotBlank() && it.courseName.isNotBlank()
                }
                if(validAll){
                    courses.forEach {
                        adminViewModel.insertCourseFaculty(CourseFaculty(it.courseID, selectedFacultyID))
                    }
                    navController.popBackStack()
                    navController.navigate(AdminNav.AddCourse.name)
                    Toast.makeText(context,"Successfully Added !", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"Please fill out all fields", Toast.LENGTH_SHORT).show()
                }

            }) {

                Text(text = "Save")
            }
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "Add",
                modifier = Modifier
                    .clickable {
                        // Handle adding the new course to the list
                        courses.add(Course("", ""))
                    }
                    .size(40.dp)
            )
        }

    }
}



