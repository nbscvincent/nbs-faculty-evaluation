package com.example.newfacultyevaluation.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student(
    @PrimaryKey
    @ColumnInfo(name = "studentid")
    val studentID: String,
    val fullName: String,
    val password: String,
    val selectedCourse: String?,
    val role: String,
    val dateCreated: String
)
