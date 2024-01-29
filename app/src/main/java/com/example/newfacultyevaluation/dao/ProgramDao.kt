package com.example.newfacultyevaluation.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.example.newfacultyevaluation.data.model.Program
import com.example.newfacultyevaluation.data.model.User
@Dao
interface ProgramDao {

    @Upsert
    suspend fun upsertProgram(program: Program)
}