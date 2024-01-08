package com.example.newfacultyevaluation.ui.screens.dash.portal.student

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newfacultyevaluation.data.model.Course

@Composable
fun Courses(
    navController: NavController
) {
    var completed: ArrayList<Course> = ArrayList()
    var pending: ArrayList<Course> = ArrayList()

    val scrollState = rememberScrollState()


//    courses.forEach { course ->
//        if(course.isCompleted){
//            completed.add(course)
//        }else{
//            pending.add(course)
//        }
//    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Dashboard",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier
                .scrollable(scrollState, orientation = Orientation.Vertical)
                .padding(20.dp)
        ) {


            Text(text = "Completed", fontSize = 30.sp, letterSpacing = 5.sp)
            Divider(color = Color.Red, modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(0.7f), thickness = 5.dp)
            getCourses(courseList = completed)
            Text(text = "Pending", fontSize = 30.sp, letterSpacing = 5.sp)
            Divider(color = Color.Red, modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(0.7f), thickness = 5.dp)
            getCourses(courseList = pending)
        }

    }
}