package com.nbscollege.facultyevaluation.model

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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
fun CourseCard(course: Course, navController: NavController){

    var showDialog by remember {
        mutableStateOf(false)
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
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp)),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = "Evaluation Form")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
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