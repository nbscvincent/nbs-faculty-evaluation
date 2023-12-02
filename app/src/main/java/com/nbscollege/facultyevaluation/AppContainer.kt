package com.nbscollege.facultyevaluation

import android.content.Context
import com.nbscollege.facultyevaluation.user.repository.OfflineUserRepository
import com.nbscollege.facultyevaluation.user.repository.UserRepository

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
