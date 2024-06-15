package com.example.newfacultyevaluation.data.online

import com.example.newfacultyevaluation.data.model.CompletedEvaluation
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseStudent
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.Form
import com.example.newfacultyevaluation.data.model.FormStudentFaculty
import com.example.newfacultyevaluation.data.model.Student
import com.example.newfacultyevaluation.data.repo.AdminRepo
import com.example.newfacultyevaluation.data.repo.StudentRepo
import com.example.newfacultyevaluation.network.HttpRoutes
import com.example.newfacultyevaluation.network.KtorClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OnlineStudentRepository(private val ktorClient: HttpClient = KtorClient() ) : StudentRepo {
    override suspend fun upsertStudent(student: Student) {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "save_user")
                append("username", student.studentID)
                append("fullName", student.fullName.toString())
                append("password", student.password.toString())
                append("year", student.year.toString())
                append("course", student.selectedCourse.toString())
                append("role", student.role)
                append("dateCreated", student.dateCreated.toString())
            }))
        }
    }

    override suspend fun upsertCourseStudent(courseStudent: CourseStudent) {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "save_courseStudent")
                append("studentID", courseStudent.studentID)
                append("courseID", courseStudent.courseID)
            }))
        }
    }

    override fun getCoursesByStudentID(id: String): Flow<List<Course>> {
        return flow {
            val cl = coroutineScope {
                ktorClient.request(
                    HttpRoutes.login
                ) {
                    method = HttpMethod.Post
                    url(HttpRoutes.login)
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    setBody(MultiPartFormDataContent(formData {
                        append("type", "check_course")
                        append("courseID", id)
                    }))
                }
            }

            emit(cl.body())
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
                append("courseID", course.courseID)
                append("courseName", course.courseName)
            }))
        }
    }

    override fun getAllCourses(): Flow<List<Course>> {
        return getAllCourses()
    }

    override fun getStudentFaculty(id: String, selectedCourse: String): Flow<Faculty> {
        return flow {
            val cl = coroutineScope {
                ktorClient.request(
                    HttpRoutes.login
                ) {
                    method = HttpMethod.Post
                    url(HttpRoutes.login)
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    setBody(MultiPartFormDataContent(formData {
                        append("type", "saveFaculty")
                        append("facultyID", id)
                    }))
                }
            }

            emit(cl.body())
        }
    }

    override suspend fun deleteCourse(courseStudent: CourseStudent) {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "save_courseStudent")
                append("studentID", courseStudent.studentID)
                append("courseID", courseStudent.courseID)
            }))
        }
    }

    override suspend fun upsertFormStudentFaculty(formStudentFaculty: FormStudentFaculty) {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "save_formFaculty")
                append("formID", formStudentFaculty.formID)
                append("studentID", formStudentFaculty.studentID)
                append("facultyID", formStudentFaculty.facultyID)

            }))
        }
    }

    override suspend fun upsertForm(form: Form) {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "save_form")
                append("formID", form.formID)
                append("overallPoints", form.overallPoints)
                append("feedback", form.feedback.toString())

            }))
        }
    }

    override suspend fun insertCompletedEvaluation(completedEvaluation: CompletedEvaluation) {
        TODO("Not yet implemented")
    }

    override suspend fun getCompletedEvaluations(): List<CompletedEvaluation> {
        TODO("Not yet implemented")
    }
}