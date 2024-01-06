package com.example.newfacultyevaluation.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "userid")
    val userID: String,
    val fullName: String,
    val password: String,
    val selectedCourse: String,
    val role: String,
    val status: Boolean
)
