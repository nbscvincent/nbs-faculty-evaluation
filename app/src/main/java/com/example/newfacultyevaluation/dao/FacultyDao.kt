package com.example.newfacultyevaluation.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface FacultyDao {

    @Upsert
    suspend fun upsertFaculty(faculty: Faculty)
    @Query("SELECT course.courseid, course.courseName, course.year, course.program FROM coursefaculty INNER JOIN course ON coursefaculty.courseCode = course.courseid WHERE facultyID=:id")
    fun getCourses(id: String): Flow<List<Course>>

    @Query("SELECT COUNT(*) FROM formstudentfaculty WHERE formstudentfaculty.facultyID = :id")
    fun getStudentCount(id: String): Flow<Int>

    @Query("SELECT form.overallPoints FROM form INNER JOIN formstudentfaculty ON formstudentfaculty.formID = form.formID WHERE formstudentfaculty.facultyID = :id")
    fun getOverallPoints(id: String): Flow<Int>
}