package org.verncreation.project.data.repository

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import org.verncreation.project.data.constant.ApiConstants
import org.verncreation.project.domain.HttpClientProvider
import kotlin.math.log

class AuthRepositoryImpl : AuthRepository {
    private val client = HttpClientProvider.client

    override suspend fun authenticate(email: String, password: String): Boolean {
        return try {
//            val response: LoginResponse = client.post("http://10.0.2.2:8004/api/login") {
            val response: LoginResponse = client.post("${ApiConstants.BASE_URL}/login") {
                contentType(ContentType.Application.Json)
                setBody(LoginRequest(email, password))
            }.body()
            println("LoginRequest(email, password) ${LoginRequest(email, password)}")
            println("Login successful token ${response.token}")
            println("response.token.isNotBlank() ${response.token.isNotBlank()}")
//            response.body()
            response.token.isNotBlank()
        } catch (e: Exception) {
            println("Login error: ${e.message}")
            false
        }
    }
}

@Serializable
data class LoginRequest(val email: String, val password: String)

@Serializable
data class LoginResponse(val token: String, val user: UserDto)

@Serializable
data class UserDto(
    val id: Int,
    val name: String,
    val email: String)