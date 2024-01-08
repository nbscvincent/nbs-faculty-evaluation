package com.example.newfacultyevaluation.data.repo

import com.example.newfacultyevaluation.data.model.Faculty

interface FacultyRepo {
    suspend fun upsertFaculty(faculty: Faculty)
}