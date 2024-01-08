package com.example.newfacultyevaluation.data.repo

import com.example.newfacultyevaluation.data.model.Admin

interface AdminRepo {

    suspend fun upsertAdmin(admin: Admin)
    suspend fun deleteAdmin(admin: Admin)

}