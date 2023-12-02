package com.nbscollege.facultyevaluation.user.model

data class RegistrationReq(
    val studentNo: String,
    val fName: String,
    val lName: String,
    val email: String,
    val password: String
)


