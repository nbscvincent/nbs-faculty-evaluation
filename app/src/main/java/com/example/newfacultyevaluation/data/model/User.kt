package com.example.newfacultyevaluation.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Suppress("PLUGIN_IS_NOT_ENABLED")
@Serializable
@Entity(tableName = "user")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "userid")
    val userID: String,
    val fullName: String?,
    val password: String,
    val year: String?,
    val selectedCourse: String?,
    val role: String,
    val dateCreated: String?
)

