package com.example.slot_3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun UserInputDemo() {
    var textField by remember { mutableStateOf("") }
    var outlinedTextField by remember { mutableStateOf("") }
    var basicTextField by remember { mutableStateOf("Basic Text Field") }
    var sliderValue by remember { mutableStateOf(50f) }
    var sliderPosition by remember { mutableStateOf(0f..100f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderText()

        DividerSection()

        TextFieldSection(
            textField,
            outlinedTextField,
            basicTextField,
            onTextFieldChange = { textField = it },
            onOutlinedTextFieldChange = { outlinedTextField = it },
            onBasicTextFieldChange = { basicTextField = it }
        )

        DividerSection(padding = PaddingValues(vertical = 70.dp))

        SliderSection(
            sliderValue,
            sliderPosition,
            onSliderChange = { sliderValue = it },
            onRangeSliderChange = { sliderPosition = it })
    }
}

@Composable
fun HeaderText() {
    Text(
        text = "Slot 3 - Code Demo \n\n \uD83E\uDDA5 Group 1",
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier.padding(bottom = 16.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun DividerSection(padding: PaddingValues = PaddingValues(bottom = 50.dp)) {
    HorizontalDivider(
        thickness = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding)
    )
}

@Composable
fun TextFieldSection(
    textField: String,
    outlinedTextField: String,
    basicTextField: String,
    onTextFieldChange: (String) -> Unit,
    onOutlinedTextFieldChange: (String) -> Unit,
    onBasicTextFieldChange: (String) -> Unit
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "TextField",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        TextField(
            value = textField,
            onValueChange = onTextFieldChange,
            label = { Text("Text Field Default") },
            placeholder = { Text("Type here...") },
            maxLines = 5,
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        )

        OutlinedTextField(
            value = outlinedTextField,
            onValueChange = onOutlinedTextFieldChange,
            label = { Text("Outlined Text Field") },
            placeholder = { Text("Type here...") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        )

        BasicTextField(
            value = basicTextField,
            onValueChange = onBasicTextFieldChange,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 18.sp
            )
        )
    }
}

@Composable
fun SliderSection(
    sliderValue: Float,
    sliderPosition: ClosedFloatingPointRange<Float>,
    onSliderChange: (Float) -> Unit,
    onRangeSliderChange: (ClosedFloatingPointRange<Float>) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
            onValueChange = onSliderChange,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.error,
                activeTrackColor = MaterialTheme.colorScheme.error,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = 4,
            valueRange = 0f..100f
        )

        Text(
            text = "Slider: ${sliderValue.toInt()}",
            modifier = Modifier.padding(bottom = 30.dp)
        )

        RangeSlider(
            value = sliderPosition,
            onValueChange = onRangeSliderChange,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.scrim,
                activeTrackColor = MaterialTheme.colorScheme.scrim,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = 5,
            valueRange = 0f..100f,
        )
        Text(text = "Range Slider: ${sliderPosition.toString()}")
    }
}