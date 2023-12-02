package com.nbscollege.facultyevaluation.course.model

import com.nbscollege.facultyevaluation.user.model.FacultyProfData
import com.nbscollege.facultyevaluation.user.model.facultyList

data class Course(
    val course: String,
    val courseDesc: String,
    val facultyProfData: FacultyProfData,
)

val courseList = listOf(
    Course("Operating System", "CSC 211", facultyList[0]),
    Course("Operating System 2", "CSC 212", facultyList[1]),
    Course("Operating System 3", "CSC 213", facultyList[2]),
    Course("Operating System 4", "CSC 214", facultyList[3]),
    Course("Operating System 5", "CSC 215", facultyList[4]),
    Course("Operating System 6", "CSC 216", facultyList[5])
)
