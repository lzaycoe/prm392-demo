package com.example.slot11.data.repository

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository( val supabase: SupabaseClient) {
    private val auth: Auth = supabase.auth

    suspend fun signUp ( email: String, password: String) : Result<Unit> = withContext(Dispatchers.IO) {
        try {
            auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            Result.success(Unit)
        } catch ( e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login ( email: String, password: String) : Result<Unit> = withContext(Dispatchers.IO) {
        try {
            auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            Result.success(Unit)
        } catch ( e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun logout () : Result<Unit> = withContext(Dispatchers.IO) {
        try {
            auth.signOut()
            Result.success(Unit)
        } catch ( e: Exception) {
            Result.failure(e)
        }
    }
}