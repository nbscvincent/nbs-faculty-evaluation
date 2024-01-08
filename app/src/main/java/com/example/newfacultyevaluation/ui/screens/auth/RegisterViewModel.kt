package com.example.newfacultyevaluation.ui.screens.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfacultyevaluation.data.model.Student
import com.example.newfacultyevaluation.data.model.User
import com.example.newfacultyevaluation.data.repo.StudentRepo
import com.example.newfacultyevaluation.data.repo.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepository: UserRepository, private val studentRepo: StudentRepo) : ViewModel() {

    var userID = ""
    var selectedCourse = ""
    var fullName = ""
    var pass = ""
    var role = ""
    var date = ""
    private var insertSuccessful by mutableStateOf(false)

    private val _userID: String
        get() = userID
    private val _selectedCourse: String
        get() = selectedCourse
    private val _fullName: String
        get() = fullName
    private val _pass: String
        get() = pass
    private val _role: String
        get() = role

    private val _date: String
        get() = date

    fun insertUser(): Boolean{
            println("UserID : $userID")
            println("FullName : $fullName")
            println("Course : $selectedCourse")
            println("Pass : $pass")
            if(userID.isNotBlank() && fullName.isNotBlank() &&
                selectedCourse != "COURSE: " && pass.isNotBlank()){
                viewModelScope.launch {
                    studentRepo.upsertStudent(Student(
                        studentID = _userID,
                        fullName = _fullName,
                        password = _pass,
                        selectedCourse = _selectedCourse,
                        role = _role,
                        dateCreated = _date
                    ))
                    userRepository.upsertUser(
                        User(
                            userID = _userID,
                            fullName = _fullName,
                            password = _pass,
                            selectedCourse = _selectedCourse,
                            role = _role,
                            dateCreated = _date
                        )
                    )
                }
                insertSuccessful = true
            }

        println("Sucess : $insertSuccessful")
        return insertSuccessful
    }
    fun checkUserID(userID: String): LiveData<User>{
        return userRepository.getUsers(userID)
    }
}