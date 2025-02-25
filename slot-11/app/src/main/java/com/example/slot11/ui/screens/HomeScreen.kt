package com.example.slot11.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.slot11.ui.components.AuthButton
import com.example.slot11.ui.components.CustomSpacer
import com.example.slot11.viewmodel.AuthViewModel

@Composable
fun HomeScreen (
    viewModel: AuthViewModel,
) {
    val tokenExpiresIn by viewModel.tokenExpiresIn.collectAsState()

    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome to Slot 11 Demo")

        CustomSpacer( height = 8 )

        Text(
            text = "Welcome, ${viewModel.email.value}",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        CustomSpacer( height = 8 )

        Text(
            text = "User ID: ${viewModel.userId.value}",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Access Token: ${viewModel.accessToken.value ?: "N/A"}",
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Refresh Token: ${viewModel.refreshToken.value ?: "N/A"}",
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Expires In: ${tokenExpiresIn ?: 0} seconds",
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Logged In: ${viewModel.isLoggedIn.value}",
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        CustomSpacer( height = 16 )

        AuthButton(
            text = "Refresh Token",
            isLoading = viewModel.isLoading.value,
            onClick = { viewModel.refreshSession() },
            modifier = Modifier.fillMaxWidth()
        )

        CustomSpacer( height = 8 )

        AuthButton(
            text = "Logout",
            isLoading = viewModel.isLoading.value,
            onClick = {
                viewModel.logout()
            },
            modifier = Modifier.fillMaxWidth()
        )

        CustomSpacer( height = 16 )
        viewModel.errorMessage.value?.let {
            Text(
                text = "Error: $it",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}