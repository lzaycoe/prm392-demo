package com.example.slot11.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AuthErrorMessage (
    errorMessage: String?,
    modifier: Modifier = Modifier
) {
    errorMessage?.let {
        Text(
            text = it,
            color = MaterialTheme.colorScheme.error,
            modifier = modifier
        )
    }
}