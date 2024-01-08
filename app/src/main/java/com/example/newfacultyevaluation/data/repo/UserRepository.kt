package com.example.newfacultyevaluation.data.repo

import androidx.lifecycle.LiveData
import com.example.newfacultyevaluation.data.model.User

interface UserRepository {

    suspend fun upsertUser(user: User)
    suspend fun deleteUser(user: User)
    fun getUsers(id: String): LiveData<User>
    fun getAllUsers(): LiveData<List<User>>

}