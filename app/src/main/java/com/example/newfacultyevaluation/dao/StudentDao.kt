package com.example.newfacultyevaluation.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.CourseStudent
import com.example.newfacultyevaluation.data.model.Student
import com.example.newfacultyevaluation.data.model.StudentFaculty
import com.example.newfacultyevaluation.data.model.User

@Dao
interface StudentDao {

    @Upsert
    suspend fun upsertStudent(student: Student)

    @Upsert
    suspend fun upsertCourse(course: Course)
    @Query("SELECT * FROM course")
    fun getAllCourses(): LiveData<List<Course>>

//    @Upsert
//    suspend fun upsertFormStudentFaculty(formID: Int, studentID: String, facultyID: String)
//    @Query("SELECT * FROM ")
//    fun getFacultyCourses(): LiveData<List<CourseFaculty>>
//    fun getStudentFaculty(): LiveData<List<StudentFaculty>>

    @Upsert
    suspend fun upsertCourseStudent(courseStudent: CourseStudent)
    @Query("SELECT course.courseid AS courseid, course.courseName AS courseName FROM coursestudent INNER JOIN course ON coursestudent.courseID = course.courseid AND coursestudent.studentID = :id")
    fun getCoursesByStudentID(id: String): LiveData<List<Course>>

    @Query("SELECT coursestudent.studentID, coursefaculty.facultyID  FROM coursestudent INNER JOIN coursefaculty ON coursestudent.courseID = coursefaculty.courseCode")
    fun getStudentFaculty(): LiveData<List<StudentFaculty>>
}