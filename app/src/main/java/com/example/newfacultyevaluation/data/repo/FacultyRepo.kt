package com.example.newfacultyevaluation.data.repo

import androidx.lifecycle.LiveData
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.FormEvaluation
import kotlinx.coroutines.flow.Flow

interface FacultyRepo {
    fun getFormEvaluation(facultyID : String) : Flow<List<FormEvaluation>>
}