package com.example.newfacultyevaluation.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun upsertUser(user: User)
    suspend fun deleteUser(user: User)
    fun getUsers(id: String): LiveData<User>
    fun getAllUsers(): LiveData<List<User>>

}