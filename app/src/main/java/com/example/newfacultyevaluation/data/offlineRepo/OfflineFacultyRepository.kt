package com.example.newfacultyevaluation.data.offlineRepo

import androidx.lifecycle.LiveData
import com.example.newfacultyevaluation.dao.FacultyDao
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.repo.FacultyRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class OfflineFacultyRepository(private val facultyDao: FacultyDao): FacultyRepo {

    override suspend fun upsertFaculty(faculty: Faculty) = facultyDao.upsertFaculty(faculty)
    override fun getCourses(id: String): Flow<List<Course>> {
        return facultyDao.getCourses(id)
    }
    override fun getStudentCount(id: String): Flow<Int> {
        return facultyDao.getStudentCount(id)
    }

    override fun getOverallPoints(id: String): Flow<Int>{
        return facultyDao.getOverallPoints(id)
    }

    override fun getOverallAverage(id: String): Flow<Double> {
        return combine(
            getOverallPoints(id),
            getStudentCount(id)
        ) { overallPoints, studentCount ->
            if (studentCount > 0) {
                overallPoints.toDouble() / studentCount
            } else {
                0.0
            }
        }
    }

}