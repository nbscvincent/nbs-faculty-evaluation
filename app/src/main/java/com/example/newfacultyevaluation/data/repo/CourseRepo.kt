package com.example.newfacultyevaluation.data.repo

import com.example.newfacultyevaluation.data.model.Course

interface CourseRepo {

    suspend fun upsertCourse(course: Course)

}