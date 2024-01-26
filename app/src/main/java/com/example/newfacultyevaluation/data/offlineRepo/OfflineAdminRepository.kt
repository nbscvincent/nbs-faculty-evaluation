package com.example.newfacultyevaluation.data.offlineRepo

import com.example.newfacultyevaluation.dao.AdminDao
import com.example.newfacultyevaluation.data.model.Admin
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.repo.AdminRepo

class OfflineAdminRepository(private val adminDao: AdminDao): AdminRepo{
    override suspend fun upsertAdmin(admin: Admin) {
        adminDao.upsertAdmin(admin)
    }

    override suspend fun deleteAdmin(admin: Admin) {
        adminDao.deleteAdminByID(admin)
    }

    override suspend fun upsertCourseFaculty(courseFaculty: CourseFaculty) {
        adminDao.upsertCourseFaculty(courseFaculty)
    }

    override suspend fun upsertCourse(course: Course) {
        adminDao.upsertCourse(course)
    }


}