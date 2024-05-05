package com.example.newfacultyevaluation.ui.screens.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfacultyevaluation.data.model.User
import com.example.newfacultyevaluation.data.repo.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel(){

    var role by mutableStateOf("")
    var status by mutableStateOf(false)
    var userID by mutableStateOf("")
    var fullName by mutableStateOf("")
    var password by mutableStateOf("")
    var year by mutableStateOf("")

    fun getUser(userID: String): Flow<User> {

        return userRepository.getUsers(userID)

    }




}

