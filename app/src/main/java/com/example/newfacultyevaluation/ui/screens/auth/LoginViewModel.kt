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

    fun getUser(userID: String, password: String): Flow<User?> {

        var flow : Flow<User?>? = null

        //flow = usersRepository.getUserPasswordStream(userDetails.username, userDetails.password)
        try {
            flow = userRepository.getUsers(userID,password); onlineUserRepository.getUsers(userID,password)

        } catch (e: Exception){
            Timber.i("SAMPLE $e")
        }
        return flow!!

    }




}

