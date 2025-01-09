package com.example.slot_3

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun UserInputDemo() {
    var text by remember { mutableStateOf("") }
    var sliderValue by remember { mutableStateOf(50f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Slot 3 - Code Demo \n Group 1",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp)
        )

        Text(
            text = "EditText (OutlinedTextField)",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter your text") },
            placeholder = { Text("Type here...") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        )

        Text(
            text = if (text.isNotEmpty()) "You entered: $text" else "No text entered",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 100.dp)
        )

        Text(
            text = "Slider",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 1f..100f,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Selected value: ${sliderValue.toInt()}",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}
