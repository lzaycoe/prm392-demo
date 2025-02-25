package com.example.slot11.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slot11.data.repository.AuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository,
    private val supabase: SupabaseClient
) : ViewModel() {
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var confirmPassword = mutableStateOf("")
    var userId = mutableStateOf("")
    var accessToken = mutableStateOf<String?>(null)
    var refreshToken = mutableStateOf<String?>(null)

    private val _tokenExpiresIn = MutableStateFlow<Long?>(null)
    val tokenExpiresIn = _tokenExpiresIn.asStateFlow()

    var isLoading = mutableStateOf(false)
    var errorMessage = mutableStateOf<String?>(null)
    var successMessage = mutableStateOf<String?>(null)
    var isLoggedIn = mutableStateOf(false)

    private var countdownJob: Job? = null

    init {
        checkSession()
    }

    fun signUp() {
        if (email.value.isBlank() || password.value.isBlank() || confirmPassword.value.isBlank()) {
            errorMessage.value = "Email and password are required. Please enter and try again."
            return
        }
        if (password.value != confirmPassword.value) {
            errorMessage.value = "Passwords do not match. Please check and try again."
            return
        }
        viewModelScope.launch {
            isLoading.value = true
            errorMessage.value = null
            successMessage.value = null

            val result = authRepository.signUp(email.value, password.value)
            isLoading.value = false
            if (result.isSuccess) {
                successMessage.value = "Sign up successful!"
            }
            if (result.isFailure) {
                val exception = result.exceptionOrNull()
                errorMessage.value = when {
                    exception?.message?.contains("User already registered") == true ->
                        "Email already exists. Please login or use a different email."
                    else -> exception?.message ?: "Sign up failed"
                }
            }
        }
    }

    fun login() {
        if (email.value.isBlank() || password.value.isBlank()) {
            errorMessage.value = "Email and password are required"
            return
        }
        viewModelScope.launch {
            isLoading.value = true
            errorMessage.value = null
            successMessage.value = null

            val result = authRepository.login(email.value, password.value)
            isLoading.value = false
            if (result.isSuccess) {
                val session = supabase.auth.currentSessionOrNull()
                session?.let {
                    email.value = it.user?.email ?: ""
                    userId.value = it.user?.id ?: ""
                    accessToken.value = it.accessToken
                    refreshToken.value = it.refreshToken
                    _tokenExpiresIn.value = it.expiresIn
                    isLoggedIn.value = true
                    startCountdown()
                }
            }
            if (result.isFailure) {
                val exception = result.exceptionOrNull()
                errorMessage.value = when {
                    exception?.message?.contains("Invalid login credentials") == true ->
                        "Email or password is incorrect. Please check and try again."
                    else -> exception?.message ?: "Login failed"
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            isLoading.value = true
            errorMessage.value = null
            successMessage.value = null

            val result = authRepository.logout()
            isLoading.value = false
            if (result.isSuccess) {
                isLoggedIn.value = false
                email.value = ""
                password.value = ""
                confirmPassword.value = ""
                userId.value = ""
                accessToken.value = null
                refreshToken.value = null
                _tokenExpiresIn.value = null
                stopCountdown()
            }
            if (result.isFailure) {
                errorMessage.value = result.exceptionOrNull()?.message ?: "Logout failed"
            }
        }
    }

    fun clearSuccessMessage() {
        successMessage.value = null
    }

    fun clearErrorMessage() {
        errorMessage.value = null
    }

    private fun checkSession() {
        viewModelScope.launch {
            val session = supabase.auth.currentSessionOrNull()
            if (session != null) {
                isLoggedIn.value = true
                email.value = session.user?.email ?: ""
                userId.value = session.user?.id ?: ""
                accessToken.value = session.accessToken
                refreshToken.value = session.refreshToken
                _tokenExpiresIn.value = session.expiresIn
                startCountdown()
            } else {
                isLoggedIn.value = false
            }
        }
    }

    fun refreshSession() {
        viewModelScope.launch {
            try {
                val currentRefreshToken = refreshToken.value
                if (currentRefreshToken != null) {
                    println("Before refresh - Access Token: ${accessToken.value}")
                    println("Before refresh - Refresh Token: ${refreshToken.value}")
                    val session = supabase.auth.refreshSession(currentRefreshToken)
                    println("After refresh - New Access Token: ${session.accessToken}")
                    println("After refresh - New Refresh Token: ${session.refreshToken}")
                    println("After refresh - New Expires In: ${session.expiresIn}")
                    accessToken.value = session.accessToken
                    refreshToken.value = session.refreshToken
                    _tokenExpiresIn.value = session.expiresIn
                } else {
                    errorMessage.value = "Refresh token is missing"
                }
            } catch (e: Exception) {
                errorMessage.value = "Failed to refresh session: ${e.message}"
            }
        }
    }

    private fun startCountdown () {
        stopCountdown()
        countdownJob = viewModelScope.launch {
            while (_tokenExpiresIn.value != null && _tokenExpiresIn.value!! > 0) {
                delay(1000)
                _tokenExpiresIn.value = (_tokenExpiresIn.value ?: 0) - 1
            }

            if (_tokenExpiresIn.value == 0L) {
                errorMessage.value = "Session expired. Please refresh or login again."
            }
        }
    }

    private fun stopCountdown() {
        countdownJob?.cancel()
        countdownJob = null
    }

    override fun onCleared () {
        super.onCleared()
        stopCountdown()
    }
}