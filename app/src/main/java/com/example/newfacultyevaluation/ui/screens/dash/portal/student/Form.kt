package com.example.newfacultyevaluation.ui.screens.dash.portal.student

import android.widget.RadioGroup
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newfacultyevaluation.data.model.Question
import com.example.newfacultyevaluation.data.model.questions
import com.example.newfacultyevaluation.ui.nav.StudentNav

@Composable
fun Form(
    navController: NavController,
    viewModel: StudentViewModel
) {



    val formID by remember{
        mutableIntStateOf((Math.random() * 100000).toInt())
    }
    var overallPoints by remember {
        mutableIntStateOf(0)
    }

    var completed by remember {
        mutableStateOf(false)
    }



    BackHandler {
        viewModel.resetAnsweredQuestions()
        navController.popBackStack()
        navController.navigate(StudentNav.FORM.name)

    }

    Column(
        modifier = Modifier
            .padding(20.dp),
    ){

        QuestionCard(viewModel)

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionCard(
    viewModel: StudentViewModel
){
    val points = listOf(4,3,2,1)

    Text(text = "Answered Questions: ${viewModel.answeredQuestions.value}")
    Text(text = "Total Points: ${viewModel.totalPoints.value}")


    LazyColumn(
        modifier = Modifier.height(500.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        items(questions){question ->
            if(question.id < 17) {
                var selectedPoint by rememberSaveable {
                    mutableIntStateOf(0)
                }
                var initial by rememberSaveable {
                    mutableIntStateOf(0)
                }
                Card (
                    modifier = Modifier.padding(vertical = 10.dp)
                ){

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(text = "${question.id}")
                        Text(text = question.question)
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
                                        viewModel.markQuestionAnswered(questionId = question.id)
                                    })
                                Text("$point")
                            }
                        }



                    }

                }

            }else {
                var feedback by remember {
                    mutableStateOf("")
                }
                Card(
                    modifier = Modifier.padding(vertical = 10.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(text = "${question.id}")
                        Text(text = question.question)
                        OutlinedTextField(
                            value = feedback,
                            onValueChange = {
                                feedback = it
                                viewModel.markQuestionAnswered(questionId = question.id)

                            },
                            label = { Text(text = "Your Answer")}
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

        Button(
            onClick = {},
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            enabled = viewModel.answeredQuestions.value >= 18
        ){
            Text("Submit Form")
        }
    }

}

