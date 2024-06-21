package com.example.newfacultyevaluation.ui.screens.dash.portal.faculty

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.FormEvaluation
import com.example.newfacultyevaluation.data.online.OnlineFacultyRepository
import com.example.newfacultyevaluation.data.repo.FacultyRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FacultyViewModel(
//    private val facultyRepo: FacultyRepo
    private val onlineFacultyRepository: OnlineFacultyRepository
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

//    fun getOverallAverage(userid: String): Flow<Double> {
//        return onlineFacultyRepository.getOverallAverage(userid)
//    }

    fun getFormEvaluation(facultyID : String) : Flow<List<FormEvaluation>>{
        return onlineFacultyRepository.getFormEvaluation(facultyID)
    }
    fun countStudentsAnswered(forms: List<FormEvaluation>){

        var count = 0
        viewModelScope.launch {
            forms.forEach {
                println("FRM: $it")
            }
        }
        println("Size RHM: ${count}")

    }
}