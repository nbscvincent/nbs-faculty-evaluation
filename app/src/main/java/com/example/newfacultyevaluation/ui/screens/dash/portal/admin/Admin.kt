package com.example.newfacultyevaluation.ui.screens.dash.portal.admin

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPortal() {
    var facultyID by rememberSaveable {
        mutableStateOf("")
    }
    var program by rememberSaveable {
        mutableStateOf("")
    }

    val courses by remember {
        mutableStateOf(mutableStateListOf(""))
    }

    Column {
        Text(text = "Add FacultyID")
        OutlinedTextField(
            value = facultyID,
            onValueChange = { facultyID = it },
            label = { Text(text = "Enter Faculty ID") }
            )
        Text(text = "Add Program")
        OutlinedTextField(
            value = program,
            onValueChange = { program = it },
            label = { Text(text = "Enter Program") }
        )
        Text(text = "Add Courses handled")
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),

        ) {
            LazyColumn(
                reverseLayout = true
            ){
                item {
                    Row(
                        modifier = Modifier.height(60.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        OutlinedTextField(
                            value = courses[0],
                            onValueChange = {courses[0] = it},
                            label = { Text(text = "Enter Course Code") }
                        )
                        Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add", modifier = Modifier
                            .clickable {
                                courses.add("")
                            }
                            .size(40.dp))
                    }
                }
                items(courses.size-1){index ->
                    Row(
                        modifier = Modifier.height(60.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        OutlinedTextField(
                            value = courses[index+1],
                            onValueChange = { newValue -> courses[index+1] = newValue},
                            label = { Text(text = "Enter Course Code") }
                        )
                        Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Add", modifier = Modifier
                            .clickable {
                                courses.removeAt(index)
                            }
                            .size(40.dp))
                    }
                }

            }
        }


    }

}
