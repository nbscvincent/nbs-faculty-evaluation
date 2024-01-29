package com.example.newfacultyevaluation.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coursefaculty")
data class CourseFaculty(
    @PrimaryKey
    val courseCode: String,
    val facultyID: String
)
