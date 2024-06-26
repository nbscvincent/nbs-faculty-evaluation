package com.example.newfacultyevaluation.ui.screens.dash.portal.student

import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.Form
import com.example.newfacultyevaluation.data.model.FormEvaluation
import com.example.newfacultyevaluation.data.model.FormStudentFaculty
import com.example.newfacultyevaluation.data.model.Question
//import com.example.newfacultyevaluation.data.model.questions
import com.example.newfacultyevaluation.ui.nav.StudentNav
import com.example.newfacultyevaluation.ui.screens.auth.LoginViewModel
import kotlin.math.floor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Form(
    navController: NavController,
    viewModel: StudentViewModel,
    loginViewModel: LoginViewModel,
    selectedCourse: String,
    facultyID: String
) {
    println("sjhdakjdsha ${viewModel.formID.value}")
    println("Faculty ID QForm: $facultyID $selectedCourse")


    var overallPoints by remember {
        mutableIntStateOf(0)
    }

    var completed by remember {
        mutableStateOf(false)
    }

    BackHandler {
        viewModel.resetAnsweredQuestions()
        navController.popBackStack()
        navController.navigate(StudentNav.FORM.name+"/$selectedCourse/$facultyID")
    }

    Column(
        modifier = Modifier
            .padding(20.dp),
    ) {
        println("SLC: $selectedCourse")
        QuestionCard(navController, viewModel, loginViewModel, selectedCourse, facultyID)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionCard(
    navController: NavController,
    viewModel: StudentViewModel,
    loginViewModel: LoginViewModel,
    selectedCourse: String,
    facultyID: String
) {
    var feedback by remember {
        mutableStateOf("")
    }
    val points = listOf(4,3,2,1)
//    val faculty = viewModel.getStudentFaculty(loginViewModel.userID, selectedCourse).collectAsState(null)
    val feedbacks by remember {
        mutableStateOf("")
    }
    println("Faculty ID QCard: $facultyID $selectedCourse")

    val questions by viewModel.getAllQuestions().collectAsState(initial = null)
    Text(text = "Evaluation for Instructor $facultyID".uppercase(), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), fontWeight = FontWeight.Bold, fontSize = 15.sp)
    Text(text = "Answered Questions: ${viewModel.answeredQuestions.value}")
    Text(text = "Total Points: ${viewModel.totalPoints.value}")
    Spacer(modifier = Modifier.height(2.dp))

    Text(    "" +
            "4: Strongly Agree " +
            "3: Agree " +
            "2: Disagree " +
            "1: Strongly Disagree", fontSize = 15.sp)
    Spacer(modifier = Modifier.height(2.dp))


    LazyColumn(
        modifier = Modifier.height(400.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(questions.orEmpty()) { question ->
            if (question.id.toInt() < 18) {
                var selectedPoint by rememberSaveable {
                    mutableIntStateOf(0)
                }
                var initial by rememberSaveable {
                    mutableIntStateOf(0)
                }
                Card(
                    modifier = Modifier.padding(vertical = 10.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(text = "${question.id}. ${question.question}", textAlign = TextAlign.Justify)
                        points.forEach { point ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = point == selectedPoint,
                                    onClick = {
                                        initial = selectedPoint
                                        selectedPoint = point
                                        viewModel.updateTotalPoints(selectedPoint - initial)
                                        viewModel.markQuestionAnswered(questionId = question.id.toInt())
                                    })
                                Text("$point")
                                Spacer(modifier = Modifier.width(8.dp)) // Adjust spacing as needed
                                Text(
                                    text = when (point) {
                                        4 -> "-  Strongly Agree"
                                        3 -> "-  Agree"
                                        2 -> "-  Disagree"
                                        1 -> "-  Strongly Disagree"
                                        else -> ""
                                    },
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                    }
                }
            } else {

                Card(
                    modifier = Modifier.padding(vertical = 10.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(text = "${question.id}. ${question.question}", textAlign = TextAlign.Justify)
                        OutlinedTextField(
                            value = feedback,
                            onValueChange = {
                                feedback = it
                                viewModel.markQuestionAnswered(questionId = question.id.toInt())
                            },
                            label = { Text(text = "Comment") }
                        )
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        Button(
            onClick = {
////                viewModel.upsertFormStudentFaculty(FormStudentFaculty(viewModel.formID.value, loginViewModel.userID, faculty.value?.facultyID.toString()))
//                feedbacks.forEach {
////                    viewModel.upsertForm(Form(viewModel.formID.value, overallPoints = viewModel.totalPoints.value, feedback = it))
//                }
                val formID = floor(Math.random() * 100000)
                println("Faculty ID QCard Btn: $facultyID $selectedCourse")

                viewModel.insertFormEvaluation(
                    FormEvaluation(
                        formID = formID.toString(),
                        comments = feedback,
                        facultyID = facultyID,
                        studentNo = loginViewModel.userID,
                        totalPoints = viewModel.totalPoints.value.toString(),
                        courseCode = selectedCourse
                    ))
                // Mark the evaluation as completed
                viewModel.markEvaluationAsCompleted(selectedCourse, loginViewModel.userID, context)
                Toast.makeText(context, "Form submitted", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
                navController.navigate(StudentNav.HOME.name)
            },
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            enabled = viewModel.answeredQuestions.value >= 18
        ) {
            Text("Submit")
        }
    }
}