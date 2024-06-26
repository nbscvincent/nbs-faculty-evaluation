package com.example.newfacultyevaluation.data.online

import android.system.Os.accept

import com.example.newfacultyevaluation.dao.UserDao
import com.example.newfacultyevaluation.data.model.Question
import com.example.newfacultyevaluation.data.model.User
import com.example.newfacultyevaluation.data.repo.UserRepository
import com.example.newfacultyevaluation.network.HttpRoutes

import com.example.newfacultyevaluation.network.HttpRoutes.login
import com.example.newfacultyevaluation.network.KtorClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.call.receive
import io.ktor.client.request.accept
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.server.util.url
import io.ktor.util.InternalAPI
import io.ktor.util.reflect.TypeInfo
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable


class OnlineUserRepository(private val ktorClient: HttpClient = KtorClient() ) : UserRepository {
    override suspend fun upsertUser(user: User) {
        val cl = ktorClient.request(
            HttpRoutes.login
        ) {
            method = HttpMethod.Post
            url(HttpRoutes.login)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(MultiPartFormDataContent(formData {
                append("type", "save_user")
                append("username", user.userID)
                append("fullName", user.fullName.toString())
                append("password", user.password)
                append("year", user.year.toString())
                append("course", user.selectedCourse.toString())
                append("role", user.role)
                append("dateCreated", user.dateCreated.toString())
            }))
        }
        println("Save User: $cl")
    }
    @OptIn(InternalAPI::class)
    override fun getUsers(id: String, password: String): Flow<User?> = flow {
        try {
            val response: HttpResponse = ktorClient.post(HttpRoutes.login) {
                contentType(ContentType.Application.Json)
                body = MultiPartFormDataContent(formData {
                    append("type", "login")
                    append("username", id)
                    append("password", password)
                })
            }
            println("Res: ${response.bodyAsText()}")
            if (response.status == HttpStatusCode.OK) {
                val user = response.body<User1>()
                emit(
                    User(
                        userID = user.username,
                        fullName = user.fullName,
                        password = user.password,
                        year = user.year,
                        selectedCourse = user.selectedCourse,
                        role = user.role,
                        dateCreated = user.dateCreated
                    )
                )
            } else {
                emit(null)
                println("No user found 1")
            }
        } catch (e: Exception) {
            emit(null)
            println("No User found 2 $e") // In case of error, emit null
        }
    }
//    @OptIn(InternalAPI::class)
//     override fun getProfiles(id: String, fullName: String,selectedCourse: String, year: String  ): Flow<User> = flow {
//        try {
//            val response: HttpResponse = ktorClient.post(HttpRoutes.profile) {
//                contentType(ContentType.Application.Json)
//                body = MultiPartFormDataContent(formData {
//                    append("type", "profile")
//                    append("username", id)
//                    append("name", fullName)
//                    append("year", year)
//                    append("selectedCourse", selectedCourse)
//                })
//            }
//            println("Res: ${response.bodyAsText()}")
//            if (response.status == HttpStatusCode.OK) {
//                val user = response.body<User1>()
//                emit(
//                    User(
//                        userID = user.username,
//                        fullName = user.fullName,
//                        password = user.password,
//                        year = user.year,
//                        selectedCourse = user.selectedCourse,
//                        role = user.role,
//                        dateCreated = user.dateCreated
//                    )
//                )
//            } else {
//                println("No user found 1")
//            }
//        } catch (e: Exception) {
//            println("No User found 2 $e") // In case of error, emit null
//        }
//    }

    @OptIn(InternalAPI::class)
    override fun getAllUsers(): Flow<List<User>> = flow {
        try {
            val response: HttpResponse = ktorClient.post(HttpRoutes.login) {
                contentType(ContentType.Application.Json)
                body = MultiPartFormDataContent(formData {
                    append("type", "get_all_users")
                })
            }
            println("Res: ${response.bodyAsText()}")
            if (response.status == HttpStatusCode.OK) {
                val userList = response.body<UserList>()
                emit(userList.data)
            } else {
                println("No user found 1")
            }
        } catch (e: Exception) {
            println("No User found 2 $e") // In case of error, emit null
        }
    }

    @OptIn(InternalAPI::class)
    override fun getAllQuestions(): Flow<List<Question>> = flow {
        try {
            val response: HttpResponse = ktorClient.post(HttpRoutes.login) {
                contentType(ContentType.Application.Json)
                body = MultiPartFormDataContent(formData {
                    append("type", "get_all_questions")
                })
            }
            println("Questions Res: ${response.bodyAsText()}")
            if (response.status == HttpStatusCode.OK) {
                val questions = response.body<QuestionList>()
                emit(questions.questionsData)
            } else {
                println("No user found 1")
            }
        } catch (e: Exception) {
            println("No User found 2 $e") // In case of error, emit null
        }
    }
    override suspend fun deleteUser(user: User) = run{}


}

@Serializable
data class User1(
    val flag: Int,
    val message: String,
    val username: String,
    val password: String,
    val fullName: String,
    val year: String,
    val selectedCourse: String,
    val role: String,
    val dateCreated: String
)

@Serializable
data class UserList(
    val flag: Int,
    val message: String,
    val data: List<User>
)
@Serializable
data class QuestionList(
    val flag: Int,
    val message: String,
    val questionsData: List<Question>
)
