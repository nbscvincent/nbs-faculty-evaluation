package com.nbscollege.facultyevaluation.model

import android.provider.ContactsContract

data class Register(
    var studentNo: String,
    var fName: String,
    var lName: String,
    var email: String,
    var password: String
)
