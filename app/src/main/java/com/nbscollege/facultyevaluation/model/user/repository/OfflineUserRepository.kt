package com.nbscollege.facultyevaluation.model.user.repository

import com.nbscollege.facultyevaluation.model.data.LoginReq
import com.nbscollege.facultyevaluation.model.user.dao.UserDao
import kotlinx.coroutines.flow.Flow

class OfflineUserRepository(private val userDao: UserDao) : UserRepository {
    override fun getAllUsersStream(): Flow<List<LoginReq>> = userDao.getAllUsers()

    override fun getUserStream(id: Int): Flow<LoginReq?> = userDao.getUser(id)

    override suspend fun insertUser(user: LoginReq) = userDao.insert(user)
    override suspend fun deleteUser(user: LoginReq) = userDao.delete(user)

    override suspend fun updateUser(user: LoginReq) = userDao.update(user)
}