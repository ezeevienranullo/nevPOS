package org.verncreation.project.data.repository

import org.verncreation.project.data.model.LoginResult

class LoginUseCaseImpl(
    private val authRepository: AuthRepository = AuthRepositoryImpl()
) : LoginUseCase {
    override suspend fun login(email: String, password: String): LoginResult {
        return try {
            val isAuthenticated = authRepository.authenticate(email, password)
            if (isAuthenticated) {
                LoginResult(isSuccess = true)
            } else {
                LoginResult(isSuccess = false, errorMessage = "Invalid credentials.")
            }
        } catch (e: Exception) {
            LoginResult(isSuccess = false, errorMessage = e.message ?: "Unknown error")
        }
    }
}
