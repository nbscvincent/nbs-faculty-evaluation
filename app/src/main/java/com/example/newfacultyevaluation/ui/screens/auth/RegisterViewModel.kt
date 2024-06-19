package com.example.newfacultyevaluation.ui.screens.auth

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.Student
import com.example.newfacultyevaluation.data.model.User
import com.example.newfacultyevaluation.data.online.OnlineStudentRepository
import com.example.newfacultyevaluation.data.online.OnlineUserRepository
import com.example.newfacultyevaluation.data.repo.FacultyRepo
import com.example.newfacultyevaluation.data.repo.StudentRepo
import com.example.newfacultyevaluation.data.repo.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class RegisterViewModel(private val onlineUserRepository: OnlineUserRepository,
                        private val onlineStudentRepository: OnlineStudentRepository) : ViewModel() {

    var userID by mutableStateOf("")
    var selectedProgram by mutableStateOf("")
    var fullName by mutableStateOf("")
    var pass by mutableStateOf("")
    var role by mutableStateOf("")
    var date by mutableStateOf("")
    var year by mutableStateOf("")

    private var insertSuccessful by mutableStateOf(false)

    fun insertUser(): Boolean {
        viewModelScope.launch {
            val existingUser = checkUserID().firstOrNull()
            if (existingUser == null) {
                onlineUserRepository.upsertUser(
                    User(
                        userID = userID,
                        fullName = fullName,
                        password = pass,
                        selectedCourse = if (role == "Student") selectedProgram else "",
                        role = role,
                        dateCreated = date,
                        year = year
                    )
                )
                insertSuccessful = true
            } else {
                // Handle case where userID is already taken
                Timber.d("User ID already taken")
                insertSuccessful = false

            }
        }
        return insertSuccessful
    }

    private fun checkUserID(): Flow<User?> {
        return flow {
            try {
                emit(onlineUserRepository.getUsers(userID, pass).firstOrNull())
            } catch (e: Exception) {
                // Handle exceptions appropriately, or rethrow if needed
                Timber.e(e, "Error fetching user")
                emit(null) // Emit null or handle the error case
            }
        }
    }

}