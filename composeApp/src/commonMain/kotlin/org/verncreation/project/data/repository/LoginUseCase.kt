package org.verncreation.project.data.repository

import org.verncreation.project.data.model.LoginResult

interface LoginUseCase {
    suspend fun login(email: String, password: String): LoginResult
}