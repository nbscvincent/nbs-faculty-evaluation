package com.example.newfacultyevaluation.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "user")
@Serializable
data class User(
    @PrimaryKey
    @ColumnInfo(name = "userid")
    val userID: String,
    val fullName: String?,
    val password: String,
    val selectedCourse: String?,
    val role: String,
    val year: String?,
    val dateCreated: String?
)
