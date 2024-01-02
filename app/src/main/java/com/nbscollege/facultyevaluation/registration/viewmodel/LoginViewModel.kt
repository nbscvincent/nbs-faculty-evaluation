package com.nbscollege.facultyevaluation.registration.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.nbscollege.facultyevaluation.user.model.User
import com.nbscollege.facultyevaluation.user.repository.UserRepository
import kotlinx.coroutines.flow.singleOrNull

class LoginViewModel(private val usersRepository: UserRepository): ViewModel(){

    suspend fun validateLogin(){
        if(usersRepository.getUserStream(0).singleOrNull() == User(0, "", "")){

        }
    }

}