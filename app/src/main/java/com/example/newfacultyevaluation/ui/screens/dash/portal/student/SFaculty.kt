package com.example.newfacultyevaluation.ui.screens.dash.portal.student

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LibraryAdd
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseStudent
import com.example.newfacultyevaluation.ui.FacultyAppViewModelProvider
import com.example.newfacultyevaluation.ui.nav.StudentNav
import com.example.newfacultyevaluation.ui.screens.auth.LoginViewModel

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SFaculty(
    loginViewModel: LoginViewModel,
    studentNavController: NavController,
    mainNav: NavController,
    viewModel: StudentViewModel
) {

    var openDialog by remember {
        mutableStateOf(false)
    }

    var openDataPrivacy by remember {
        mutableStateOf(false)
    }



    val courses = viewModel.getCoursesByStudentID(loginViewModel.userID).observeAsState()

    var selectedCourse by remember {
        mutableStateOf("")
    }
    BackHandler {
        studentNavController.popBackStack()
        studentNavController.navigate(StudentNav.HOME.name)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Row (
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            Icon(
                imageVector = Icons.Rounded.LibraryAdd,
                contentDescription = "Add Course",
                modifier = Modifier.clickable {
                    openDialog = true
                }
            )
        }

        if (courses.value == null) {
                Text(text = "No Courses Found", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "Tap \"+\" icon to enroll your courses.")
        } else {

            courses.value?.forEach { course ->

                Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .height(50.dp)
                            .fillMaxWidth()
                    ) {
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
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
        if(openDataPrivacy){
            Dialog(onDismissRequest = { openDataPrivacy = false}) {
                var checked by remember {
                    mutableStateOf(false)
                }

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
                            .verticalScroll(
                                rememberScrollState()
                            )
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
                        Checkbox(checked = checked, onCheckedChange = { checked = !checked })
                        Text("I have read the above statement")
                    }

                    Button(
                        onClick = {
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
                Column(
                    modifier = Modifier
                        .height(300.dp)
                        .background(Color.White)
                        .padding(20.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var courseID by remember {
                        mutableStateOf("")
                    }

                    var courseName by remember {
                        mutableStateOf("")
                    }
                    Text("Add Your Course".uppercase(), fontWeight = FontWeight.Bold)
                    OutlinedTextField(value = courseID, onValueChange = { courseID = it }, label = { Text("Enter Course Code")} )
                    OutlinedTextField(value = courseName, onValueChange = { courseName = it }, label = { Text("Enter Course Name")}  )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        val context = LocalContext.current
                        Button(onClick = {
                            if(courseID.isNotBlank() && courseName.isNotBlank()){
                                viewModel.upsertCourseStudent(CourseStudent(courseID = courseID, studentID = loginViewModel.userID))
                                viewModel.upsertCourse(Course(courseID, courseName))
                                openDialog = false
                            } else {
                                Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_SHORT).show()
                            }

                        }) {
                            Text(text = "Add Course")
                        }

                        Button(onClick = {
                            openDialog = false
                        }) {
                            Text(text = "Cancel")
                        }
                    }
                }
            }
        }
    }

}