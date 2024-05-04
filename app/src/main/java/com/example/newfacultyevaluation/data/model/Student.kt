package com.example.newfacultyevaluation.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Year

@Entity(tableName = "student")
data class Student(
    @PrimaryKey
    @ColumnInfo(name = "studentid")
    val studentID: String,
    val fullName: String,
    val password: String,
    val selectedCourse: String?,
    val role: String,
    val year: String,
    val dateCreated: String
)
