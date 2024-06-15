package com.example.newfacultyevaluation.data.offlineRepo

import com.example.newfacultyevaluation.dao.FacultyDao
import com.example.newfacultyevaluation.dao.ProgramDao
import com.example.newfacultyevaluation.data.model.Program
import com.example.newfacultyevaluation.data.repo.ProgramRepo

class OfflineProgramRepository(private val programDao: ProgramDao): ProgramRepo {
    override suspend fun upsertProgram(program: Program) {
        programDao.upsertProgram(program)
    }
}