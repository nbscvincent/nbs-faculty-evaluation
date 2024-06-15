package com.example.newfacultyevaluation.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseWithFaculty
import com.example.newfacultyevaluation.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Upsert
    suspend fun upsertCourse(course: Course)

    @Query("""
        SELECT course.*, user.fullName AS facultyName
        FROM course
        JOIN CourseFaculty ON course.courseID = CourseFaculty.courseCode
        JOIN User AS user ON CourseFaculty.facultyID = user.userID
    """)
    fun getCoursesWithFaculties(): Flow<List<CourseWithFaculty>>
}