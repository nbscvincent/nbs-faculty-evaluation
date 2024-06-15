package com.example.newfacultyevaluation.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FormStudentFaculty(
    @PrimaryKey
    val formID: Int,
    val studentID: String,
    val facultyID: String
)
