package com.example.newfacultyevaluation.ui.screens.dash.portal.faculty

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.repo.FacultyRepo
import kotlinx.coroutines.flow.Flow

class FacultyViewModel(
//    private val facultyRepo: FacultyRepo
): ViewModel() {

//    fun getCourses(id: String): Flow<List<Course>> {
//        return facultyRepo.getCourses(id)
//    }
//
//    fun getStudentCountAnswered(id: String): Flow<Int>{
//        return facultyRepo.getStudentCount(id)
//    }
//
//    fun getOverallPoints(id: String): Flow<Int>{
//        return facultyRepo.getOverallPoints(id)
//    }

    fun getOverallAverage(userid: String): Flow<Double> {
        return facultyRepo.getOverallAverage(userid)
    }

}