package com.example.newfacultyevaluation.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Upsert
import com.example.newfacultyevaluation.data.model.Student
import com.example.newfacultyevaluation.data.model.User

@Dao
interface StudentDao {

    @Upsert
    suspend fun upsertStudent(student: Student)

}