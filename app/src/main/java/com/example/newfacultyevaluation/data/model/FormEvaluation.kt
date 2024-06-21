package com.example.newfacultyevaluation.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FormEvaluation(
    val formID : String,
    val totalPoints: String,
    val comments : String,
    val facultyID: String,
    val studentNo : String,
    val courseCode: String
)
