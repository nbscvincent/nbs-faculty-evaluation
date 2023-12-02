package com.nbscollege.facultyevaluation.user.model

data class FacultyProfData(
    val empId: String,
    val fName: String,
    val lName: String
)

val facultyList = listOf(
    FacultyProfData("0001", "Claire", "Terumba"),
    FacultyProfData("0002", "Josie", "Cola"),
    FacultyProfData("0003", "Xyrie", "Sprite"),
    FacultyProfData("0004", "Zeus", "Royal"),
    FacultyProfData("0005", "Hymen", "Cobra"),
    FacultyProfData("0006", "Hola", "Hymn")
)
