package com.example.newfacultyevaluation.data.repo

import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.Student

interface StudentRepo {

    suspend fun upsertStudent(student: Student)

}