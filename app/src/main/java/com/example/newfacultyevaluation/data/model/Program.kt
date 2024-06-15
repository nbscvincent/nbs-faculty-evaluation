package com.example.newfacultyevaluation.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "program")
data class Program(
    @PrimaryKey
    @ColumnInfo("programid")
    val programID: String,
    val programName: String
)
