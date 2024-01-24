package com.example.newfacultyevaluation.ui.screens.dash.portal.student

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.CourseStudent
import com.example.newfacultyevaluation.data.model.FormStudentFaculty
import com.example.newfacultyevaluation.data.model.StudentFaculty
import com.example.newfacultyevaluation.data.repo.StudentRepo
import kotlinx.coroutines.launch

class StudentViewModel(private val studentRepo: StudentRepo) : ViewModel() {

    private val _totalPoints = mutableIntStateOf(0)
    val totalPoints: State<Int> get() = _totalPoints

    private val _answeredQuestions = mutableIntStateOf(0)
    val answeredQuestions: State<Int> get() = _answeredQuestions

    private val answeredQuestionsSet = mutableSetOf<Int>()

    fun updateTotalPoints(delta: Int) {
        _totalPoints.value += delta
    }

    fun markQuestionAnswered(questionId: Int) {
        if (!answeredQuestionsSet.contains(questionId)) {
            _answeredQuestions.value++
            answeredQuestionsSet.add(questionId)
        }
    }

    fun resetAnsweredQuestions() {
        _answeredQuestions.value = 0
        _totalPoints.value = 0
        answeredQuestionsSet.clear()
    }

    fun removeAnsweredQuestion(id: Int){
        answeredQuestionsSet.remove(id)
    }
    fun upsertCourseStudent(courseStudent: CourseStudent){
        viewModelScope.launch {
            studentRepo.upsertCourseStudent(courseStudent)
        }
    }

    fun upsertCourse(course: Course){
        viewModelScope.launch {
           studentRepo.upsertCourse(course)
        }
    }
    fun upsertFormStudentFaculty(formStudentFaculty: FormStudentFaculty){
        viewModelScope.launch {
            // studentRepo.upsertFormStudentFaculty(formStudentFaculty)
        }
    }
    fun getCoursesByStudentID(id: String): LiveData<List<Course>>{
        return studentRepo.getCoursesByStudentID(id)
    }

    fun getAllCourses(): LiveData<List<Course>>{
        return studentRepo.getAllCourses()
    }

    fun getStudentFaculty(): LiveData<List<StudentFaculty>>{
        return studentRepo.getStudentFaculty()
    }

}