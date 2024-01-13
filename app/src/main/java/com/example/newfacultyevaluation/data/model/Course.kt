package com.example.newfacultyevaluation.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course")
data class Course(
    @PrimaryKey
    @ColumnInfo("courseid")
    val courseID: String,
    val courseName: String
)

//val courses = listOf<Course>(
//
//)