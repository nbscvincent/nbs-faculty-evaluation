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
import com.example.newfacultyevaluation.data.online.OnlineUserRepository
import com.example.newfacultyevaluation.data.repo.FacultyRepo
import com.example.newfacultyevaluation.data.repo.StudentRepo
import com.example.newfacultyevaluation.data.repo.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class RegisterViewModel(private val facultyRepo: FacultyRepo,private val userRepository: UserRepository, private val studentRepo: StudentRepo,  private val onlineUserRepository: OnlineUserRepository) : ViewModel() {

    var userID = ""
    var selectedProgram = ""
    var fullName = ""
    var pass = ""
    var role = ""
    var date = ""
    var year = ""

    private var insertSuccessful by mutableStateOf(false)

    private val _userID: String
        get() = userID
    private val _selectedCourse: String
        get() = selectedProgram
    private val _fullName: String
        get() = fullName
    private val _pass: String
        get() = pass
    private val _role: String
        get() = role

    private val _date: String
        get() = date

    private val _year: String
        get() = year

    fun insertUser(): Boolean{
            println("UserID : $userID")
            println("FullName : $fullName")
            println("Course : $selectedProgram")
            println("Pass : $pass")
            if(userID.isNotBlank() && fullName.isNotBlank() && pass.isNotBlank() && year.isNotBlank() && role != "ROLE: "){
                viewModelScope.launch {
                    if(role == "Student" && selectedProgram != "PROGRAM: "){
                        studentRepo.upsertStudent(Student(
                            studentID = _userID,
                            fullName = _fullName,
                            password = _pass,
                            selectedCourse = _selectedCourse,
                            role = _role,
                            dateCreated = _date,
                            year = _year,
                        ))
                    }
                    else if(role == "Faculty"){
                        facultyRepo.upsertFaculty(Faculty(
                            facultyID = _userID,
                            fullName = _fullName,
                            password = _pass,

                        ))

                    } else if (selectedProgram == "PROGRAM: "){
                        return@launch
                    }
                    userRepository.upsertUser(
                        User(
                            userID = _userID,
                            fullName = _fullName,
                            password = _pass,
                            selectedCourse = if(role == "Student") _selectedCourse else "",
                            role = _role,
                            dateCreated = _date,
                            year = _year,
                        )
                    )
                    onlineUserRepository.upsertUser(
                        User(
                            userID = _userID,
                            fullName = _fullName,
                            password = _pass,
                            selectedCourse = if(role == "Student") _selectedCourse else "",
                            role = _role,
                            dateCreated = _date,
                            year = _year,
                        )
                    )

                }
                insertSuccessful = true
            }

        println("Success : $insertSuccessful")
        return insertSuccessful
    }
    fun checkUserID(userID: String, password:String): Flow<User?>? {
        var flow : Flow<User?>? = null

        //flow = usersRepository.getUserPasswordStream(userDetails.username, userDetails.password)
        try {
            flow = userRepository.getUsers(userID,password);

        } catch (e: Exception){
            Timber.i("SAMPLE $e")
        }
        return flow
    }
}