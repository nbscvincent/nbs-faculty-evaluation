//package com.example.newfacultyevaluation.data.offlineRepo
//
//import com.example.newfacultyevaluation.dao.CourseDao
//import com.example.newfacultyevaluation.data.model.Course
//import com.example.newfacultyevaluation.data.repo.CourseRepo
//
//class OfflineCourseRepository(private val courseDao: CourseDao): CourseRepo {
//    override suspend fun upsertCourse(course: Course) {
//        courseDao.upsertCourse(course)
//    }
//
//
//}