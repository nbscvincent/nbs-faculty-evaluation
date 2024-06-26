package com.example.newfacultyevaluation.network


import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpRedirect
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import timber.log.Timber
import io.ktor.client.plugins.logging.Logger
import javax.inject.Singleton


@Singleton
fun KtorClient(): HttpClient {
    return HttpClient(Android) {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Timber.d("SAMPLE - HTTP call $message")
                    println("SAMPLE - HTTP call $message")
                }
            }
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json( Json {
                prettyPrint = true
                isLenient = true
                encodeDefaults = true
                ignoreUnknownKeys = true
                //explicitNulls = false
            })
        }
        install(HttpRedirect) {
            checkHttpMethod = false
            allowHttpsDowngrade = true
        }
    }
}