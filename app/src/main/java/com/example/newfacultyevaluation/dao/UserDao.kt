package com.example.newfacultyevaluation.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.newfacultyevaluation.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user WHERE userid =:id")
    fun getUsers(id: String): Flow<User?>

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<User>>


}