package com.nbscollege.facultyevaluation.model.data

import com.nbscollege.facultyevaluation.navigation.routes.DashboardRoute
import com.nbscollege.facultyevaluation.navigation.routes.MainScreen

data class Course(
    val course: String,
    val courseDesc: String,
    val facultyProfData: FacultyProfData,
    val route: String
)

val courseList = listOf(
    Course("Operating System", "CSC 211", facultyList[0], DashboardRoute.Form.name),
    Course("Operating System 2", "CSC 212", facultyList[1], DashboardRoute.Form.name),
    Course("Operating System 3", "CSC 213", facultyList[2], DashboardRoute.Form.name),
    Course("Operating System 4", "CSC 214", facultyList[3], DashboardRoute.Form.name),
    Course("Operating System 5", "CSC 215", facultyList[4], DashboardRoute.Form.name),
    Course("Operating System 6", "CSC 216", facultyList[5], DashboardRoute.Form.name)
)
