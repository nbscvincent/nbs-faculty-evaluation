package com.example.newfacultyevaluation.ui.screens.dash.portal.student

import android.widget.RadioGroup
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newfacultyevaluation.data.model.Question
import com.example.newfacultyevaluation.data.model.questions
import com.example.newfacultyevaluation.ui.nav.StudentNav

@Composable
fun Form(
    navController: NavController
) {

    val formID by remember{
        mutableIntStateOf((Math.random() * 10000).toInt())
    }
    var overallPoints by remember {
        mutableIntStateOf(0)
    }




    BackHandler {
        navController.popBackStack()
        navController.navigate(StudentNav.HOME.name)
    }

    Column(
        modifier = Modifier.padding(20.dp).fillMaxSize(),
    ){
        QuestionCard()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionCard(): Int {
    val points = listOf(4,3,2,1)
    var totalPoints by remember {
        mutableIntStateOf(0)
    }

    Text(text = "$totalPoints")
    LazyColumn(
        modifier = Modifier.height(600.dp),
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
                Card {
                    Column {
                        Text(text = "${question.id}")
                        Text(text = question.question)
                        points.forEach { point ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = point == selectedPoint,
                                    onClick = { initial = selectedPoint;selectedPoint = point })
                                Text(text = "$point")

                            }
                        }

                    }
                }
                DisposableEffect(selectedPoint){
                    if(selectedPoint != initial){
                        totalPoints += selectedPoint - initial
                    }
                    onDispose {  }
                }
            }else {
                var feedback by remember {
                    mutableStateOf("")
                }
                Card {
                    Column {
                        Text(text = "${question.id}")
                        Text(text = question.question)
                        OutlinedTextField(
                            value = feedback,
                            onValueChange = { feedback = it },
                            label = { Text(text = "Your Answer")}
                        )

                    }
                }
            }


        }
    }
    return totalPoints
}

