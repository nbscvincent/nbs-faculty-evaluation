package com.example.newfacultyevaluation.ui.screens.dash.portal.admin

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfacultyevaluation.data.model.Admin
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.CourseWithFaculty
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.Student
import com.example.newfacultyevaluation.data.model.User
import com.example.newfacultyevaluation.data.online.OnlineUserRepository
import com.example.newfacultyevaluation.data.repo.AdminRepo
import com.example.newfacultyevaluation.data.repo.FacultyRepo
import com.example.newfacultyevaluation.data.repo.StudentRepo
import com.example.newfacultyevaluation.data.repo.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.serializer

class AdminViewModel(private val onlineUserRepository: OnlineUserRepository): ViewModel() {


    var fullName = ""
    var password = ""
    var facultyID = ""
    var program = ""
    var course  = ""
    var date = ""

    var userID = ""
    var pass = ""
    var selectedProgram = ""
    var role = ""
    var year = ""



    private val _facultyID: String
        get() = facultyID
    private val _program: String
        get() = program
    private val _course: String
        get() = course

    private val _date: String
        get() = date
    private val _fullName: String
        get() = fullName
    private val _password: String
        get() = password

    private val _userID: String
        get() = userID

    private val _pass: String
        get() = pass

    private val _selectedCourse: String
        get() = selectedProgram

    private val _role: String
        get() = role
    private val _year: String
        get() = year

    var insertSuccessful by mutableStateOf(false)

    fun getAllUsers(): Flow<List<User>> {
       return onlineUserRepository.getAllUsers()
    }

//    fun insertCourseFaculty(courseFaculty: CourseFaculty){
//        viewModelScope.launch {
//            adminRepo.upsertCourseFaculty(courseFaculty)
//
//        }
//    }

    @SuppressLint("SuspiciousIndentation")
    private fun checkUserID(): Flow<User?> = flow {

        val user = onlineUserRepository.getUsers(_userID, _password)
            user.collect { value ->
                if(_userID.isNotEmpty()){
                    println("User 1: $user")
                    emit(value)
                }
                else{
                    emit(null)
                }

            }
    }

    suspend fun insertUser(): Boolean {
//        println("UserID : $userID")
//        println("FullName : $fullName")
//        println("Course : $selectedProgram")
//        println("Year : $year")
//        println("Pass : $pass")

        onlineUserRepository.upsertUser(
            User(
            userID = _userID,
            fullName = _fullName,
            password = _pass,
            year = _year,
            selectedCourse = _selectedCourse,
            dateCreated = _date,
            role = _role
        ))
//            if(
//                userID.isNotBlank() &&
//                fullName.isNotBlank() &&
//                pass.isNotBlank() &&
//                role != "ROLE:" ||
//                year.isNotBlank()
//                ){
//                    insertSuccessful = true
//
//                }
//            else {
//                insertSuccessful = false
//            }




//            viewModelScope.launch {
//                if(role == "Student" && selectedProgram != "PROGRAM: "){
//                    studentRepo.upsertStudent(
//                        Student(
//                        studentID = _userID,
//                        fullName = _fullName,
//                        password = _pass,
//                        selectedCourse = _selectedCourse,
//                        role = _role,
//                         year = _year,
//                        dateCreated = _date
//                        )
//                    )
//                }
//                else if(role == "Faculty"){
//                    facultyRepo.upsertFaculty(Faculty(
//                        facultyID = _userID,
//                        fullName = _fullName,
//                        password = _pass,
//                        )
//                    )
//                } else if (selectedProgram == "PROGRAM: "){
//                    return@launch
//                }
//                userRepository.upsertUser(
//                    User(
//                        userID = _userID,
//                        fullName = _fullName,
//                        password = _pass,
//                        selectedCourse = if(role == "Student") _selectedCourse else "",
//                        role = _role,
//                        year = _year,
//                        dateCreated = _date
//                    )
//                )
//            }
//
//        println("Success : $insertSuccessful")
        return insertSuccessful
    }

    fun checkRole(role: String): String{
        if(role == "Student"){

        }
        return role
    }


//    fun upsertCourse(course: Course){
//        viewModelScope.launch {
//            adminRepo.upsertCourse(course)
//        }
//    }
//
//    fun updateUser(user: User){
//        viewModelScope.launch {
//            adminRepo.updateUser(user)
//        }
//    }
//
//    fun updateFaculty(faculty: Faculty){
//        viewModelScope.launch {
//            adminRepo.updateFaculty(faculty)
//        }
//    }
//    fun updateStudent(student: Student){
//        viewModelScope.launch {
//            adminRepo.updateStudent(student)
//        }
//    }
//
//    fun deleteUser(user: User) {
//        viewModelScope.launch {
//            // Delete the user from the repository
//
//            when (user.role) {
//
//                "Faculty" -> facultyRepo.upsertFaculty(Faculty(user.userID, "", "",))
//                "Student" -> studentRepo.upsertStudent(Student(user.userID, "", "", "", "", "", ""))
//
//            }
//
//            // Now delete the user from the main user repository
//            userRepository.deleteUser(user)
//        }
//    }



    private val _courseSessionStates = MutableStateFlow<Map<Long, Boolean>>(emptyMap())
    val courseSessionStates: StateFlow<Map<Long, Boolean>> = _courseSessionStates

    fun setCourseSessionState(courseId: Long, isActive: Boolean) {
        _courseSessionStates.value = _courseSessionStates.value.toMutableMap().apply {
            put(courseId, isActive)
        }
    }

    fun finishCourseSession(courseId: Long) {
        _courseSessionStates.value = _courseSessionStates.value.toMutableMap().apply {
            put(courseId, false)
        }
    }

//    fun getCoursesWithFaculties(): Flow<List<CourseWithFaculty>> {
//        return adminRepo.getCoursesWithFaculties()
//    }


}