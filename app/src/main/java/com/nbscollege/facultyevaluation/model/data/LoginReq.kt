package com.nbscollege.facultyevaluation.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class LoginReq(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val studentNo: String,
    val password: String
)

val userList = listOf(
    RegistrationReq("101","Jerald", "Ysaac", "je@email.com", "123"),
    RegistrationReq("102","Jerald2", "Ysaac2", "je2@email.com", "1232"),
    RegistrationReq("103","Jerald3", "Ysaac3", "je3@email.com", "1233")
)



