package com.example.newfacultyevaluation.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course")
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo("courseid")
    val courseID: String,
    val courseName: String,
    val year: String,
    val program: String,
)

//val courses = listOf<Course>(
//
//)