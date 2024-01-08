package com.example.newfacultyevaluation.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import com.example.newfacultyevaluation.data.model.Admin

@Dao
interface AdminDao {

    @Upsert
    suspend fun upsertAdmin(admin: Admin)
    @Delete
    suspend fun deleteAdminByID(admin: Admin)

}