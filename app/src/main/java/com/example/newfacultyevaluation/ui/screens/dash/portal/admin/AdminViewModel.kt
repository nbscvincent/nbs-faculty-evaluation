package com.example.newfacultyevaluation.ui.screens.dash.portal.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.User
import com.example.newfacultyevaluation.data.repo.FacultyRepo
import com.example.newfacultyevaluation.data.repo.UserRepository
import kotlinx.coroutines.launch

class AdminViewModel(private val userRepository: UserRepository,private val facultyRepo: FacultyRepo): ViewModel() {

    var fullName = ""
    var password = ""
    var facultyID = ""
    var program = ""
    var course  = ""
    var date = ""

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

    fun getAllUsers(): LiveData<List<User>>{
       return userRepository.getAllUsers()
    }

}