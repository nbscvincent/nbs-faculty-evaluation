package com.nbscollege.facultyevaluation.model.user.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nbscollege.facultyevaluation.model.data.LoginReq
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * from user ORDER BY id ASC")
    fun getAllUsers(): Flow<List<LoginReq>>
    @Query("SELECT * from user WHERE id = :id")
    fun getUser(id: Int): Flow<LoginReq>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: LoginReq)
    @Update
    suspend fun update(user: LoginReq)
    @Delete
    suspend fun delete(user: LoginReq)
}
