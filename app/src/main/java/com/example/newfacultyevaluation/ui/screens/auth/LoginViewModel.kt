package com.example.newfacultyevaluation.ui.screens.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfacultyevaluation.data.model.User
import com.example.newfacultyevaluation.data.online.OnlineUserRepository

import com.example.newfacultyevaluation.data.repo.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginViewModel(private val userRepository: UserRepository, private val onlineUserRepository: OnlineUserRepository) : ViewModel(){

    var role by mutableStateOf("")
    var status by mutableStateOf(false)
    var userID by mutableStateOf("")
    var fullName by mutableStateOf("")
    var selectedCourse by mutableStateOf("")
    var password by mutableStateOf("")
    var year by mutableStateOf("")

    fun getUser(userID: String, password: String): Flow<User> {
        return onlineUserRepository.getUsers(userID,password)
    }

    fun setUserDetails(user: User) {
        role = user.role
        this.userID = user.userID
        fullName = user.fullName.toString()
        selectedCourse = user.selectedCourse.toString()
        password = user.password
        year = user.year.toString()
    }
}

