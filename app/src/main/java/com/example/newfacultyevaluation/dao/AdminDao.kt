package com.example.newfacultyevaluation.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.newfacultyevaluation.data.model.Admin
import com.example.newfacultyevaluation.data.model.CourseFaculty

@Dao
interface AdminDao {

    @Upsert
    suspend fun upsertAdmin(admin: Admin)
    @Delete
    suspend fun deleteAdminByID(admin: Admin)

    @Upsert
    suspend fun upsertCourseFaculty(courseFaculty: CourseFaculty)
}