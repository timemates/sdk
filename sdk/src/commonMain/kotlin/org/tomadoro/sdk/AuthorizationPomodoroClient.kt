package org.tomadoro.sdk

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.plus
import org.tomadoro.sdk.internal.toResult
import org.tomadoro.sdk.results.SignWithGoogleResult
import org.tomadoro.sdk.results.serializer.ResultsSerializersModule
import org.tomadoro.sdk.types.serializer.TypesSerializersModule
import org.tomadoro.sdk.types.value.Code

public class AuthorizationPomodoroClient(
    private val apiUrl: String = "https://pomodoro.y9vad9.com"
) {
    private val client = HttpClient {
        val json = Json {
            @OptIn(ExperimentalSerializationApi::class)
            explicitNulls = false
            serializersModule = ResultsSerializersModule +
                TypesSerializersModule
        }

        defaultRequest {
            url(apiUrl)
            contentType(ContentType.parse("application/json"))
        }

        install(HttpTimeout) {
            connectTimeoutMillis = 15000L
            requestTimeoutMillis = 15000L
            socketTimeoutMillis = 15000L
        }

        install(ContentNegotiation) {
            json(json)
        }
    }

    public suspend fun authViaGoogle(code: Code): SignWithGoogleResult {
        return client.post("auth/google") {
            parameter("code", code.string)
        }.toResult()
    }
}