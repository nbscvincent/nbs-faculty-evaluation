package com.example.newfacultyevaluation.data

import androidx.lifecycle.LiveData
import com.example.newfacultyevaluation.dao.UserDao
import kotlinx.coroutines.flow.Flow

class OfflineUserRepository(private val userDao: UserDao) : UserRepository {
    override suspend fun upsertUser(user: User) = userDao.upsertUser(user)

    override suspend fun deleteUser(user: User) = userDao.deleteUser(user)

    override fun getUsers(id: String): LiveData<User> {
        return userDao.getUsers(id)
    }

    override fun getAllUsers(): LiveData<List<User>> {
        return userDao.getAllUsers()
    }

}