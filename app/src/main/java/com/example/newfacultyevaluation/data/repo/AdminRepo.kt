package com.example.newfacultyevaluation.data.repo

import com.example.newfacultyevaluation.data.model.Admin
import com.example.newfacultyevaluation.data.model.CourseFaculty

interface AdminRepo {

    suspend fun upsertAdmin(admin: Admin)
    suspend fun deleteAdmin(admin: Admin)
    suspend fun upsertCourseFaculty(courseFaculty: CourseFaculty)

}