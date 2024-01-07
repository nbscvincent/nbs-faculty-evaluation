package com.example.newfacultyevaluation.ui.screens.auth

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.newfacultyevaluation.data.User
import com.example.newfacultyevaluation.data.UserRepository
import com.example.newfacultyevaluation.database.FacultyEvaluationDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val userRepository: UserRepository) : ViewModel(){

    var role by mutableStateOf("")
    var status by mutableStateOf(false)
    var userID by mutableStateOf("")
    var password by mutableStateOf("")

    fun getUser(userID: String): LiveData<User>{
        return userRepository.getUsers(userID)
    }



}

