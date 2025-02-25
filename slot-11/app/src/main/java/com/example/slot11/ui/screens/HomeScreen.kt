package com.example.slot11.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.slot11.ui.components.AuthButton
import com.example.slot11.ui.components.CustomSpacer
import com.example.slot11.viewmodel.AuthViewModel

@Composable
fun HomeScreen (
    viewModel: AuthViewModel,
    navController: NavController
) {
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

        AuthButton(
            text = "Logout",
            isLoading = viewModel.isLoading.value,
            onClick = {
                viewModel.logout()
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}