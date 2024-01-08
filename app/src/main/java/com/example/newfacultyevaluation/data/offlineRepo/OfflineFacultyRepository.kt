package com.example.newfacultyevaluation.data.offlineRepo

import com.example.newfacultyevaluation.dao.FacultyDao
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.repo.FacultyRepo

class OfflineFacultyRepository(private val facultyDao: FacultyDao): FacultyRepo {

    override suspend fun upsertFaculty(faculty: Faculty) = facultyDao.upsertFaculty(faculty)
}