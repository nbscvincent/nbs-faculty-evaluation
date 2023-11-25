package com.nbscollege.facultyevaluation.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nbscollege.facultyevaluation.model.data.courseList
import com.nbscollege.facultyevaluation.model.data.questionList

@Composable
fun HomeDash(navController: NavController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Text(text = "Dashboard", color = Color.Black, fontSize = 20.sp, modifier = Modifier.padding(10.dp), fontWeight = FontWeight.Bold)

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            items(courseList){
                    course ->
                CourseCard(course = course)

            }
        }

    }

}