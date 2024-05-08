package com.example.newfacultyevaluation.ui.screens.dash.portal.student

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.LibraryAdd
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseStudent
import com.example.newfacultyevaluation.ui.FacultyAppViewModelProvider
import com.example.newfacultyevaluation.ui.nav.StudentNav
import com.example.newfacultyevaluation.ui.screens.auth.LoginViewModel
import com.example.newfacultyevaluation.ui.theme.White

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SFaculty(
    loginViewModel: LoginViewModel,
    studentNavController: NavController,
    mainNav: NavController,
    viewModel: StudentViewModel,


) {

    var openDialog by remember {
        mutableStateOf(false)
    }

    var openDataPrivacy by remember {
        mutableStateOf(false)
    }

    val courses = viewModel.getCoursesByStudentID(loginViewModel.userID).collectAsState(null)

    var selectedCourse by remember {
        mutableStateOf("")
    }

    var year by remember {
        mutableStateOf("")
    }
    BackHandler {
        studentNavController.popBackStack()
        studentNavController.navigate(StudentNav.HOME.name)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var showCheckBox by remember {
            mutableStateOf(false)
        }

        Row (
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){

            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete Course",
                modifier = Modifier.clickable {
                    showCheckBox = !showCheckBox
                }
            )
            Icon(
                imageVector = Icons.Rounded.LibraryAdd,
                contentDescription = "Add Course",
                modifier = Modifier.clickable {
                    openDialog = true
                    showCheckBox = false
                }
            )
        }

        if (courses.value?.size == 0) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "No Courses Found", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "Tap \"+\" icon to enroll your courses.")
            }
        } else {

            courses.value?.forEach { course ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    if(showCheckBox){
                        Button(onClick = {
                            viewModel.deleteCourse(CourseStudent(course.courseID, loginViewModel.userID))
                        }, shape = RoundedCornerShape(10.dp), modifier = Modifier.size(40.dp), contentPadding = PaddingValues(10.dp)) {
                            Icon(imageVector = Icons.Rounded.DeleteOutline, contentDescription = "Delete")
                        }
                    }
                    Card(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .height(50.dp)
                    ) {

                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable(
                                    enabled = !showCheckBox
                                ) {
                                    openDataPrivacy = true
                                    selectedCourse = course.courseID
                                }

                        ){

                            Text(text = course.courseID, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                            Text(text = course.courseName, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                        }

                    }
                }


            }
        }
        if(openDataPrivacy){
            Dialog(onDismissRequest = { openDataPrivacy = false}) {
                var checked by remember {
                    mutableStateOf(false)
                }
                var scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .fillMaxHeight(0.85f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                            .height(500.dp)
                            .verticalScroll(scrollState)
                            .padding(10.dp)

                    ) {
                        Text(text = "Directions:\n" +
                                "\n" +
                                "The form uses a 4-point rating scale.\n" +
                                "\n" +
                                "4: Strongly Agree\n" +
                                "3: Agree\n" +
                                "2: Disagree\n" +
                                "1: Strongly Disagree\n" +
                                "\n" +
                                "Please carefully read each statement and indicate whether you Strongly Agree, Agree, Disagree, or Strongly Disagree. Please make the response that best describe your teacher.\n" +
                                "\n" +
                                "This evaluation is CONFIDENTIAL. \n" +
                                "\n" +
                                "Confidential means that only HR and key personnel have access to information about who made the evaluation, but this information is not available to anyone outside the office. The results are kept confidential, and HR will never associate a survey respondent's identity with their evaluation response in any kind of reporting. The evaluation results are always aggregated. All evaluation results are combined together and presented as a group. Comments are never associated with a respondent's identity, however, the comments are reported verbatim, in random order."
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,

                    ){
                        Checkbox(checked = checked, onCheckedChange = { checked = !checked },enabled = scrollState.value == scrollState.maxValue)
                        Text("I have read the above statement")
                    }

                    Button(
                        onClick = {
                            viewModel.updateFormID()
                            studentNavController.popBackStack()
                            studentNavController.navigate(StudentNav.FORM.name+"/$selectedCourse")
                        },
                        enabled = checked
                    ){
                        Text("Confirm")
                    }
                }

            }
        }
        if(openDialog) {
            Dialog(onDismissRequest = { openDialog = false }) {
                val allCourses = viewModel.getAllCourses().collectAsState(null)
                var expanded by remember {
                    mutableStateOf(false)
                }
                Column(
                    modifier = Modifier
                        .height(300.dp)
                        .background(Color.White)
                        .padding(20.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    var selectedCourse1 by remember {
                        mutableStateOf(Course(0,"","", "", ""))
                    }

                    Text("Add Your Course".uppercase(), fontWeight = FontWeight.Bold)
                    Row (
                        modifier = Modifier
                            .clickable {
                                expanded = !expanded
                            }
                            .height(50.dp)
                            .fillMaxWidth()
                            .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                            .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        if(selectedCourse1.courseID.isBlank() && selectedCourse1.courseName.isBlank() ){
                            Text(text = "Select your Course")

                        }
                        else  Text(text = "${selectedCourse1.courseID} ${selectedCourse1.courseName}")
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.background(Color.White)
                        ) {
                            allCourses.value?.forEach { course ->
                                if (course.year == loginViewModel.year && course.program == loginViewModel.selectedCourse) {
                                    DropdownMenuItem(text = { Text(text = "${course.courseID} ${course.courseName}") }, onClick = { selectedCourse1 = course; expanded = false})
                                }
                            }
                        }
                        Icon(imageVector = if(expanded)Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown, contentDescription = "Selection")
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        val context = LocalContext.current
                        Button(onClick = {
                            if(selectedCourse1.courseID.isNotBlank()
                                && selectedCourse1.courseName.isNotBlank()
                                && selectedCourse1.year == loginViewModel.year
                                && selectedCourse1.program == loginViewModel.selectedCourse

                                ){
                                viewModel.upsertCourseStudent(CourseStudent(courseID = selectedCourse1.courseID, studentID = loginViewModel.userID))
                                viewModel.upsertCourse(selectedCourse1)
                                openDialog = false
                            }
                            else {
                                Toast.makeText(context, "You are not enrolled in selected course", Toast.LENGTH_SHORT,).show()
                            }

                        }, modifier = Modifier, shape = RoundedCornerShape(10.dp)) {
                            Text(text = "Add Course")
                        }

                        Button(onClick = {
                            openDialog = false
                        }, modifier = Modifier, shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Black)) {
                            Text(text = "Cancel")
                        }
                    }
                }
            }
        }
    }

}