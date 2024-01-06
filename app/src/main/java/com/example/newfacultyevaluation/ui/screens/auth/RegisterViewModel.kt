package com.example.newfacultyevaluation.ui.screens.auth

import android.os.Handler
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfacultyevaluation.data.User
import com.example.newfacultyevaluation.data.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {

    var userID = ""
    var selectedCourse = ""
    var fullName = ""
    var pass = ""
    var role = ""
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

    fun insertUser(): Boolean{
            println("UserID : $userID")
            println("FullName : $fullName")
            println("Course : $selectedCourse")
            println("Pass : $pass")
            if(userID.isNotBlank() && fullName.isNotBlank() &&
                selectedCourse != "COURSE: " && pass.isNotBlank()){
                viewModelScope.launch {
                    userRepository.upsertUser(User(userID = _userID, fullName = _fullName, password = _pass, selectedCourse = _selectedCourse, status = true, role = _role))
                }
                insertSuccessful = true
            }

        println("Sucess : $insertSuccessful")
        return insertSuccessful
    }
}