package com.example.newfacultyevaluation.data.offlineRepo

import androidx.lifecycle.LiveData
import com.example.newfacultyevaluation.dao.UserDao
import com.example.newfacultyevaluation.data.model.User
import com.example.newfacultyevaluation.data.repo.UserRepository
import kotlinx.coroutines.flow.Flow

class OfflineUserRepository(private val userDao: UserDao) : UserRepository {
    override suspend fun upsertUser(user: User) = userDao.upsertUser(user)

    override suspend fun deleteUser(user: User) = userDao.deleteUser(user)

    override fun getUsers(id: String): Flow<User> {
        return userDao.getUsers(id)
    }

    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers()
    }

}