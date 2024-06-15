package com.example.newfacultyevaluation.data.repo

import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.Program

interface ProgramRepo {

    suspend fun upsertProgram(program: Program)
}