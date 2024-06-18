package com.example.newfacultyevaluation.data.repo

import androidx.lifecycle.LiveData
import com.example.newfacultyevaluation.data.model.Question
import com.example.newfacultyevaluation.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun upsertUser(user: User)
    suspend fun deleteUser(user: User)
    fun getUsers(id: String, password: String): Flow<User?>
    fun getAllUsers(): Flow<List<User>>
    fun getAllQuestions(): Flow<List<Question>>

}