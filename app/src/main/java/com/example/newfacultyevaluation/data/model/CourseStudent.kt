package com.example.newfacultyevaluation.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coursestudent", primaryKeys = ["courseID", "studentID"])
data class CourseStudent(
    val courseID: String,
    val studentID: String
)
//

/*
    CourseID    StudentID
    CSC123      200026
    CSC124      200026
 */