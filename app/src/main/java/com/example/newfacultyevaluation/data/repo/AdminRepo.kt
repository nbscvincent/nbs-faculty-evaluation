package com.example.newfacultyevaluation.data.repo

import com.example.newfacultyevaluation.data.model.Admin
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.CourseWithFaculty
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.Question
import com.example.newfacultyevaluation.data.model.Student
import com.example.newfacultyevaluation.data.model.User
import kotlinx.coroutines.flow.Flow

interface AdminRepo {

    suspend fun upsertAdmin(admin: Admin)
    suspend fun deleteAdmin(admin: Admin)
    suspend fun upsertCourseFaculty(courseFaculty: CourseFaculty)
//    suspend fun upsertCourse(course: Course)

    suspend fun updateUser(user: User)
    suspend fun updateFaculty(faculty: Faculty)
    suspend fun updateStudent(student: Student)

    fun getCoursesWithFaculties(): Flow<List<CourseWithFaculty>>

    suspend fun updateQuestion(question: Question)

}