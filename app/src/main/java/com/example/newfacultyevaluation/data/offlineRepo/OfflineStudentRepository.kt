package com.example.newfacultyevaluation.data.offlineRepo

import com.example.newfacultyevaluation.dao.StudentDao
import com.example.newfacultyevaluation.data.model.Student
import com.example.newfacultyevaluation.data.repo.StudentRepo

class OfflineStudentRepository(private val studentDao: StudentDao): StudentRepo {
    override suspend fun upsertStudent(student: Student) {
        studentDao.upsertStudent(student)
    }
}