package com.example.newfacultyevaluation.data

import android.content.Context
import com.example.newfacultyevaluation.database.FacultyEvaluationDatabase

interface FacultyAppContainer {
    val userRepository: UserRepository
}

class FacultyAppDataContainer(private val context: Context) : FacultyAppContainer {

    override val userRepository: UserRepository by lazy {
        OfflineUserRepository(FacultyEvaluationDatabase.getDatabase(context).userDao())
    }
}