package com.example.myauthapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myauthapp.domain.SaveUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    val uiState = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        val currentState = _uiState.value
        val emailError = validateEmail(email)

        _uiState.value = currentState.copy(
            email = email,
            emailError = emailError,
            isFormValid = emailError == null && validatePassword(currentState.password) == null
        )
    }

    fun onPasswordChange(password: String) {
        val currentState = _uiState.value
        val passwordError = validatePassword(password)

        _uiState.value = currentState.copy(
            password = password,
            passwordError = passwordError,
            isFormValid = validateEmail(currentState.email) == null && passwordError == null
        )
    }

    fun onLoginClick(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                saveUserUseCase(_uiState.value.email, _uiState.value.password)
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Ошибка сохранения данных"
                )
            }
        }
    }

    private fun validateEmail(email: String): String? {
        return when {
            email.isBlank() -> "Email не может быть пустым"
            !email.contains("@") -> "Email должен содержать @"
            !email.contains(".") -> "Email должен содержать ."
            email.contains(" ") -> "Email не должен содержать пробелы"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Неверный формат email"
            else -> null
        }
    }

    private fun validatePassword(password: String): String? {
        return when {
            password.length < 8 -> "Пароль должен содержать минимум 8 символов"
            !password.any { it.isUpperCase() } -> "Пароль должен содержать минимум 1 заглавную букву"
            !password.any { it.isDigit() } -> "Пароль должен содержать минимум 1 цифру"
            !password.any { !it.isLetterOrDigit() } -> "Пароль должен содержать минимум 1 спецсимвол"
            else -> null
        }
    }
}

data class LoginState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isFormValid: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)