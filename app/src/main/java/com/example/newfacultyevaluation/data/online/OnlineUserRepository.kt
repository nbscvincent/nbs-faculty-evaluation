package com.example.newfacultyevaluation.data.online

import android.system.Os.accept
import androidx.lifecycle.LiveData
import com.example.newfacultyevaluation.dao.UserDao
import com.example.newfacultyevaluation.data.model.User
import com.example.newfacultyevaluation.data.repo.UserRepository
import com.example.newfacultyevaluation.network.HttpRoutes
import com.example.newfacultyevaluation.network.HttpRoutes.BASE_URL
import com.example.newfacultyevaluation.network.HttpRoutes.login
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.server.util.url
import io.ktor.util.reflect.TypeInfo
import kotlinx.coroutines.flow.Flow


class OnlineUserRepository(private val ktorClient: HttpClient) : UserRepository {
    override suspend fun upsertUser(user: User) {

        val response = ktorClient.request(HttpRoutes.BASE_URL) {
            method = HttpMethod.Get
            url(HttpRoutes.MARS)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(login)
        } ;return response.body()
    }


    override suspend fun deleteUser(user: User) = run{}

    override fun getUsers(id: String): Flow<User> {
        return getUsers(id)
    }

    override fun getAllUsers(): Flow<List<User>> {
        return getAllUsers()
    }

}