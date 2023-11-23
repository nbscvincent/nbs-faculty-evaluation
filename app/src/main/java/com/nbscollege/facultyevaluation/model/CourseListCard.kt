package com.nbscollege.facultyevaluation.model

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.nbscollege.facultyevaluation.model.data.Course
import com.nbscollege.facultyevaluation.model.data.courseList
import com.nbscollege.facultyevaluation.model.data.facultyList
import com.nbscollege.facultyevaluation.navigation.routes.DashboardRoute
import com.nbscollege.facultyevaluation.navigation.routes.MainScreen



// Student Dashboard
@Composable
fun CourseCard(course: Course){

    var showDialog by remember {
        mutableStateOf(false)
    }
    var clicked by remember {
        mutableStateOf(false)
    }
    var enabled by remember {
        mutableStateOf(false)
    }
//    var clickedNext by remember {
//        mutableStateOf(false)
//    }
//    var clickedPrev by remember {
//        mutableStateOf(false)
//    }
    var qNo by remember {
        mutableIntStateOf(0)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 20.dp)
            .shadow(50.dp, RoundedCornerShape(10.dp))
            .clickable(
                onClick = {
//                    navController.navigate(DashboardRoute.Form.name)
                    showDialog = true
                }
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )

    ){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "${course.course}")
            Text(text = "${course.courseDesc}")
            Text(text = "${course.facultyProfData.fName} ${course.facultyProfData.lName}")
        }

    }
    if(showDialog){
        Dialog(
            onDismissRequest = { showDialog = false },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ){

            Column(
                modifier = Modifier
                    .fillMaxSize(0.95f)
                    .background(Color.White, RoundedCornerShape(10.dp)),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Text(
                    text = course.course,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black)
                        .padding(vertical = 10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 5.dp)
                        .border(1.dp, Color.Black)
                        .size(width = 250.dp, height = 520.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ){
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .border(1.dp, Color.Black),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        println("clicked: $clicked")
                        if(clicked){

                            when(qNo){

                                1 -> {
                                    Column(

                                    ){
                                        Text(text = "Question1")
                                    }
                                    Column(

                                    ) {
                                        Text(text = "Answer1")
                                    }
                                }
                                2 -> {
                                    Column(

                                    ){
                                        Text(text = "Question2")
                                    }
                                    Column(

                                    ) {
                                        Text(text = "Answer2")
                                    }
                                }
                                3 -> {
                                    Column(

                                    ){
                                        Text(text = "Question3")
                                    }
                                    Column(

                                    ) {
                                        Text(text = "Answer3")
                                    }
                                }

                            }
                        }

                    }

                }

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ){
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowLeft,
                        contentDescription = "Previous",
                        tint = Color.Black,
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    clicked = true
                                    qNo--
                                    if(qNo == 1){
                                        qNo = 1
                                        enabled = false
                                    }
                                    println("qNo = $qNo")

                                },
                                enabled = enabled



                            )
                            .size(30.dp))
                    println("e = $enabled")
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowRight,
                        contentDescription = "Next",
                        tint = Color.Black,
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    clicked = true
                                    qNo++
                                    if(qNo > 1){
                                        enabled = true
                                    }
                                    println("qNo = $qNo")
                                }
                            )
                            .size(30.dp))
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Button(
                        onClick = { showDialog = false },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray,
                            contentColor = Color.White
                        )) {
                        Text(text = "Cancel")
                    }
                    Button(
                        onClick = {

                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color.Red
                        )
                    ){
                        Text(text = "Submit")
                    }
                }

            }

        }
    }


    




}