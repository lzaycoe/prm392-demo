package com.example.slot11.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slot11.data.repository.AuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import kotlinx.coroutines.launch

class AuthViewModel (
    private val authRepository: AuthRepository,
    private val supabase: SupabaseClient
) : ViewModel() {
    var email =  mutableStateOf("")
    var password = mutableStateOf("")
    var confirmPassword = mutableStateOf("")
    var userId = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var errorMessage = mutableStateOf<String?>(null)
    var successMessage = mutableStateOf<String?>(null)
    var isLoggedIn = mutableStateOf(false)

    init {
        checkSession()
    }

    fun signUp () {
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
                    else -> exception?.message?: "Sign up failed"
                }
            }
        }
    }

    fun login () {
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
                val session = authRepository.supabase.auth.currentSessionOrNull()
                session?.user?.let { user ->
                    email.value = user.email?: ""
                    userId.value = user.id
                }
                isLoggedIn.value = true
            }

            if (result.isFailure) {
                val exception = result.exceptionOrNull()
                errorMessage.value = when {
                    exception?.message?.contains("Invalid login credentials") == true ->
                        "Email or password is incorrect. Please check and try again."
                    else -> exception?.message?: "Login failed"
                }
            }
        }
    }

    fun logout () {
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
            }

            if (result.isFailure) {
                errorMessage.value = result.exceptionOrNull()?.message?: "Logout failed"
            }
        }
    }

    fun clearSuccessMessage () {
        successMessage.value = null
    }

    fun clearErrorMessage () {
        errorMessage.value = null
    }

    private fun checkSession () {
        viewModelScope.launch {
            val session = supabase.auth.currentSessionOrNull()

            if (session != null) {
                isLoggedIn.value = true
                email.value = session.user?.email?: ""
                userId.value = session.user?.id?: ""
            } else {
                isLoggedIn.value = false
            }
        }
    }
}