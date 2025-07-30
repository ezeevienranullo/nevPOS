package org.verncreation.project.presentation.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import org.verncreation.project.data.repository.LoginUseCaseImpl

@Composable
fun LoginScreenWrapper(onLoginSuccess: () -> Unit) {
    val viewModel = remember { LoginViewModel(LoginUseCaseImpl()) }
    val state by viewModel.uiState

    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            onLoginSuccess()
        }
    }

    LoginScreen(
        email = state.email,
        password = state.password,
        onEmailChange = viewModel::onEmailChanged,
        onPasswordChange = viewModel::onPasswordChanged,
        onLoginClick = viewModel::onLoginClicked,
        isLoading = state.isLoading,
        errorMessage = state.errorMessage,
    )
}
