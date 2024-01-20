package com.example.newfacultyevaluation.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.User

@Dao
interface FacultyDao {

    @Upsert
    suspend fun upsertFaculty(faculty: Faculty)
    @Query("SELECT * FROM coursefaculty WHERE facultyID=:id")
    fun getCourses(id: String): LiveData<List<CourseFaculty>>

}