package com.example.newfacultyevaluation.dao

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.newfacultyevaluation.data.User

interface FacultyDao {

    @Query("SELECT * FROM user WHERE role = 'Faculty'")
    fun getUsers(id: String): LiveData<List<User>>

}