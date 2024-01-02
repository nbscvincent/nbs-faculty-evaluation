package com.nbscollege.facultyevaluation.user.repository

import com.nbscollege.facultyevaluation.user.dao.UserDao
import com.nbscollege.facultyevaluation.user.model.User
import kotlinx.coroutines.flow.Flow

class OfflineUserRepository(private val userDao: UserDao) : UserRepository {
    override fun getAllUsersStream(): Flow<List<User>> = userDao.getAllUsers()

    override fun getUserStream(id: Int): Flow<User?> = userDao.getUser(id)

    override suspend fun insertUser(user: User) = userDao.insert(user)
    override suspend fun deleteUser(user: User) = userDao.delete(user)

    override suspend fun updateUser(user: User) = userDao.update(user)
}