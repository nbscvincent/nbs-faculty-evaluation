package com.example.newfacultyevaluation.ui.screens.dash.portal.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.newfacultyevaluation.data.model.Course

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCourses() {
    var courses by remember {
        mutableStateOf(mutableStateListOf(Course("", "")))
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn {
            items(courses.size - 1) { index ->
                OutlinedTextField(
                    value = courses[index + 1].courseID,
                    onValueChange = { newValue ->
                        courses[index + 1] = courses[index + 1].copy(courseID = newValue)
                    },
                    label = { Text("Enter Course Code") }
                )
                OutlinedTextField(
                    value = courses[index + 1].courseName,
                    onValueChange = { newValue ->
                        courses[index + 1] = courses[index + 1].copy(courseName = newValue)
                    },
                    label = { Text("Enter Course Name") }
                )
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete",
                    modifier = Modifier.clickable {
                        // Handle adding the new course to the list
                        courses.removeAt(index+1)
                    }
                )
            }
        }

        // Additional row for entering a new course
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = courses[0].courseID,
                onValueChange = { newValue ->
                    courses[0] = courses[0].copy(courseID = newValue) },
                label = { Text("Enter Course Code") }
            )

            OutlinedTextField(
                value = courses[0].courseName,
                onValueChange = { newValue ->
                    courses[0] = courses[0].copy(courseName = newValue) },
                label = { Text("Enter Course Name") }
            )

            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "Add",
                modifier = Modifier.clickable {
                    // Handle adding the new course to the list
                    courses.add(Course("",""))
                }
            )
        }
    }
}
