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
//        return flow {
//            try {
//                // Try to fetch user from the online repository
//                val onlineUser = onlineUserRepository.getUsers(userID, password).firstOrNull()
//                val offlineUser = userRepository.getUsers(userID, password).firstOrNull()
//                if (onlineUser != null && offlineUser == null) {
//                    // User found online, emit and return
//                    emit(onlineUser)
//                    return@flow
//                }
//            } catch (e: Exception) {
//                Timber.e("Error fetching user from online repository: $e")
//            }
//
//            try {
//                // If online fetch fails, try to fetch user from the offline repository
//                val offlineUser = userRepository.getUsers(userID, password).firstOrNull()
//                if (offlineUser != null) {
//                    // User found offline, emit and return
//                    emit(offlineUser)
//                    return@flow
//                }
//            } catch (e: Exception) {
//                Timber.e("Error fetching user from offline repository: $e")
//            }
//
//            // If user is not found online or offline, emit null
//            emit(null)
//        }



        return onlineUserRepository.getUsers(userID,password)
    }






}

