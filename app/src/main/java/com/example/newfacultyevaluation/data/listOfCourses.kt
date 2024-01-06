package com.example.newfacultyevaluation.data

data class Course(
    val name: String,
    val isCompleted: Boolean
)

val courses = listOf<Course>(
    Course("DAA", false),
    Course("DAA2", true),
    Course("DAA3", false),
    Course("KS4", true),
    Course("KS5", true),
)