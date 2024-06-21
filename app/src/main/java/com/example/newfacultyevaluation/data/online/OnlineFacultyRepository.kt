package com.example.newfacultyevaluation.data.online

import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.FormEvaluation
import com.example.newfacultyevaluation.data.repo.FacultyRepo
import com.example.newfacultyevaluation.network.HttpRoutes
import com.example.newfacultyevaluation.network.KtorClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OnlineFacultyRepository(private val ktorClient: HttpClient = KtorClient()) : FacultyRepo {
    @OptIn(InternalAPI::class)
    override fun getFormEvaluation(facultyName : String): Flow<List<FormEvaluation>> = flow {
        try {
            val response: HttpResponse = ktorClient.post(HttpRoutes.login) {
                contentType(ContentType.Application.Json)
                body = MultiPartFormDataContent(formData {
                    append("type", "get_faculty_evaluation")
                    append("facultyName", facultyName)
                })
            }
            println("Res FEval: ${response.bodyAsText()}")
            if (response.status == HttpStatusCode.OK) {
                val formEvaluation = response.body<Form>()
                emit(formEvaluation.formList)
            } else {
                println("No user found 1")
            }
        } catch (e: Exception) {
            println("No User found 2 $e") // In case of error, emit null
        }
    }


}

private data class Form(
    val flag: Int,
    val message: String,
    val formList: List<FormEvaluation>
)