package com.example.newfacultyevaluation.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Course(
    @PrimaryKey
    val courseCode: String,
    val courseName: String,
    val yearLevel: String,
    val facultyName: String,
)

//val courses = listOf<Course>(
//
//)