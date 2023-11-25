package com.nbscollege.facultyevaluation.model.user.repository

import com.nbscollege.facultyevaluation.model.data.LoginReq
import kotlinx.coroutines.flow.Flow

interface UserRepository {

        /**
         * Retrieve all the users from the the given data source.
         */
        fun getAllUsersStream(): Flow<List<LoginReq>>

        /**
         * Retrieve an user from the given data source that matches with the [id].
         */
        fun getUserStream(id: Int): Flow<LoginReq?>

        /**
         * Insert user in the data source
         */
        suspend fun insertUser(user: LoginReq)

        /**
         * Delete user from the data source
         */
        suspend fun deleteUser(user: LoginReq)

        /**
         * Update user in the data source
         */
        suspend fun updateUser(user: LoginReq)
    }
