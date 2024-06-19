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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
    var expanded by remember {
        mutableStateOf(false)
    }



//    var courses by remember {
//        mutableStateOf(mutableStateListOf(Course(0,"", "", "", "")))
//    }

    var expanded1 by remember {
        mutableStateOf(false)
    }

    var expanded2 by remember {
        mutableStateOf(false)
    }

    var selectedYear by remember {
        mutableStateOf("Select Year")
    }

    var selectedProgram by remember {
        mutableStateOf("Select Program")
    }

    var selectedFaculty by remember {
        mutableStateOf("")
    }


    var selectedFacultyID by remember {
        mutableStateOf("")
    }

    var validAll by remember {
        mutableStateOf(false)
    }

    var valid by remember {
        mutableStateOf(false)
    }

    val yearOptions = listOf("1st", "2nd", "3rd", "4th")
    val programOptions = listOf("BSCS", "BSEntrep", "BSA", "BSAIS", "BSTM")

//    fun addNewCourse() {
//        courses.add(Course(0,"","", "", "", ))
//    }

    var expandedYear by remember { mutableStateOf(false) }
    var expandedProgram by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val users = adminViewModel.getAllUsers().collectAsState(null)
        Row (
            modifier = Modifier
                .clickable {
                    expanded = !expanded
                },
            horizontalArrangement = Arrangement.Center
        ){
            val displayText = if (selectedFaculty.isNotBlank()) selectedFaculty else "Select Faculty"
            Text(text = displayText, color = Color.Black)
            Icon(imageVector = if(expanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown, contentDescription = "", tint = Color.Black)
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(Color.White)
            ) {
                users.value?.forEach { user ->
                    if(user.role == "Faculty"){
                        DropdownMenuItem(text = { Text(text = user.fullName.toString(), color = Color.Black) }, onClick = { selectedFacultyID = user.userID;selectedFaculty = user.fullName.toString();expanded = false })
                    }
                }

            }
        }

//        LazyColumn (
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier
//                .height(550.dp)
//                .padding(10.dp)
//                .border(1.dp, Color.Black)
//                .padding(10.dp)
//        ){
//            items(courses.size) { index ->
//                val course = courses[index]
//                var expandedYear by remember { mutableStateOf(false) }
//                var expandedProgram by remember { mutableStateOf(false) }
//
//                Column(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(text = "Course ${index + 1}".uppercase(), color = Color.Black)
//                    OutlinedTextField(
//                        value = courses[index].courseID,
//                        onValueChange = { newValue ->
//                            courses[index] = courses[index].copy(courseID = newValue) },
//                        label = { Text("Enter Course Code", color =Color.Black) },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .border(1.dp, Color.DarkGray, RoundedCornerShape(10.dp)),
//                        colors = TextFieldDefaults.textFieldColors(
//                            containerColor = Color.White,
//                            textColor = Color.Black,
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent
//                        )
//                    )
//
//                    Spacer(modifier = Modifier.height(20.dp))
//
//                    OutlinedTextField(
//                        value = courses[index].courseName,
//                        onValueChange = { newValue ->
//                            courses[index] = courses[index].copy(courseName = newValue) },
//                        label = { Text("Enter Course Name", color = Color.Black) },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .border(1.dp, Color.DarkGray, RoundedCornerShape(10.dp)),
//                        colors = TextFieldDefaults.textFieldColors(
//                            containerColor = Color.White,
//                            textColor = Color.Black,
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent
//                        )
//                    )
//
//                    Spacer(modifier = Modifier.height(20.dp))
//                    // Year Dropdown
//                    Row(
//                        modifier = Modifier.clickable {
//                            expandedYear = !expandedYear
//                        },
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        val displayYear = if (course.year.isNotBlank()) course.year else "Select Year"
//                        Text(text = displayYear, color = Color.Black)
//                        Icon(imageVector = if (expandedYear) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown, contentDescription = "", tint = Color.Black)
//
//                        DropdownMenu(
//                            expanded = expandedYear,
//                            onDismissRequest = { expandedYear = false },
//                            modifier = Modifier.background(Color.White)
//                        ) {
//                            yearOptions.forEach { year ->
//                                DropdownMenuItem(text = { Text(text = year, color = Color.Black) }, onClick = {
//                                    courses[index] = course.copy(year = year)
//                                    expandedYear = false
//                                })
//                            }
//                        }
//
//                        Spacer(modifier = Modifier.width(20.dp))
//                        // Program Dropdown
//                        Row(
//                            modifier = Modifier.clickable {
//                                expandedProgram = !expandedProgram
//                            },
//                            horizontalArrangement = Arrangement.Center
//                        ) {
//                            val displayProgram = if (course.program.isNotBlank()) course.program else "Select Program"
//                            Text(text = displayProgram, color = Color.Black)
//                            Icon(imageVector = if (expandedProgram) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown, contentDescription = "", tint = Color.Black)
//
//                            DropdownMenu(
//                                expanded = expandedProgram,
//                                onDismissRequest = { expandedProgram = false },
//                                modifier = Modifier.background(Color.White)
//                            ) {
//                                programOptions.forEach { program ->
//                                    DropdownMenuItem(text = { Text(text = program, color = Color.Black) }, onClick = {
//                                        courses[index] = course.copy(program = program)
//                                        expandedProgram = false
//                                    })
//                                }
//                            }
//                        }
//                    }
//
//
//                    Spacer(modifier = Modifier.height(20.dp))
//                }
//            }
//        }
//        Row (
//            modifier = Modifier
//                .padding(horizontal = 10.dp)
//                .height(50.dp)
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceAround,
//            verticalAlignment = Alignment.CenterVertically
//        ){
//            val context = LocalContext.current
//
//            Button(onClick = {
//                courses.forEach {
//                    validAll = it.courseID.isNotBlank() && it.courseName.isNotBlank()
//                }
//
//                if(selectedFaculty.isNotBlank() && validAll){
//                    courses.forEach {
////                        adminViewModel.insertCourseFaculty(CourseFaculty(it.courseID, selectedFacultyID))
////                        adminViewModel.upsertCourse(Course(it.id,it.courseID, it.courseName, it.year, it.program))
//                        println(selectedFaculty)
//                        println(it.courseID)
//                        println(it.courseName)
//                        println(it.year)
//                        println(it.program)
//                    }
//
//
//                    navController.popBackStack()
//                    navController.navigate(AdminNav.AddCourse.name)
//                    Toast.makeText(context,"Successfully Added !", Toast.LENGTH_SHORT).show()
//                }else{
//                    Toast.makeText(context,"Please fill out all fields", Toast.LENGTH_SHORT).show()
//                }
//
//            },
//                shape = RoundedCornerShape(10.dp),
//                modifier = Modifier.weight(1f)
//            ) {
//                Text(text = "Save")
//            }
//            Icon(
//                imageVector = Icons.Outlined.Add,
//                contentDescription = "Add",
//                modifier = Modifier
//                    .clickable {
//                        // Handle adding the new course to the list
//                        addNewCourse()
//                    }
//                    .size(40.dp)
//                    .weight(1f),
//                tint = Color.Black
//            )
//        }

    }
}



