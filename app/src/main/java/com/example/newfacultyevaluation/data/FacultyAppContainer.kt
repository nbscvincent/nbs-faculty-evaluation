package com.example.newfacultyevaluation.data

import android.content.Context
import com.example.newfacultyevaluation.data.offlineRepo.OfflineAdminRepository
import com.example.newfacultyevaluation.data.offlineRepo.OfflineCourseRepository
import com.example.newfacultyevaluation.data.offlineRepo.OfflineFacultyRepository
import com.example.newfacultyevaluation.data.offlineRepo.OfflineProgramRepository
import com.example.newfacultyevaluation.data.offlineRepo.OfflineStudentRepository
import com.example.newfacultyevaluation.data.offlineRepo.OfflineUserRepository
import com.example.newfacultyevaluation.data.online.OnlineStudentRepository
import com.example.newfacultyevaluation.data.online.OnlineUserRepository
import com.example.newfacultyevaluation.data.repo.AdminRepo
import com.example.newfacultyevaluation.data.repo.CourseRepo
import com.example.newfacultyevaluation.data.repo.FacultyRepo
import com.example.newfacultyevaluation.data.repo.ProgramRepo
import com.example.newfacultyevaluation.data.repo.StudentRepo
import com.example.newfacultyevaluation.data.repo.UserRepository
import com.example.newfacultyevaluation.database.FacultyEvaluationDatabase

interface FacultyAppContainer {
    val userRepository: UserRepository
    val facultyRepository: FacultyRepo
    val studentRepository: StudentRepo
    val courseRepository: CourseRepo
    val programRepository: ProgramRepo
    val adminRepository: AdminRepo
    val onlineUserRepository: OnlineUserRepository
    val onlineStudentRepository: OnlineStudentRepository
}

class FacultyAppDataContainer(private val context: Context) : FacultyAppContainer {

    override val userRepository: UserRepository by lazy {
        OfflineUserRepository(FacultyEvaluationDatabase.getDatabase(context).userDao())
    }

    override val facultyRepository: FacultyRepo by lazy {
        OfflineFacultyRepository(FacultyEvaluationDatabase.getDatabase(context).facultyDao())
    }

    override val studentRepository: StudentRepo by lazy {
        OfflineStudentRepository(FacultyEvaluationDatabase.getDatabase(context).studentDao())
    }
    override val courseRepository: CourseRepo by lazy {
        OfflineCourseRepository(FacultyEvaluationDatabase.getDatabase(context).courseDao())
    }
    override val programRepository: ProgramRepo by lazy {
        OfflineProgramRepository(FacultyEvaluationDatabase.getDatabase(context).programDao())
    }
    override val adminRepository: AdminRepo by lazy {
        OfflineAdminRepository(FacultyEvaluationDatabase.getDatabase(context).adminDao())
    }

    override val onlineUserRepository: OnlineUserRepository by lazy {
        OnlineUserRepository()
    }

    override val onlineStudentRepository: OnlineStudentRepository by lazy {
        OnlineStudentRepository()
    }




}