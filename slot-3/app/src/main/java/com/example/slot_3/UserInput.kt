package com.example.slot_3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserInputDemo() {
    var text by remember { mutableStateOf("") }
    var sliderValue by remember { mutableStateOf(50f) }
    var sliderPosition by remember { mutableStateOf(0f..100f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "Slot 3 - Code Demo \n\n \uD83E\uDDA5 Group 1",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        HorizontalDivider(
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
        )

        HorizontalDivider(
            thickness = 3.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 70.dp)
        )

        Text(
            text = "Slider",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 25.dp),
            textAlign = TextAlign.Center
        )

        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.error,
                activeTrackColor = MaterialTheme.colorScheme.error,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = 4,
            valueRange = 0f..100f
        )

        Text(
            text = "Selected value: ${sliderValue.toInt()}",
            modifier = Modifier.padding(bottom = 30.dp)
        )

        RangeSlider(
            value = sliderPosition,
            onValueChange = { range -> sliderPosition = range },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.scrim,
                activeTrackColor = MaterialTheme.colorScheme.scrim,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = 5,
            valueRange = 0f..100f,
        )
        Text(text = sliderPosition.toString())

    }
}
