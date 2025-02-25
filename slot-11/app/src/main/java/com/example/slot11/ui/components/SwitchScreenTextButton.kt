package com.example.slot11.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun SwitchScreenTextButton (
    message: String,
    actionText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = buildAnnotatedString {
            append(message)
            append(" ")
            withStyle(
                style = SpanStyle ( color = MaterialTheme.colorScheme.primary)
            ) {
                append(actionText)
            }
        },
        modifier = modifier.clickable { onClick() }
    )
}