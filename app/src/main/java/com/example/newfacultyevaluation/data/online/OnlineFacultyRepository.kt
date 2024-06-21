package com.example.newfacultyevaluation.data.online

import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.FormEvaluation
import com.example.newfacultyevaluation.data.repo.FacultyRepo
import com.example.newfacultyevaluation.network.HttpRoutes
import com.example.newfacultyevaluation.network.KtorClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.call.receive
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
import kotlinx.serialization.Serializable

class OnlineFacultyRepository(private val ktorClient: HttpClient = KtorClient()) : FacultyRepo {
    @OptIn(InternalAPI::class)
    override fun getFormEvaluation(facultyID : String): Flow<List<FormEvaluation>> = flow {
        try {
            val response: HttpResponse = ktorClient.post(HttpRoutes.login) {
                contentType(ContentType.Application.Json)
                body = MultiPartFormDataContent(formData {
                    append("type", "get_faculty_evaluation")
                    append("facultyID", facultyID)
                })
            }
            println("Res Status OFR: ${response.status == HttpStatusCode.OK}")
            println("Res FEval OFR: ${response.bodyAsText()}")
            if (response.status == HttpStatusCode.OK) {
                println("SSSL : ${response.body<Form>()}")
                val formEvaluation = response.body<Form>()
                println("SSS: $formEvaluation")
                emit(formEvaluation.formList)
            } else {
                println("No user found 1")
            }
        } catch (e: Exception) {
            println("No User found 2 $e") // In case of error, emit null
        }
    }


}

@Serializable
data class Form(
    val flag: Int,
    val message: String,
    val formList: List<FormEvaluation>
)