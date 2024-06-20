package com.example.newfacultyevaluation.data.repo

import androidx.lifecycle.LiveData
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.Faculty
import kotlinx.coroutines.flow.Flow

interface FacultyRepo {
    suspend fun upsertFaculty(faculty: Faculty)
    fun getCourses(id: String): Flow<List<Course>>

    fun getStudentCount(id: String): Flow<Int>

    fun getOverallPoints(id: String): Flow<Int>

    fun getOverallAverage(id: String): Flow<Double>
}