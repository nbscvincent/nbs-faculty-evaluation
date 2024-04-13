package com.example.newfacultyevaluation.data.online

import com.example.newfacultyevaluation.data.model.Admin
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.Student
import com.example.newfacultyevaluation.data.model.User
import com.example.newfacultyevaluation.data.repo.AdminRepo
import com.example.newfacultyevaluation.data.repo.UserRepository
import com.example.newfacultyevaluation.network.HttpRoutes
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType


class OnlineAdminRepository(private val ktorClient: HttpClient) : AdminRepo {
    override suspend fun upsertAdmin(admin: Admin) {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "save_user")
                append("userID", admin.adminID)
                append("password", admin.password)
            }))
        }
    }

    override suspend fun deleteAdmin(admin: Admin) {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "save_user")
                append("userID", admin.adminID)
                append("userID", admin.password)
            }))
        }
    }

    override suspend fun upsertCourseFaculty(courseFaculty: CourseFaculty) {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "save_user")
                append("userID", courseFaculty.facultyID)
                append("userID", courseFaculty.courseCode)
            }))
        }
    }

    override suspend fun upsertCourse(course: Course) {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "save_user")
                append("userID", course.courseID)
                append("password", course.courseName)
            }))
        }
    }

    override suspend fun updateUser(user: User) {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "save_user")
                append("userID", user.userID)
                append("password", user.password)
                append("fullName", user.fullName.toString())
                append("role", user.role)
                append("selectedCourse", user.selectedCourse.toString())
                append("dateCreated", user.dateCreated.toString())
            }))
        }
    }

    override suspend fun updateFaculty(faculty: Faculty) {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "save_user")
                append("userID", faculty.facultyID)
                append("password", faculty.password)
                append("password", faculty.fullName)
            }))
        }
    }

    override suspend fun updateStudent(student: Student) {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "save_user")
                append("userID", student.studentID)
                append("password", student.password)
                append("password", student.fullName)
                append("password", student.role)
                append("password", student.dateCreated)
            }))
        }
    }

}
