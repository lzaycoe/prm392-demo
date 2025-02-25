package com.example.slot11.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.slot11.ui.components.AuthButton
import com.example.slot11.ui.components.AuthErrorMessage
import com.example.slot11.ui.components.AuthTextField
import com.example.slot11.ui.components.CustomSpacer
import com.example.slot11.ui.components.SwitchScreenTextButton
import com.example.slot11.viewmodel.AuthViewModel

@Composable
fun SignupScreen (
    viewModel: AuthViewModel,
    onNavigateToLogin: () -> Unit
) {
    val context = LocalContext.current

    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        AuthTextField(
            value = viewModel.email.value,
            onValueChange = { viewModel.email.value = it },
            label = "Email",
            modifier = Modifier.fillMaxWidth()
        )

        CustomSpacer( height = 8 )

        AuthTextField(
            value = viewModel.password.value,
            onValueChange = { viewModel.password.value = it },
            label = "Password",
            isPassword = true,
            modifier = Modifier.fillMaxWidth()
        )

        CustomSpacer( height = 8 )

        AuthTextField(
            value = viewModel.confirmPassword.value,
            onValueChange = { viewModel.confirmPassword.value = it },
            label = "Confirm Password",
            isPassword = true,
            modifier = Modifier.fillMaxWidth()
        )

        CustomSpacer( height = 8 )

        AuthButton(
            text = "Sign Up",
            isLoading = viewModel.isLoading.value,
            onClick = { viewModel.signUp() },
            modifier = Modifier.fillMaxWidth()
        )

        CustomSpacer( height = 8)

        SwitchScreenTextButton(
            message = "Already have an account?",
            actionText = "Login",
            onClick = onNavigateToLogin
        )

        CustomSpacer( height = 16 )

        AuthErrorMessage( errorMessage = viewModel.errorMessage.value)
    }

    LaunchedEffect(viewModel.successMessage.value) {
        viewModel.successMessage.value?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            onNavigateToLogin()
            if (viewModel.errorMessage.value != null) {
                viewModel.clearErrorMessage()
            }
            viewModel.clearSuccessMessage()
        }
    }
}