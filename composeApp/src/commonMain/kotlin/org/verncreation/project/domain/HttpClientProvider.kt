package org.verncreation.project.domain
// shared/network/HttpClientProvider.kt

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.* // use Darwin for iOS
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object HttpClientProvider {
    val client: HttpClient by lazy {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }
    }
}
