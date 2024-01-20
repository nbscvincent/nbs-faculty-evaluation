package com.example.newfacultyevaluation.ui.screens.dash.portal.faculty

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.repo.FacultyRepo

class FacultyViewModel(private val facultyRepo: FacultyRepo): ViewModel() {

    fun getCourses(id: String): LiveData<List<CourseFaculty>>{
        return facultyRepo.getCourses(id)
    }

}