package com.nbscollege.facultyevaluation.model.data

import android.content.Context
import com.nbscollege.facultyevaluation.model.FacultyAppDatabase
import com.nbscollege.facultyevaluation.model.user.repository.OfflineUserRepository
import com.nbscollege.facultyevaluation.model.user.repository.UserRepository

interface AppContainer {
    val userRepository: UserRepository
}
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [userRepository]
     */
    override val userRepository: UserRepository by lazy {
        OfflineUserRepository(FacultyAppDatabase.getDatabase(context).userDao())
    }
}
