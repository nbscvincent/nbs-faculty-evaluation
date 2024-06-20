package com.example.newfacultyevaluation.ui.screens.dash.portal.student

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LibraryAdd
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.LibraryAdd
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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

    var showOptions by remember { mutableStateOf(false) }
    var showCheckBox by remember { mutableStateOf(false) }
    var openDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var openDataPrivacy by rememberSaveable {
        mutableStateOf(false)
    }

    val courses =
        viewModel.getAllCourses().collectAsState(null)
    println("Course List: ${courses.value}")
    var selectedCourse by remember {
        mutableStateOf("")
    }

    val scrollState = rememberScrollState()

    var year by remember {
        mutableStateOf("")
    }
    BackHandler {
        studentNavController.popBackStack()
        studentNavController.navigate(StudentNav.HOME.name)
    }


    Box(modifier = Modifier.fillMaxSize()) {
    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.White).verticalScroll(scrollState),
    ) {
        var showCheckBox by remember {
            mutableStateOf(false)
        }
        Spacer(modifier = Modifier.height(15.dp))

        Column(modifier = Modifier.fillMaxWidth(),){
            Row {
                Text(
                    "Student Number:",
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = Color.Black
                )

                Text(
                    text = loginViewModel.userID,
                    modifier = Modifier.padding(start = 5.dp),
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }

            Row {
                Text(
                    "Student Name:",
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = Color.Black
                )

                Text(
                    text = loginViewModel.fullName,
                    modifier = Modifier.padding(start = 5.dp),
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = Color.Black
                )

            }

            Row {
                Text(
                    "Course/Year:",
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = Color.Black
                )

                Text(
                    text = "${loginViewModel.selectedCourse}${loginViewModel.year}",
                    modifier = Modifier.padding(start = 5.dp),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = Color.Black
                )

            }

        }
        Spacer(modifier = Modifier.height(20.dp))

        if (courses.value == null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = "No Courses Found", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(text = "Tap \"+\" icon to enroll your courses.", color = Color.Black)
            }
        } else {

            val filteredCourses = courses.value!!.filter {
                it.yearLevel == loginViewModel.year && it.program == loginViewModel.selectedCourse
            }
            filteredCourses.forEach { course ->
                val isEvaluationCompleted = viewModel.isEvaluationCompleted(course.courseCode, loginViewModel.userID)

                if (!isEvaluationCompleted) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding()
                                    .fillMaxWidth()
                                    .height(190.dp).background(color = Color.White)

                            ) {
                                if (showCheckBox) {
                                    Button(
                                        onClick = {
            //                                viewModel.deleteCourse(
            //                                    CourseStudent(
            //                                        course.courseID,
            //                                        loginViewModel.userID
            //                                    )
            //                                )
                                        },
                                        shape = RoundedCornerShape(10.dp),
                                        modifier = Modifier.size(40.dp),
                                        contentPadding = PaddingValues(10.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.DeleteOutline,
                                            contentDescription = "Delete"
                                        )
                                    }
                                }

                                Card(
                                    elevation = CardDefaults.cardElevation(8.dp),
                                    modifier = Modifier

                                        .clickable() {

                                        }
                                        .padding(5.dp)
                                        .fillMaxWidth()
                                        .background(color = Color.White),
                                    shape = RoundedCornerShape(4.dp),
                                    ) {
                                    Column(
                                        modifier = Modifier.background(Color.White).fillMaxWidth()
                                    ) {

                                        Text(
                                            text = "${course.courseCode} - ${course.courseName}",
                                            modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 10.dp),
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Left,
                                            fontSize = 20.sp,
                                            color = Color.Black
                                        )

                                        Text(
                                            text = "Instructor",
                                            modifier = Modifier.padding(top = 10.dp, start = 20.dp),
                                            fontWeight = FontWeight.Light,
                                            textAlign = TextAlign.Center,
                                            fontSize = 20.sp,
                                            color = Color.Black
                                        )

            //                            val faculty =
            //                                viewModel.getStudentFaculty(loginViewModel.userID, course.courseID)
            //                                    .collectAsState(null)
                                        // Display the faculty name if available
            //                            val facultyNameText =
            //                                if (faculty.value != null) "Mr./Ms. ${faculty.value?.fullName}".uppercase() else "Faculty Name Not Available"
                                        Text(
                                            text = "${course.facultyName}",
                                            textAlign = TextAlign.Left,
                                            modifier = Modifier.padding(top = 10.dp, start = 20.dp),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 20.sp,
                                            color = Color.Black
                                        )

                                        Spacer(modifier = Modifier.weight(1f))

                                        // Evaluate button positioned in the bottom right corner
                                        Button(
                                            onClick = {
                                                if (!showCheckBox) {
                                                    openDataPrivacy = true
                                                    selectedCourse = course.courseCode
                                                }
                                            },
                                            modifier = Modifier
                                                .padding(end = 10.dp, bottom = 5.dp)
                                                .align(Alignment.End)
                                        ) {
                                            Text(text = "Evaluate")
                                        }

                                    }

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier.fillMaxSize().background(Color.DarkGray)
                                    ) {


                                    }
                                }

                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(1.dp))

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
                        .background(MaterialTheme.colorScheme.background)
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
//                val allCourses = viewModel.getAllCourses().collectAsState(null)
                val isDarkTheme = isSystemInDarkTheme()
                val backgroundColor = if (isDarkTheme) Color.Black else Color.White
                val bg = MaterialTheme.colorScheme.background
                var expanded by remember {
                    mutableStateOf(false)
                }

                Column(
                    modifier = Modifier
                        .height(300.dp)
                        .background(bg)
                        .padding(20.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    var selectedCourse1 by remember {
                        mutableStateOf(Course("","","", "", ""))
                    }

                    Text("Add Your Course".uppercase(), fontWeight = FontWeight.Bold)
                    val textColor = if (isDarkTheme) Color.White else Color.Black
                    val borderColor = if (isDarkTheme) Color.White else Color.Black
                    Row (
                        modifier = Modifier
                            .clickable {
                                expanded = !expanded
                            }
                            .height(50.dp)
                            .fillMaxWidth()
                            .border(1.dp, borderColor, RoundedCornerShape(10.dp))
                            .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        if(selectedCourse1.courseCode.isBlank() && selectedCourse1.courseName.isBlank() ){
                            Text(text = "Select your Course")

                        }
                        else  Text(text = "${selectedCourse1.courseCode} ${selectedCourse1.courseName}")
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.background( MaterialTheme.colorScheme.background)
                        ) {
//                            allCourses.value?.forEach { course ->
//                                if (course.year == loginViewModel.year && course.program == loginViewModel.selectedCourse) {
//                                    DropdownMenuItem(text = { Text(text = "${course.courseID} ${course.courseName}") }, onClick = { selectedCourse1 = course; expanded = false})
//                                }
//                            }
                            courses.value?.forEach { course ->
                                DropdownMenuItem(text = { Text(text = "${course.courseCode} ${course.courseName}") }, onClick = { selectedCourse1 = course; expanded = false})

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
                            if(selectedCourse1.courseCode.isNotBlank()
                                && selectedCourse1.courseName.isNotBlank()
                                && selectedCourse1.yearLevel == loginViewModel.year
                                && selectedCourse1.courseName == loginViewModel.selectedCourse

                                ){
//                                viewModel.upsertCourseStudent(CourseStudent(courseID = selectedCourse1.courseID, studentID = loginViewModel.userID))
//                                viewModel.upsertCourse(selectedCourse1)
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

        FloatingActionButton(
            onClick = { showOptions = !showOptions },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color.Red
        ) {
            Icon(Icons.Filled.LibraryAdd, contentDescription = "More options")
        }

    if (showOptions) {
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 80.dp)
        ) {

            FloatingActionButton(
                onClick = { openDialog = true },
                containerColor = Color.Red
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Course")
            }

            FloatingActionButton(
                onClick = { showCheckBox = !showCheckBox },
                containerColor = Color.Red
            ) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete Course")
            }


        }
    }
}



}

