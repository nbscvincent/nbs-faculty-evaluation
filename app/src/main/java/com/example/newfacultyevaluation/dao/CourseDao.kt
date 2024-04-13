package com.example.newfacultyevaluation.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.User
@Dao
interface CourseDao {
    @Upsert
    suspend fun upsertCourse(course: Course)
}