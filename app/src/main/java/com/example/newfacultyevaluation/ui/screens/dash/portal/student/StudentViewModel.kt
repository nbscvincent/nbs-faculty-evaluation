package com.example.newfacultyevaluation.ui.screens.dash.portal.student

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.FormEvaluation
import com.example.newfacultyevaluation.data.model.Question
import com.example.newfacultyevaluation.data.model.User
import com.example.newfacultyevaluation.data.online.OnlineStudentRepository
import com.example.newfacultyevaluation.data.online.OnlineStudentRepository.CourseList
import com.example.newfacultyevaluation.data.online.OnlineUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class StudentViewModel(private val onlineStudentRepository: OnlineStudentRepository, private val onlineUserRepository: OnlineUserRepository) : ViewModel() {

    private val _totalPoints = mutableIntStateOf(0)
    val totalPoints: State<Int> get() = _totalPoints

    private val _answeredQuestions = mutableIntStateOf(0)
    val answeredQuestions: State<Int> get() = _answeredQuestions

    private val answeredQuestionsSet = mutableSetOf<Int>()

    private val _formID = mutableIntStateOf(0)
    val formID: State<Int> get() = _formID
    fun updateFormID(){
       _formID.intValue = (Math.random() * 100000).toInt()
    }

    fun updateTotalPoints(delta: Int) {
        _totalPoints.intValue += delta
    }

    fun markQuestionAnswered(questionId: Int) {
        if (!answeredQuestionsSet.contains(questionId)) {
            _answeredQuestions.intValue++
            answeredQuestionsSet.add(questionId)
        }
    }

    fun resetAnsweredQuestions() {
        _answeredQuestions.intValue = 0
        _totalPoints.intValue = 0
        answeredQuestionsSet.clear()
    }



//    fun upsertCourseStudent(courseStudent: CourseStudent){
//        viewModelScope.launch {
//            studentRepo.upsertCourseStudent(courseStudent)
//        }
//    }
//
//    fun upsertCourse(course: Course){
//        viewModelScope.launch {
//           studentRepo.upsertCourse(course)
//        }
//    }
//    fun upsertFormStudentFaculty(formStudentFaculty: FormStudentFaculty){
//        viewModelScope.launch {
//             studentRepo.upsertFormStudentFaculty(formStudentFaculty)
//        }
//    }
    fun getAllCourses(): Flow<List<Course>?>{
        return onlineStudentRepository.getAllCourses()
//        return studentRepo.getCoursesByStudentID(id)
    }

    fun getAllQuestions(): Flow<List<Question>>{
        return onlineStudentRepository.getAllQuestions()
    }



//
//    fun getAllCourses(): Flow<List<Course>>{
//        return studentRepo.getAllCourses()
//    }
//
//    fun getStudentFaculty(id: String, selectedCourse: String): Flow<Faculty>{
//        return studentRepo.getStudentFaculty(id, selectedCourse)
//    }
//
//    fun deleteCourse(courseStudent: CourseStudent){
//        viewModelScope.launch {
//            studentRepo.deleteCourse(courseStudent)
//        }
//    }
//
//    fun upsertForm(form: Form){
//        viewModelScope.launch {
//            studentRepo.upsertForm(form)
//        }
//    }

    fun markEvaluationAsCompleted(courseID: String, studentID: String) {
        CompletedEvaluationsCache.markEvaluationAsCompleted(courseID, studentID)
    }

    // Function to check if an evaluation is completed
    fun isEvaluationCompleted(courseID: String, studentID: String): Boolean {
        return CompletedEvaluationsCache.isEvaluationCompleted(courseID, studentID)
    }

    fun insertFormEvaluation(formEvaluation: FormEvaluation){
        viewModelScope.launch {
            onlineStudentRepository.upsertForm(formEvaluation = formEvaluation)
        }
    }
    fun getFacultyID() : Flow<List<User>>{
        return onlineUserRepository.getAllUsers()
    }
}
object CompletedEvaluationsCache {
    private val completedEvaluations = mutableSetOf<Pair<String, String>>()

    fun markEvaluationAsCompleted(courseID: String, studentID: String) {
        completedEvaluations.add(courseID to studentID)
    }

    fun isEvaluationCompleted(courseID: String, studentID: String): Boolean {
        return completedEvaluations.contains(courseID to studentID)
    }


}