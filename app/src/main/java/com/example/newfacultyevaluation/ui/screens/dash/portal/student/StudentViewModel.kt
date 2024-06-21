package com.example.newfacultyevaluation.ui.screens.dash.portal.student

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.Question
import com.example.newfacultyevaluation.data.online.OnlineStudentRepository
import com.example.newfacultyevaluation.data.online.OnlineStudentRepository.CourseList
import kotlinx.coroutines.flow.Flow

class StudentViewModel(private val onlineRepository: OnlineStudentRepository) : ViewModel() {

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
        return onlineRepository.getAllCourses()
//        return studentRepo.getCoursesByStudentID(id)
    }

    fun getAllQuestions(): Flow<List<Question>>{
        return onlineRepository.getAllQuestions()
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

    fun markEvaluationAsCompleted(courseID: String, studentID: String, context: Context) {
        CompletedEvaluationsCache.markEvaluationAsCompleted(courseID, studentID)
        EvaluationSharedPreferences.markEvaluationCompleted(context, courseID, studentID)
    }

    fun isEvaluationCompleted(courseID: String, studentID: String, context: Context): Boolean {
        return CompletedEvaluationsCache.isEvaluationCompleted(courseID, studentID) ||
                EvaluationSharedPreferences.isEvaluationCompleted(context, courseID, studentID)
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


object EvaluationSharedPreferences {

    private const val EVAL_PREFS_NAME = "evaluation_prefs"
    private const val EVAL_PREFIX = "eval_"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(EVAL_PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun markEvaluationCompleted(context: Context, courseID: String, studentID: String) {
        val prefs = getPrefs(context)
        prefs.edit().putBoolean("$EVAL_PREFIX$courseID-$studentID", true).apply()
    }

    fun isEvaluationCompleted(context: Context, courseID: String, studentID: String): Boolean {
        val prefs = getPrefs(context)
        return prefs.getBoolean("$EVAL_PREFIX$courseID-$studentID", false)
    }

}