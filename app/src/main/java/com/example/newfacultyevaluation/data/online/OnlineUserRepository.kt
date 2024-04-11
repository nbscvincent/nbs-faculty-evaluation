package com.example.newfacultyevaluation.data.online

import android.system.Os.accept
import androidx.lifecycle.LiveData
import com.example.newfacultyevaluation.dao.UserDao
import com.example.newfacultyevaluation.data.model.User
import com.example.newfacultyevaluation.data.repo.UserRepository
import com.example.newfacultyevaluation.network.HttpRoutes

import com.example.newfacultyevaluation.network.HttpRoutes.login
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
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
import kotlinx.coroutines.flow.flow


class OnlineUserRepository(private val ktorClient: HttpClient) : UserRepository {
    override suspend fun upsertUser(user: User) = run{


    }


    override suspend fun deleteUser(user: User) = run{}

    override fun getUsers(id: String): Flow<User> {
        return getUsers(id)
    }

    override fun getAllUsers(): Flow<List<User>> {
        return getAllUsers()
    }

}