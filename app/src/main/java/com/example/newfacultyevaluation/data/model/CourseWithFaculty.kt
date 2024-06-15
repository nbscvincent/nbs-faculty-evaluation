package com.example.newfacultyevaluation.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class CourseWithFaculty(
    @Embedded val course: Course,
    @ColumnInfo(name = "facultyName") val facultyName: String

)