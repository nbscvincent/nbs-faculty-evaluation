package com.example.newfacultyevaluation.data.offlineRepo

import androidx.lifecycle.LiveData
import com.example.newfacultyevaluation.dao.StudentDao
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.CourseStudent
import com.example.newfacultyevaluation.data.model.Student
import com.example.newfacultyevaluation.data.model.StudentFaculty
import com.example.newfacultyevaluation.data.repo.StudentRepo

class OfflineStudentRepository(private val studentDao: StudentDao): StudentRepo {
    override suspend fun upsertStudent(student: Student) {
        studentDao.upsertStudent(student)
    }

//    override suspend fun upsertFormStudentFaculty(
//        formID: Int,
//        studentID: String,
//        facultyID: String
//    ) {
//        TODO("Not yet implemented")
//    }
//
    override suspend fun upsertCourseStudent(courseStudent: CourseStudent) {
        studentDao.upsertCourseStudent(courseStudent)
    }
//
//    override fun getFacultyCourses(): LiveData<List<CourseFaculty>> {
//        TODO("Not yet implemented")
//    }
//
    override fun getCoursesByStudentID(id: String): LiveData<List<Course>> {
        return studentDao.getCoursesByStudentID(id)
    }

    override suspend fun upsertCourse(course: Course) {
        studentDao.upsertCourse(course)
    }

    override fun getAllCourses(): LiveData<List<Course>> {
        return studentDao.getAllCourses()
    }

    override fun getStudentFaculty(): LiveData<List<StudentFaculty>> {
        return studentDao.getStudentFaculty()
    }

//    override fun getStudentFaculty(): LiveData<List<StudentFaculty>> {
//        TODO("Not yet implemented")
//    }
}