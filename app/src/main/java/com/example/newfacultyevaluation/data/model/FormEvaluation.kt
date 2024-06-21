package com.example.newfacultyevaluation.data.model

data class FormEvaluation(
    val formID : String,
    val totalPoints: Int,
    val comments : List<String>,
    val facultyName: String,
    val studentNo : String,
)
