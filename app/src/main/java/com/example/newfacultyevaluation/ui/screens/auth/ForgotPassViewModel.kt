package com.example.newfacultyevaluation.ui.screens.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfacultyevaluation.data.model.User
import com.example.newfacultyevaluation.data.repo.UserRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ForgotPassViewModel(private val userRepository: UserRepository) : ViewModel(){
    private val _timer = MutableStateFlow(300L)
    val timer = _timer.asStateFlow()

    private var timerJob: Job? = null

    fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _timer.value--
            }
        }
    }

    fun getUser(id: String): LiveData<User>{
        return userRepository.getUsers(id)
    }
    fun upsertUser(user: User){
        viewModelScope.launch {
            userRepository.upsertUser(user)
        }
    }

}