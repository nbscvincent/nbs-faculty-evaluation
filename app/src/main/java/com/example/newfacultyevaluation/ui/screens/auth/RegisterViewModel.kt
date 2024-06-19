package com.example.newfacultyevaluation.ui.screens.auth

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.Student
import com.example.newfacultyevaluation.data.model.User
import com.example.newfacultyevaluation.data.online.OnlineStudentRepository
import com.example.newfacultyevaluation.data.online.OnlineUserRepository
import com.example.newfacultyevaluation.data.repo.FacultyRepo
import com.example.newfacultyevaluation.data.repo.StudentRepo
import com.example.newfacultyevaluation.data.repo.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class RegisterViewModel(private val onlineUserRepository: OnlineUserRepository,
                        private val onlineStudentRepository: OnlineStudentRepository) : ViewModel() {

    var userID by mutableStateOf("")
    var selectedProgram by mutableStateOf("")
    var fullName by mutableStateOf("")
    var pass by mutableStateOf("")
    var role by mutableStateOf("")
    var date by mutableStateOf("")
    var year by mutableStateOf("")

    private var insertSuccessful by mutableStateOf(false)

    fun insertUser(): Boolean{
//            println("UserID : $userID")
//            println("FullName : $fullName")
//            println("Course : $selectedProgram")
//            println("Pass : $pass")
//
//
//        if(userID.isNotBlank() && fullName.isNotBlank() && pass.isNotBlank() && selectedProgram.isNotBlank()&& year.isNotBlank() &&role != "ROLE: "){
//                viewModelScope.launch {
//                    if(role == "Student" && selectedProgram != "PROGRAM: "){
//                        studentRepo.upsertStudent(Student(
//                            studentID = _userID,
//                            fullName = _fullName,
//                            password = _pass,
//                            selectedCourse = _selectedCourse,
//                            role = _role,
//                            dateCreated = _date,
//                            year = _year,
//                        ))
//
//
//                    }
//                    else if(role == "Faculty"){
//                        facultyRepo.upsertFaculty(Faculty(
//                            facultyID = _userID,
//                            fullName = _fullName,
//                            password = _pass,
//                        ))
//
//                    } else if (selectedProgram == "PROGRAM: "){
//                        return@launch
//                    }
////                    userRepository.upsertUser(
////                        User(
////                            userID = _userID,
////                            fullName = _fullName,
////                            password = _pass,
////                            selectedCourse = if(role == "Student") _selectedCourse else "",
////                            role = _role,
////                            dateCreated = _date,
////                            year = _year,
////                        )
////                    )
//                    onlineUserRepository.upsertUser(
//                        User(
//                            userID = _userID,
//                            fullName = _fullName,
//                            password = _pass,
//                            selectedCourse = if(role == "Student") _selectedCourse else "",
//                            role = _role,
//                            dateCreated = _date,
//                            year = _year,
//                        )
//                    )
//
//                }
//                insertSuccessful = true
//            } else {
//                insertSuccessful = false
//            }
//
//        println("Success : $insertSuccessful")
        viewModelScope.launch {
            if(checkUserID().equals(null)){
                insertSuccessful = true
                onlineUserRepository.upsertUser(User(
                    userID = userID,
                    fullName = fullName,
                    password = pass,
                    selectedCourse = if(role == "Student") selectedProgram else "",
                    role = role,
                    dateCreated = date,
                    year = year,
                ))
            }
        }
        return insertSuccessful

    }
    private fun checkUserID(): Flow<User?> {
//        var flow: Flow<User?>? = null
//        try {
//            flow = onlineUserRepository.getUsers(userID,password)
//        } catch (e: Exception) {
//            Timber.i("SAMPLE $e")
//        }
//        return flow
        println("GET USERS: ${onlineUserRepository.getUsers(userID, pass)}")
        return onlineUserRepository.getUsers(userID, pass)


    }
}