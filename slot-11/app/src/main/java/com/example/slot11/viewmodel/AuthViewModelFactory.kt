package com.example.slot11.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.slot11.data.repository.AuthRepository
import supabase

class AuthViewModelFactory ( private val authRepository: AuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(authRepository, supabase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}