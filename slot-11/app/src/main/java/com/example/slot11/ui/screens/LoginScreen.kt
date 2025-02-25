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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun LoginScreen (
    viewModel: AuthViewModel,
    onNavigateToSignUp: () -> Unit,
) {
    val context = LocalContext.current
    var wasLoggedIn by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login",
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

        AuthButton(
            text = "Login",
            isLoading = viewModel.isLoading.value,
            onClick = { viewModel.login() },
            modifier = Modifier.fillMaxWidth()
        )

        CustomSpacer( height = 8 )

        SwitchScreenTextButton(
            message = "Don't have an account?",
            actionText = "Sign Up",
            onClick = {
                if (viewModel.successMessage.value != null) {
                    viewModel.clearSuccessMessage()
                }

                if (viewModel.errorMessage.value != null) {
                    viewModel.clearErrorMessage()
                }

                onNavigateToSignUp()
            }
        )

        CustomSpacer( height = 16 )

        AuthErrorMessage( errorMessage = viewModel.errorMessage.value )
    }

    LaunchedEffect(viewModel.isLoggedIn.value) {
        if (viewModel.isLoggedIn.value) {
            Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
        }
        wasLoggedIn = viewModel.isLoggedIn.value
    }
}