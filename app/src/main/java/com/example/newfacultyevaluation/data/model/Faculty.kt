package com.example.newfacultyevaluation.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "faculty")
data class Faculty(
    @PrimaryKey
    @ColumnInfo("facultyid")
    val facultyID: String,
    val fullName: String,
    val password: String,
    val program: String
)
